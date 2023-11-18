package com.example.recipefinder.presentation.feature_recipe_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipefinder.R
import com.example.recipefinder.presentation.util.getIngredients
import com.example.recipefinder.presentation.util.getVideoId
import com.example.recipefinder.util.Screen

@Composable
fun RecipeDetailsScreen(
    viewModel: RecipeDetailsViewModel = hiltViewModel(),
    id: String?,
    navController: NavController
) {

    LaunchedEffect(key1 = true){
        viewModel.getRecipeById(id)
    }
    val state = viewModel.state.value
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {

            if (state.isLoading) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.error?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error,
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            }
            state.mealClient?.let {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = state.mealClient[0].mealName ?: stringResource(id = R.string.no_meal_name),
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AsyncImage(
                        model = state.mealClient[0].mealImage,
                        contentDescription = state.mealClient[0].mealName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.ingredients),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )//Ingredient Title
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = getIngredients(state.mealClient[0]),
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )//Ingredient Text
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.instructions),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = state.mealClient[0].mealInstruction ?: "",
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                state.mealClient[0].mealYoutube?.let { videoAddress ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Button(onClick = {
                            val videoId = getVideoId(state.mealClient[0].mealYoutube!!)
                            navController.navigate(
                                "${Screen.YoutubePlayerScreen.route}/$videoId"
                            )
                        }) {
                            Text(
                                text = stringResource(id = R.string.open_video)
                            )
                        }
                    }

                }
                
            }//state.mealClient
        }//item
    }//LazyColumn
}