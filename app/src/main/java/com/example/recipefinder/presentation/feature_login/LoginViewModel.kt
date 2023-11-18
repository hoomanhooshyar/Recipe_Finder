package com.example.recipefinder.presentation.feature_login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.domain.model.User
import com.example.recipefinder.util.data_store.AppUser
import com.example.recipefinder.util.data_store.AppUserSerializer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore:DataStore<AppUser>
):ViewModel() {
    val appUser:StateFlow<AppUser> = dataStore.data
        .stateIn(viewModelScope, SharingStarted.Eagerly,AppUserSerializer.defaultValue)
    var state by mutableStateOf(LoginState())
        private set
    var isCorrectUsername by mutableStateOf(false)
    private set



    fun login(username:String,password:String){
        if(username == "user1" && password == "123456"){
            state = state.copy(
                user = User(
                    "User",
                    lastName = "name",
                    username = username,
                    phoneNumber = "09151234567"
                ),
                isLoading = false,
                error = null
            )

            viewModelScope.launch {
                saveAppUser(state.user!!)
                isCorrectUsername = true
            }

        }else{
            state = state.copy(
                user = null,
                isLoading = false,
                error = "Your username and/or password is wrong"
            )
        }
    }

    private suspend fun saveAppUser(user: User){
        dataStore.updateData {
            it.copy(user = user)
        }
    }
    

}