package com.example.recipefinder.presentation.feature_recipe_details

import com.example.recipefinder.domain.model.MealClient

data class RecipeDetailsState(
    val mealClient: List<MealClient>? = null,
    val isLoading:Boolean = false,
    val error:String? = null
)
