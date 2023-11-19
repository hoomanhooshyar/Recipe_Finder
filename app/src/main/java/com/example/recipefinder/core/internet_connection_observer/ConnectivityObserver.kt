package com.example.recipefinder.core.internet_connection_observer

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe():Flow<Status>
}