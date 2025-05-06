package com.example.moviedb2025.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviedb2025.MovieDBApplication
import com.example.moviedb2025.database.MoviesRepository
import com.example.moviedb2025.models.Movie
import com.example.moviedb2025.models.MovieDetails
import com.example.moviedb2025.models.ReviewResponse
import com.example.moviedb2025.models.Review
import com.example.moviedb2025.models.Video
import com.example.moviedb2025.models.VideoResponse

import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface MovieListUiState {
    data class Success(val movies: List<Movie>) : MovieListUiState
    object Error : MovieListUiState
    object Loading : MovieListUiState
}

sealed interface SelectedMovieUiState {
    data class Success(val movie: MovieDetails) : SelectedMovieUiState
    object Error : SelectedMovieUiState
    object Loading : SelectedMovieUiState
}

class MovieDBViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    var movieListUiState: MovieListUiState by mutableStateOf(MovieListUiState.Loading)
        private set

    var selectedMovieUiState: SelectedMovieUiState by mutableStateOf(SelectedMovieUiState.Loading)
        private set

    init {
        getPopularMovies()
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                MovieListUiState.Success(moviesRepository.getTopRatedMovies().results)
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                MovieListUiState.Success(moviesRepository.getPopularMovies().results)
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

//    fun setSelectedMovie(movie: Movie) {
//        viewModelScope.launch {
//            selectedMovieUiState = SelectedMovieUiState.Loading
//            selectedMovieUiState = try {
//                SelectedMovieUiState.Success(movie)
//            } catch (e: IOException) {
//                SelectedMovieUiState.Error
//            } catch (e: HttpException) {
//                SelectedMovieUiState.Error
//            }
//        }
//    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            selectedMovieUiState = SelectedMovieUiState.Loading
            selectedMovieUiState = try {
                val movieDetails = moviesRepository.getMovieDetails(movieId)
                SelectedMovieUiState.Success(movieDetails)
            } catch (e: IOException) {
                SelectedMovieUiState.Error
            } catch (e: HttpException) {
                SelectedMovieUiState.Error
            }
        }
    }

    var movieReviews by mutableStateOf<List<Review>>(emptyList())
        private set

    fun getMovieReviews(movieId: Int) {
        viewModelScope.launch {
            try {
                movieReviews = moviesRepository.getMovieReviews(movieId).results
            } catch (e: Exception) {
                movieReviews = emptyList()
            }
        }
    }

    var movieVideos by mutableStateOf<List<Video>>(emptyList())
        private set

    fun fetchMovieVideos(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = moviesRepository.getMovieVideos(movieId)
                movieVideos = response.results
            } catch (e: Exception) {
                movieVideos = emptyList()
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieDBApplication)
                val moviesRepository = application.container.moviesRepository
                MovieDBViewModel(moviesRepository = moviesRepository)
            }
        }
    }
}