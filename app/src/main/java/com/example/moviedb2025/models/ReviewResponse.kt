package com.example.moviedb2025.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>
)

@Serializable
data class Review(
    val author: String,
    val content: String
)