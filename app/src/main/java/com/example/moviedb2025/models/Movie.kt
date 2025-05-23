package com.example.moviedb2025.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Movie(
    @SerialName(value = "id")
    var id: Long = 0L,

    @SerialName(value = "title")
    var title: String,

    @SerialName(value = "poster_path")
    var posterPath: String,

    @SerialName(value = "backdrop_path")
    var backdropPath: String?,

    @SerialName(value = "release_date")
    var releaseDate: String,

    @SerialName(value = "overview")
    var overview: String,

//    @SerialName(value = "homePage")
//    var homePage: String?,
//
//    @SerialName(value = "imdb_id")
//    var imdb_id: String?,
//
//    @SerialName(value = "genres")
//    var genres: List<Long>?
)