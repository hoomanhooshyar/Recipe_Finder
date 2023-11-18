package com.example.recipefinder.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val firstName:String? = null,
    val lastName:String? = null,
    val username:String? = null,
    val phoneNumber:String? = null
)
