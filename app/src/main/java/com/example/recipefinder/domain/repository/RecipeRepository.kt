package com.example.recipefinder.domain.repository

import com.example.recipefinder.domain.model.MealClient
import com.example.recipefinder.domain.util.Resource

interface RecipeRepository {
    suspend fun getRandomRecipe():Resource<List<MealClient>>
    suspend fun getSearchedRecipe(query:String):Resource<List<MealClient>>
    suspend fun getRecipeById(id:String?):Resource<List<MealClient>>
}