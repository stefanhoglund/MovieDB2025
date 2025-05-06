package com.example.moviedb2025.network

import android.provider.SyncStateContract
import com.example.moviedb2025.models.MovieResponse
import com.example.moviedb2025.models.ReviewResponse
import com.example.moviedb2025.models.VideoResponse
import com.example.moviedb2025.models.MovieDetails
import com.example.moviedb2025.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface MovieDBApiService {

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String = Constants.API_KEY
    ): MovieResponse

    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key")
        apiKey: String = Constants.API_KEY
    ): MovieResponse

    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): MovieDetails

    @GET("{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): ReviewResponse

    @GET("{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): VideoResponse


}