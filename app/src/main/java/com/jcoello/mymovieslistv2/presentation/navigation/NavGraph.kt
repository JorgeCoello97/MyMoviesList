package com.jcoello.mymovieslistv2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jcoello.mymovieslistv2.presentation.screens.details.DetailsScreen
import com.jcoello.mymovieslistv2.presentation.screens.movies.MoviesScreen
import com.jcoello.mymovieslistv2.presentation.screens.series.SeriesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val selectedTabIndexState = remember { mutableStateOf(1) }
    NavHost(
        navController = navController,
        startDestination = Screen.Movies.route
    ) {
        composable(route = Screen.Movies.route) {
            MoviesScreen(
                navController = navController,
                selectedTabIndexState = selectedTabIndexState
            )
        }
        composable(route = Screen.Series.route) {
            SeriesScreen(
                navController = navController,
                selectedTabIndexState = selectedTabIndexState
            )
        }
        composable(
            route = Screen.Details.route+"/{posterPath}/{title}/{adult}/{isMovie}/{gendersIds}/{overview}/{voteAverage}",
            arguments = listOf(
                navArgument(name = "posterPath") {
                    type = NavType.StringType
                },
                navArgument(name = "title") {
                    type = NavType.StringType
                },
                navArgument(name = "adult") {
                    type = NavType.BoolType
                },
                navArgument(name = "isMovie") {
                    type = NavType.BoolType
                },
                navArgument(name = "gendersIds") {
                    type = NavType.StringType
                },
                navArgument(name = "overview") {
                    type = NavType.StringType
                },
                navArgument(name = "voteAverage") {
                    type = NavType.FloatType
                }
            )
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                posterPath = backStackEntry.arguments?.getString("posterPath") ?: "",
                title = backStackEntry.arguments?.getString("title") ?: "",
                adult = backStackEntry.arguments?.getBoolean("adult") ?: false,
                isMovie = backStackEntry.arguments?.getBoolean("isMovie") ?: false,
                gendersIdsTokenized = backStackEntry.arguments?.getString("gendersIds") ?: "" ,
                overview = backStackEntry.arguments?.getString("overview") ?: "",
                voteAverage = backStackEntry.arguments?.getFloat("voteAverage") ?: 0.0f
            )
        }
        composable(route = Screen.MyList.route) {

        }
    }
}