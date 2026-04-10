package com.example.firebase

data class Movie(
    val id: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val rating: Double = 0.0,
    val genre: String = ""
)

data class Ticket(
    val id: String = "",
    val userId: String = "",
    val movieId: String = "",
    val movieTitle: String = "",
    val showtime: String = "",
    val seat: String = "",
    val price: Long = 0
)