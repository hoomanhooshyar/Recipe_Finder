package com.example.recipefinder.presentation.feature_recipe_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.domain.repository.RecipeRepository
import com.example.recipefinder.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val repository: RecipeRepository
):ViewModel() {
    private val _state = mutableStateOf(RecipeDetailsState())
    val state:State<RecipeDetailsState> = _state

    fun getRecipeById(id:String?){
        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch {
            when(val result = repository.getRecipeById(id)){
                is Resource.Success ->{
                    _state.value = _state.value.copy(
                        mealClient = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error ->{
                    _state.value = _state.value.copy(
                        mealClient = null,
                        isLoading = false,
                        error = result.message
                    )
                }
                is Resource.Loading ->{
                    _state.value = _state.value.copy(
                        mealClient = null,
                        isLoading = true,
                        error = null
                    )
                }
            }
        }
    }

}