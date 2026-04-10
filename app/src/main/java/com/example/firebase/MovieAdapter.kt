package com.example.firebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebase.databinding.ItemMovieBinding

class MovieAdapter(
    private var movies: List<Movie>,
    private val onBookClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.apply {
            tvMovieTitle.text = movie.title
            tvGenre.text = "Genre: ${movie.genre}"
            tvRating.text = "Rating: ${movie.rating}"
            
            // Load image using Glide
            Glide.with(root.context)
                .load(movie.imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(ivMoviePoster)

            btnBook.setOnClickListener { onBookClick(movie) }
        }
    }

    override fun getItemCount() = movies.size

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}