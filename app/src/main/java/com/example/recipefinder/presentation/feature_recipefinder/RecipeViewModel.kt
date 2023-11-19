package com.example.recipefinder.presentation.feature_recipefinder


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.core.internet_connection.ConnectivityObserver
import com.example.recipefinder.core.internet_connection.Status
import com.example.recipefinder.domain.repository.RecipeRepository
import com.example.recipefinder.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private val connectivityObserver: ConnectivityObserver
):ViewModel() {
    var state by mutableStateOf(RecipeState())
        private set

    private val _status = MutableStateFlow(Status.Unavailable)
    val status:StateFlow<Status> get() = _status

    init {
        networkConnectionCheck()
        loadRandomRecipe()
    }

    private fun networkConnectionCheck(){
        viewModelScope.launch {
            connectivityObserver.observe().collect{ newStatus ->
                _status.value = newStatus
            }
        }
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