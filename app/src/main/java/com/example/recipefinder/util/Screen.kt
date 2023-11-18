package com.example.recipefinder.util

sealed class Screen(val route:String) {
    object RecipeFinderScreen:Screen(route = "recipe_screen")
    object LoginScreen:Screen(route = "login_screen")
    object RecipeDetailsScreen:Screen(route = "recipe_details_screen")
    object YoutubePlayerScreen:Screen(route = "youtube_player_screen")
}