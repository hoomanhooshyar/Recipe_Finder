package com.example.recipefinder.util.data_store

import com.example.recipefinder.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class AppUser(
    val user:User = User()
)