package com.example.recipefinder.di

import com.example.recipefinder.core.internet_connection_observer.ConnectivityObserver
import com.example.recipefinder.core.internet_connection_observer.NetworkConnectivityObserver
import com.example.recipefinder.data.repository.RecipeRepositoryImpl
import com.example.recipefinder.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModules {

    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ):RecipeRepository

    @Binds
    @Singleton
    abstract fun bindNetworkConnectivityObserver(
        networkConnectivityObserver: NetworkConnectivityObserver
    ):ConnectivityObserver
}