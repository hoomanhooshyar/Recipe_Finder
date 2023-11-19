package com.example.recipefinder.presentation.util.internet_connection

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe():Flow<Status>
}