package com.example.recipefinder.data.remote

import com.example.recipefinder.data.model.MealResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("search.php")
    suspend fun searchRecipe(
        @Query("s") search:String
    ):MealResult

    @GET("random.php")
    suspend fun getRandomRecipe():MealResult

    @GET("lookup.php")
    suspend fun getRecipeById(
        @Query("i") id:String?
    ):MealResult
}