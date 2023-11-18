package com.example.recipefinder.data.mapper

import com.example.recipefinder.data.model.MealResult
import com.example.recipefinder.domain.model.MealClient

fun MealResult.toMealClient():List<MealClient>{
    return meals.map { meal ->
        MealClient(
            idMeal = meal.idMeal,
            mealName = meal.strMeal,
            mealCategory = meal.strCategory,
            mealArea = meal.strArea,
            mealInstruction = meal.strInstructions,
            mealImage = meal.strMealThumb,
            mealYoutube = meal.strYoutube,
            mealIngredient1 = meal.strIngredient1,
            mealIngredient2 = meal.strIngredient2,
            mealIngredient3 = meal.strIngredient3,
            mealIngredient4 = meal.strIngredient4,
            mealIngredient5 = meal.strIngredient5,
            mealIngredient6 = meal.strIngredient6,
            mealIngredient7 = meal.strIngredient7,
            mealIngredient8 = meal.strIngredient8,
            mealIngredient9 = meal.strIngredient9,
            mealIngredient10 = meal.strIngredient10,
            mealIngredient11 = meal.strIngredient11,
            mealIngredient12 = meal.strIngredient12,
            mealIngredient13 = meal.strIngredient13,
            mealIngredient14 = meal.strIngredient14,
            mealIngredient15 = meal.strIngredient15,
            mealIngredient16 = meal.strIngredient16,
            mealIngredient17 = meal.strIngredient17,
            mealIngredient18 = meal.strIngredient18,
            mealIngredient19 = meal.strIngredient19,
            mealIngredient20 = meal.strIngredient20,
            mealMeasure1 = meal.strMeasure1,
            mealMeasure2 = meal.strMeasure2,
            mealMeasure3 = meal.strMeasure3,
            mealMeasure4 = meal.strMeasure4,
            mealMeasure5 = meal.strMeasure5,
            mealMeasure6 = meal.strMeasure6,
            mealMeasure7 = meal.strMeasure7,
            mealMeasure8 = meal.strMeasure8,
            mealMeasure9 = meal.strMeasure9,
            mealMeasure10 = meal.strMeasure10,
            mealMeasure11 = meal.strMeasure11,
            mealMeasure12 = meal.strMeasure12,
            mealMeasure13 = meal.strMeasure13,
            mealMeasure14 = meal.strMeasure14,
            mealMeasure15 = meal.strMeasure15,
            mealMeasure16 = meal.strMeasure16,
            mealMeasure17 = meal.strMeasure17,
            mealMeasure18 = meal.strMeasure18,
            mealMeasure19 = meal.strMeasure19,
            mealMeasure20 = meal.strMeasure20,

        )
    }
}