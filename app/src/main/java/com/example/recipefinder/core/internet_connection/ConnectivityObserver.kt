package com.example.recipefinder.core.internet_connection

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe():Flow<Status>
}