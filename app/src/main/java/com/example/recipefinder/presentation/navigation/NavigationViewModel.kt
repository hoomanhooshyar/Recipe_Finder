package com.example.recipefinder.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.presentation.feature_login.LoginViewModel
import com.example.recipefinder.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
     loginViewModel: LoginViewModel
):ViewModel() {
    val startDestination:StateFlow<String> = loginViewModel.appUser.map {
        if(it.user.username.isNullOrEmpty()){
            Screen.LoginScreen.route
        }else{
            Screen.RecipeFinderScreen.route
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly,Screen.LoginScreen.route)
}