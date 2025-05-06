@file:kotlin.OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("INFERRED_TYPE_VARIABLE_INTO_EMPTY_INTERSECTION_WARNING")

package com.example.myapplication0.ui.screens

import androidx.annotation.OptIn
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviedb2025.R
//import com.example.moviedb2025.database.Movies
import com.example.moviedb2025.models.Movie
import com.example.moviedb2025.ui.screens.MovieDetailScreen
import com.example.moviedb2025.ui.screens.MovieDetailPlotScreen
import com.example.moviedb2025.ui.screens.MovieListItemCard
//import com.example.moviedb2025.ui.screens.MovieListScreen
import com.example.moviedb2025.ui.screens.MovieGridScreen
import com.example.moviedb2025.ui.theme.MovieDB2025Theme
import com.example.moviedb2025.viewmodel.MovieDBViewModel


enum class MovieDBScreen(@StringRes val title: Int){
    List(title = R.string.app_name),
    Detail(title = R.string.movie_detail),
    Plot(title = R.string.movie_plot)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDBAppBar(
    currScreen: MovieDBScreen,
    canNavigateBack:Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {Text(stringResource(currScreen.title))},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
@Composable
fun MovieDbApp(viewModel: MovieDBViewModel = viewModel(factory = MovieDBViewModel.Factory),
               navController: NavHostController = rememberNavController()
) {
    val backStackEntity by navController.currentBackStackEntryAsState()

    val currentScreen = MovieDBScreen.valueOf(
        backStackEntity?.destination?.route ?: MovieDBScreen.List.name
    )

    Scaffold(
        topBar = {
            MovieDBAppBar(currScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
//        val uiState by viewModel.uiState.collectAsState()
        val movieDBViewModel: MovieDBViewModel = viewModel(factory = MovieDBViewModel.Factory)
        NavHost(
            navController = navController,
            startDestination = MovieDBScreen.List.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            composable(route = MovieDBScreen.List.name){
                MovieGridScreen(
//                    movieList = Movies().getMovies(),
                    movieListUiState = movieDBViewModel.movieListUiState,
                    onMovieListItemClicked = { movie ->
                        viewModel.getMovieDetails(movie.id.toInt())
                        navController.navigate(MovieDBScreen.Detail.name)
                    }, modifier = Modifier.fillMaxSize().padding(16.dp))
            }

            composable(route = MovieDBScreen.Detail.name) {
                MovieDetailScreen(
                    selectedMovieUiState = movieDBViewModel.selectedMovieUiState,
                    modifier = Modifier,
                    navController = navController
                )
            }

//            composable(route = MovieDBScreen.Detail.name){
//                uiState.selectedMovie?.let { movie ->
//                    MovieDetailScreen(movie = movie,
//                        modifier = Modifier,
//                        navController = navController)
//                }
//            }
//

            composable(route = MovieDBScreen.Plot.name){
                MovieDetailPlotScreen(
                    selectedMovieUiState = movieDBViewModel.selectedMovieUiState,
                    modifier = Modifier,
                    reviews = movieDBViewModel.movieReviews,
                    videos = movieDBViewModel.movieVideos,
                    navController = navController,
                    onFetchReviews = { movieId -> viewModel.getMovieReviews(movieId) },
                    onFetchVideos = { movieId -> viewModel.fetchMovieVideos(movieId) }
                )
            }


        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieDB2025Theme {
        MovieListItemCard(
            movie = Movie(
                2,
                "Captain America: Brave New World",
                "/pzIddUEMWhWzfvLI3TwxUG2wGoi.jpg",
                "/gsQJOfeW45KLiQeEIsom94QPQwb.jpg",
                "2025-02-12",
                "When a group of radical activists take over an energy company's annual gala, seizing 300 hostages, an ex-soldier turned window cleaner suspended 50 storeys up on the outside of the building must save those trapped inside, including her younger brother.",
//                "http://google.com",
//                "",
//                null

            ), {}
        )
    }
}