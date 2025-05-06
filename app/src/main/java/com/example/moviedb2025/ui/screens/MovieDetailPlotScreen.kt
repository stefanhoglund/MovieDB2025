package com.example.moviedb2025.ui.screens


import android.content.Intent
import android.content.ActivityNotFoundException
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import android.net.Uri
import coil.compose.AsyncImage
import com.example.moviedb2025.models.Movie
import com.example.moviedb2025.models.MovieDetails
import com.example.moviedb2025.models.Review
import com.example.moviedb2025.models.Video
import com.example.moviedb2025.models.ReviewResponse
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.platform.LocalContext

import com.example.moviedb2025.database.Genres
import com.example.moviedb2025.utils.Constants
import com.example.moviedb2025.viewmodel.SelectedMovieUiState
import androidx.navigation.NavController


@Composable
fun ReviewsList(
    reviews: List<Review>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier.padding(8.dp)) {
        items(reviews) { review ->
            ReviewCard(review = review)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun ReviewCard(review: Review, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(250.dp)
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Author: ${review.author}",
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = review.content,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun TrailersList(videos: List<Video>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier.padding(8.dp)) {
        items(videos) { video ->
            TrailerCard(video = video)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun TrailerCard(video: Video, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(300.dp)
            .padding(8.dp)
    ) {
        Column {
            if (video.site == "YouTube") {

                val videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"

                VideoPlayer(
                    videoUrl = videoUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }
            Text(
                text = video.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun VideoPlayer(videoUrl: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(videoUrl)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = false
        }
    }

    DisposableEffect(key1 = exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = {
            PlayerView(it).apply {
                player = exoPlayer
            }
        },
        modifier = modifier
    )
}


@Composable
fun MovieDetailPlotScreen(
    selectedMovieUiState: SelectedMovieUiState,
    reviews: List<Review>,
    videos: List<Video>,
    modifier: Modifier = Modifier,
    navController: NavController,
    onFetchReviews: (Int) -> Unit,
    onFetchVideos: (Int) -> Unit

) {

    when (selectedMovieUiState) {
        is SelectedMovieUiState.Success -> {
            LaunchedEffect(selectedMovieUiState.movie.id) {
                onFetchReviews(selectedMovieUiState.movie.id.toInt())
                onFetchVideos(selectedMovieUiState.movie.id.toInt())
            }

            Column(modifier = modifier.padding(8.dp)) {
                Text(
                    text = "Reviews",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                ReviewsList(reviews = reviews)
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Trailers",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                TrailersList(videos = videos)
            }
        }
        is SelectedMovieUiState.Loading -> {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(16.dp)
            )
        }
        is SelectedMovieUiState.Error -> {
            Text(
                text = "Error...",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
