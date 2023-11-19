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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipefinder.R
import com.example.recipefinder.core.internet_connection_observer.Status
import com.example.recipefinder.util.Screen

@Composable
fun RecipeFinderScreen(
    viewModel: RecipeViewModel = hiltViewModel(),
    navController: NavController
) {
    val status = viewModel.status.collectAsState()

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
        when(status.value){
            Status.Available ->{
                internetAvailable(viewModel = viewModel, navController = navController)
            }
            Status.Lost ->{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = stringResource(id = R.string.internet_lost),
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }

            }
            Status.Losing ->{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = stringResource(id = R.string.internet_losing),
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            }
            Status.Unavailable ->{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = stringResource(id = R.string.internet_unavailable),
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            }
        }

    }

}

@Composable
fun internetAvailable(viewModel: RecipeViewModel,navController: NavController) {
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