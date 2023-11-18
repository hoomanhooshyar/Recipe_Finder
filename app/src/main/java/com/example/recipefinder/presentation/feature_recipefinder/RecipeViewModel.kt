package com.example.recipefinder.presentation.feature_recipefinder

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.domain.repository.RecipeRepository
import com.example.recipefinder.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
):ViewModel() {
    var state by mutableStateOf(RecipeState())
        private set

    init {
        loadRandomRecipe()
    }

    private fun loadRandomRecipe(){
        state = state.copy(
            isLoading = true
        )
        viewModelScope.launch {
            when(val result = repository.getRandomRecipe()){
                is Resource.Success ->{
                    state = state.copy(
                        mealClients = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        mealClients = null,
                        isLoading = false,
                        error = result.message
                    )
                }
                is Resource.Loading -> {
                    state = state.copy(
                        mealClients = null,
                        isLoading = true,
                        error = null
                    )
                }
            }
        }
    }

    fun searchRecipe(query:String){
        viewModelScope.launch {
            when(val result = repository.getSearchedRecipe(query)){
                is Resource.Success ->{
                    state = state.copy(
                        mealClients = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error ->{
                    state = state.copy(
                        mealClients = null,
                        error = result.message,
                        isLoading = false
                    )
                }

                is Resource.Loading ->{
                    state = state.copy(
                        mealClients = null,
                        isLoading = true,
                        error = null
                    )
                }
            }

        }
    }
}