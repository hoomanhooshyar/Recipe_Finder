package com.example.recipefinder.presentation.feature_login

import com.example.recipefinder.domain.model.User

data class LoginState(
    val user: User? = null,
    val isLoading:Boolean = false,
    val error:String? = null
)
