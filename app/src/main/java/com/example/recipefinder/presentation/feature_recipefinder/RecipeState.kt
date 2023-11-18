package com.example.recipefinder.presentation.feature_recipefinder

import com.example.recipefinder.domain.model.MealClient

data class RecipeState(
    val mealClients:List<MealClient>? = null,
    val isLoading:Boolean = false,
    val error:String? = null
)
