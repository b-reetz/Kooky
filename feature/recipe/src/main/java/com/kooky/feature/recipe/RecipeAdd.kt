package com.kooky.feature.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kooky.navigation.LocalToolbar
import com.kooky.navigation.ToolbarProps
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.close
import dev.enro.core.compose.navigationHandle
import dev.enro.core.forward
import kotlinx.parcelize.Parcelize

@Parcelize class RecipeAddKey : NavigationKey

@OptIn(ExperimentalMaterialApi::class)
@Composable
@ExperimentalComposableDestination
@NavigationDestination(RecipeAddKey::class)
fun RecipeAdd() {
    val navigation = navigationHandle()

    LocalToolbar.current.value = ToolbarProps("Add Recipe") {
        TextButton(onClick = { /*TODO*/ }) {
            Text("Save", color = Color.White)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var first by remember { mutableStateOf("") }
        var second by remember { mutableStateOf("") }
        var third by remember { mutableStateOf("") }

        Card(elevation = 4.dp, shape = RoundedCornerShape(4.dp), onClick = { navigation.forward(IngredientAddKey()) }) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Ingredients", fontSize = 18.sp)
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
            }
        }

        BasicTextField(value = first, onValueChange = { first = it })
        TextField(
            value = second,
            onValueChange = { second = it },
            placeholder = { Text("Recipe name placeholder") },
            label = { Text("Recipe name label") })
        OutlinedTextField(
            value = third,
            onValueChange = { third = it },
            label = { Text("Recipe name label") },
            placeholder = { Text("Recipe name placeholder") })
    }
}

@Composable
fun BasicAppBar(title: String) {
    val navigation = navigationHandle()
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navigation.close() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}