package com.example.moviedb2025.database

import com.example.moviedb2025.models.MovieResponse
import com.example.moviedb2025.models.MovieDetails
import com.example.moviedb2025.models.ReviewResponse
import com.example.moviedb2025.models.VideoResponse
import com.example.moviedb2025.network.MovieDBApiService

interface MoviesRepository {
    suspend fun getPopularMovies(): MovieResponse
    suspend fun getTopRatedMovies(): MovieResponse
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMovieReviews(movieId: Int): ReviewResponse
    suspend fun getMovieVideos(movieId: Int): VideoResponse
}

class NetworkMoviesRepository(private val apiService: MovieDBApiService) : MoviesRepository {
    override suspend fun getPopularMovies(): MovieResponse {
        return apiService.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): MovieResponse {
        return apiService.getTopRatedMovies()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails(movieId)
    }

    override suspend fun getMovieReviews(movieId: Int): ReviewResponse {
        return apiService.getMovieReviews(movieId)
    }

    override suspend fun getMovieVideos(movieId: Int): VideoResponse {
        return apiService.getMovieVideos(movieId)
    }
}