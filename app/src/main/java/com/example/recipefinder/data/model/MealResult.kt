package com.example.recipefinder.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MealResult(
    @field:Json(name = "meals")
    val meals: List<Meal>
)