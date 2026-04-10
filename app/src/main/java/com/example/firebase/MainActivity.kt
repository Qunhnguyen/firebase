package com.example.firebase

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: MovieAdapter

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupRecyclerView()
        loadMovies()
        askNotificationPermission()
        getFCMToken() // Lấy token ở đây

        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_logout) {
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            } else {
                false
            }
        }
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM_TOKEN", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }
            val token = task.result
            Log.d("FCM_TOKEN", "TOKEN_CUA_BAN_LA: $token")
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(emptyList()) { movie ->
            bookTicket(movie)
        }
        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
        binding.rvMovies.adapter = adapter
    }

    private fun loadMovies() {
        binding.progressBar.visibility = View.VISIBLE
        db.collection("movies")
            .get()
            .addOnSuccessListener { result ->
                binding.progressBar.visibility = View.GONE
                val movies = result.toObjects(Movie::class.java)
                if (movies.isEmpty()) {
                    addSampleMovies()
                } else {
                    adapter.updateMovies(movies)
                }
            }
            .addOnFailureListener { e ->
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addSampleMovies() {
        val sampleMovies = listOf(
            Movie("1", "Inception", "https://image.tmdb.org/t/p/w500/9gk7Fn9sVAsj9o9iwYg9M8O3sM6.jpg", "Sci-fi thriller", 8.8, "Sci-Fi"),
            Movie("2", "Interstellar", "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlSaba7.jpg", "Space exploration", 8.6, "Adventure"),
            Movie("3", "The Dark Knight", "https://image.tmdb.org/t/p/w500/qJ2tW6ixuS1Y3mZ0p6S3TqS6S3f.jpg", "Batman movie", 9.0, "Action"),
            Movie("4", "Deadpool & Wolverine", "https://image.tmdb.org/t/p/w500/8cdWjvZQUmOZpU7qScyZlbPE6C5.jpg", "Marvel comedy", 8.2, "Action")
        )
        for (movie in sampleMovies) {
            db.collection("movies").document(movie.id).set(movie)
        }
        loadMovies()
    }

    private fun bookTicket(movie: Movie) {
        val user = auth.currentUser ?: return
        val ticketId = UUID.randomUUID().toString()
        val ticket = Ticket(
            id = ticketId,
            userId = user.uid,
            movieId = movie.id,
            movieTitle = movie.title,
            showtime = "20:00 PM, Today",
            seat = "A1",
            price = 100000
        )

        db.collection("tickets").document(ticketId)
            .set(ticket)
            .addOnSuccessListener {
                Toast.makeText(this, "Ticket booked for ${movie.title}!", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Booking failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}