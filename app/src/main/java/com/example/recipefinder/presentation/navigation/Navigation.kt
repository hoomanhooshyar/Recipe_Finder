package com.example.recipefinder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipefinder.presentation.feature_login.LoginScreen
import com.example.recipefinder.presentation.feature_recipe_details.RecipeDetailsScreen
import com.example.recipefinder.presentation.feature_recipefinder.RecipeFinderScreen
import com.example.recipefinder.presentation.feature_youtube.YoutubePlayerScreen
import com.example.recipefinder.util.Screen

@Composable
fun Navigation(
    viewModel: NavigationViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val startDestination = viewModel.startDestination.collectAsState()

    NavHost(
        navController = navController,
        startDestination = startDestination.value
    ){
        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(route = Screen.RecipeFinderScreen.route){
            RecipeFinderScreen(navController = navController)
        }
        composable(
            route = "${Screen.RecipeDetailsScreen.route}/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                }
            )
        ){
            RecipeDetailsScreen(id = it.arguments?.getString("id"), navController = navController)
        }
        composable(
            route = "${Screen.YoutubePlayerScreen.route}/{videoId}",
            arguments = listOf(
                navArgument("videoId"){
                    type = NavType.StringType
                }
            )
        ){
            YoutubePlayerScreen(videoId = it.arguments?.getString("videoId"))
        }
    }
}