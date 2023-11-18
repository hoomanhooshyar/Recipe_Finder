package com.example.recipefinder.presentation.feature_login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipefinder.util.Screen



@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {

    var usernameValue by remember { mutableStateOf("") }
    var noUsername by remember { mutableStateOf(false) }
    var passwordValue by remember { mutableStateOf("") }
    var noPassword by remember { mutableStateOf(false) }
    val passwordFocusRequester = remember { FocusRequester() }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        TextField(
            value = usernameValue,
            onValueChange = {
                usernameValue = it
            },
            label = {
                Text(text = "Username")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person"
                )
            },
            supportingText = {
                if (noUsername) {
                    Text(text = "Enter your username")
                } else {
                    Text(text = "*required")
                }
            },
            isError = noUsername,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    noUsername = if (usernameValue == "") {
                        true
                    } else {
                        passwordFocusRequester.requestFocus()
                        false
                    }
                }
            )
        )//TextField

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            label = {
                Text(text = "Enter Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Password,
                    contentDescription = "password"
                )
            },
            supportingText = {
                if (noPassword) {
                    Text(text = "Please enter your password")
                } else {
                    Text(text = "*required")
                }
            },
            isError = noPassword,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (passwordValue == "") {
                        noPassword = true
                    } else {
                        noPassword = false
                        viewModel.login(
                            username = usernameValue,
                            password = passwordValue

                        )
                    }
                }
            ),

            modifier = Modifier.focusRequester(passwordFocusRequester)
        )//TextField

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (usernameValue == "" && passwordValue == "") {
                noUsername = true
                noPassword = true
                return@Button
            }
            if (usernameValue == "") {
                noUsername = true
                return@Button
            }
            if (passwordValue == "") {
                noPassword = true
                return@Button
            }
            noUsername = false
            noPassword = false
            viewModel.login(
                username = usernameValue,
                password = passwordValue

            )
        }) {
            Text(text = "Login")
        }

    }//Column
    if(viewModel.isCorrectUsername){
        navController.navigate(Screen.RecipeFinderScreen.route)
    }
}
