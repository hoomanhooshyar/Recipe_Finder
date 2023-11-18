package com.example.recipefinder.presentation.feature_recipefinder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipefinder.R
import com.example.recipefinder.util.Screen

@Composable
fun RecipeFinderScreen(
    viewModel: RecipeViewModel = hiltViewModel(),
    navController: NavController
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 8.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
        ){
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        SearchComponent(modifier = Modifier.fillMaxWidth()){ searchValue ->
            viewModel.searchRecipe(searchValue)
        }
        viewModel.state.error?.let {
            Box(modifier = Modifier.fillMaxWidth()){
                Text(text = viewModel.state.error!!)
            }
        }
        viewModel.state.mealClients?.let { mealList ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ){
                itemsIndexed(mealList){ index,meal ->
                    MealItem(
                        meal = meal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)

                    ) {
                        navController.navigate(
                            "${Screen.RecipeDetailsScreen.route}/${meal.idMeal}"
                        )
                    }
                }
            }
        }
    }

}