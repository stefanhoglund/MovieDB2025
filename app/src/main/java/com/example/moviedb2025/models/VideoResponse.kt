package com.example.moviedb2025.models

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val type: String
)

@Serializable
data class VideoResponse(
    val id: Int,
    val results: List<Video>
)