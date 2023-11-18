package com.example.recipefinder.data.repository

import com.example.recipefinder.data.mapper.toMealClient
import com.example.recipefinder.data.remote.RecipeApi
import com.example.recipefinder.domain.model.MealClient
import com.example.recipefinder.domain.repository.RecipeRepository
import com.example.recipefinder.domain.util.Resource
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api: RecipeApi
) : RecipeRepository {
    override suspend fun getRandomRecipe(): Resource<List<MealClient>> {

        return try {

            Resource.Success(
                data = api.getRandomRecipe().toMealClient()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message)
        }
    }

    override suspend fun getSearchedRecipe(query: String): Resource<List<MealClient>> {

        return try {

            Resource.Success(
                data = api.searchRecipe(query).toMealClient()
            )

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message)
        }
    }

    override suspend fun getRecipeById(id: String?): Resource<List<MealClient>> {
        return try {
            Resource.Success(
                data = api.getRecipeById(id).toMealClient()
            )
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Error(message = e.message)
        }
    }
}