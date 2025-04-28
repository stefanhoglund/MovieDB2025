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
import androidx.compose.ui.unit.dp
import android.net.Uri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviedb2025.R
import com.example.moviedb2025.models.Movie
import com.example.moviedb2025.database.Genres
import com.example.moviedb2025.utils.Constants
import com.example.myapplication0.ui.screens.MovieDBScreen

@Composable
fun MovieDetailScreen(movie: Movie,
                      modifier: Modifier = Modifier,
                      navController: NavController) {

    val context = LocalContext.current
    val packageName = "com.imdb.mobile"

    Column {
        Box {
            AsyncImage(
                model = Constants.BACKDROP_IMAGE_BASE_URL + Constants.BACKDROP_IMAGE_BASE_WIDTH + movie.backdropPath,
                contentDescription = movie.title,
                modifier = Modifier, //complete weight
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = movie.releaseDate,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis

        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = "Homepage",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,

            )

        Text(
            text = movie.homePage,
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.clickable {
                val webIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.homePage))
                context.startActivity(webIntent)
                }
            )


        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = "IMDB Movie Page",
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                val primaryIntent = movie?.let {
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("imdb:///title/"+movie.imdb_id)
                    )
                } ?: context.packageManager.getLaunchIntentForPackage(packageName)

                try {
                    if (primaryIntent != null) {
                        context.startActivity(primaryIntent)
                        return@clickable
                    }
                } catch (_: ActivityNotFoundException) {
                   /* */
                }

                val playIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                context.startActivity(playIntent)

            }
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = "Movie Plot",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {

                navController.navigate(MovieDBScreen.Plot.name  )
            }
            )




        Spacer(modifier = Modifier.size(8.dp))




        Text(
            text = "Genres",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,

        )

        movie.genres?.forEach { genre ->
            Text(
                text = Genres().getGenre(genre).name,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis

            )
        }

    }
}