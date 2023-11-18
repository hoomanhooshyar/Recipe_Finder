package com.example.recipefinder.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import com.example.recipefinder.data.remote.RecipeApi
import com.example.recipefinder.domain.util.Constants.BASE_URL
import com.example.recipefinder.presentation.feature_login.LoginViewModel
import com.example.recipefinder.presentation.navigation.NavigationViewModel
import com.example.recipefinder.util.data_store.AppUser
import com.example.recipefinder.util.data_store.AppUserSerializer
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi():Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    @Singleton
    @Provides
    fun provideRecipeApi(moshi: Moshi):RecipeApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideAppUserSerializer():Serializer<AppUser>{
        return AppUserSerializer
    }





    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context,
        serializer: Serializer<AppUser>
    ): DataStore<AppUser> {
        return DataStoreFactory.create(
            serializer = serializer,
            produceFile = { File(context.dataDir, "datastore/preferences") }
        )
    }

    @Provides
    @Singleton
    fun provideLoginViewModel(
        dataStore: DataStore<AppUser>
    ):LoginViewModel{
        return LoginViewModel(dataStore)
    }

    @Provides
    @Singleton
    fun provideNavigationViewModel(
        loginViewModel: LoginViewModel
    ):NavigationViewModel{
        return NavigationViewModel(loginViewModel)
    }
}