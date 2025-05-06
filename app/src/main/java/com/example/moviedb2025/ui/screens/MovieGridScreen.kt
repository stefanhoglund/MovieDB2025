package com.example.moviedb2025.ui.screens

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviedb2025.models.Movie
import com.example.moviedb2025.utils.Constants
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviedb2025.ui.theme.MovieDB2025Theme
import com.example.moviedb2025.viewmodel.MovieListUiState

@Composable
fun MovieGridScreen(
    movieListUiState: MovieListUiState,
    onMovieListItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    when (movieListUiState) {
        is MovieListUiState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3), // 🔥 2 columns
                modifier = modifier.padding(8.dp) // optional padding
            ) {
                items(movieListUiState.movies) { movie ->
                    MovieGridItemCard(
                        movie = movie,
                        onMovieListItemClicked,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        is MovieListUiState.Loading -> {
            // You can still show Loading as a simple Column
            Column(modifier = modifier.padding(16.dp)) {
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }

        is MovieListUiState.Error -> {
            Column(modifier = modifier.padding(16.dp)) {
                Text(
                    text = "Error: Something went wrong!",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
fun MovieGridItemCard(movie: Movie,
                      onMovieListItemClicked: (Movie) -> Unit,
                      modifier: Modifier = Modifier) {
    Card(modifier = modifier,
        onClick = {
            onMovieListItemClicked(movie)
        } )
    {
        Row {
            Box {
                AsyncImage(
                    model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_BASE_WIDTH + movie.posterPath,
                    contentDescription = movie.title,
                    modifier = modifier
                        .width(92.dp)
                        .height(138.dp),
                    contentScale = ContentScale.Crop
             )
            }
//            Column {
//               Text(
//                    text = movie.title,
//                    style = MaterialTheme.typography.bodySmall
//                )
//                Spacer(modifier = androidx.compose.ui.Modifier.size(8.dp))
//
//                Text(
//                    text = movie.releaseDate,
//                    style = MaterialTheme.typography.bodySmall
//                )
//                Spacer(modifier = androidx.compose.ui.Modifier.size(8.dp))
//
//                Text(
//                    text = movie.overview,
//                    style = MaterialTheme.typography.bodySmall,
//                    maxLines = 3,
//                    overflow = TextOverflow.Ellipsis
//                )
//                Spacer(modifier = androidx.compose.ui.Modifier.size(8.dp))
//            }
        }
    }
}