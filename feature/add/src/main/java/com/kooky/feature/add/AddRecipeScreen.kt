@file:OptIn(ExperimentalMaterialApi::class)

package com.kooky.feature.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kooky.navigation.RecipeAddKey
import com.kooky.navigation.ToolbarProps
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination

@Composable
@ExperimentalComposableDestination
@NavigationDestination(RecipeAddKey::class)
fun RecipeAddScreen() {
    val viewModel: AddRecipeViewModel = viewModel()

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //TODO make these backed by viewModel
            var first by remember { mutableStateOf("") }
            var second by remember { mutableStateOf("") }

            RecipeComponentCard(title = "Ingredients", onClick = viewModel::onIngredientsSelected)
            RecipeComponentCard(title = "Directions", onClick = { })

            TextField(
                value = first,
                onValueChange = { first = it },
                placeholder = { Text("Recipe name placeholder") },
                label = { Text("Recipe name label") })
            OutlinedTextField(
                value = second,
                onValueChange = { second = it },
                label = { Text("Recipe name label") },
                placeholder = { Text("Recipe name placeholder") })
        }
    }
}

@Composable
private fun RecipeComponentCard(title: String, onClick: () -> Unit) {
    Card(elevation = 4.dp, shape = RoundedCornerShape(4.dp), onClick = onClick) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 18.sp)
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Composable
private fun RecipeTag(tag: String) {
    Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFFFDF0D9)) {
        LazyRow(verticalAlignment = Alignment.CenterVertically) {
            item { Text(tag) }
        }
    }
}

@Composable
@Preview
private fun RecipeRow() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        RecipeTag(tag = "Vegan")
        RecipeTag(tag = "Cheese")
    }
}