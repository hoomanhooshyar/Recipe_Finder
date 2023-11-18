package com.example.recipefinder.presentation.feature_recipefinder

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipefinder.R
import com.example.recipefinder.domain.model.MealClient
import com.example.recipefinder.presentation.util.getIngredients

@Composable
fun MealItem(
    modifier:Modifier = Modifier,
    meal:MealClient,
    onCLick:() -> Unit
) {
    var expanded by remember{ mutableStateOf(false) }
    Card(
        modifier = modifier.clickable { onCLick() },
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(
            Color.White
        )
    ) {
        Column {
            meal.mealImage?.let { 
                AsyncImage(
                    model =meal.mealImage,
                    contentDescription = meal.mealImage,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(4.dp))
                )//AsyncImage
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = meal.mealName ?: stringResource(id = R.string.no_meal_name),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 8.dp)
            )//Meal name Text
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.ingredients),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )//Ingredient Title
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = getIngredients(meal),
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 8.dp)
            )//Ingredient Text
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = expanded) {
                Column {
                    Text(
                        text = stringResource(id = R.string.instructions),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text(
                        text = meal.mealInstruction ?: "",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }//Column
            }//AnimatedVisibility
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .clickable {
                        expanded = !expanded
                    }
            ) {
                Icon(
                    imageVector =if(expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "arrow",
                    tint = Color.Black,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }//Column
        }//Main Column
    }//Card
}