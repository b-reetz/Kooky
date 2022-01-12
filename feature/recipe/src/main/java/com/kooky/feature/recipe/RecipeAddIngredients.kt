package com.kooky.feature.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
@Preview
fun RecipeAddIngredients() {

    val viewModel: AddMyIngredientsViewModel = viewModel()
    val state by viewModel.stateFlow.collectAsState()

    Scaffold {
        Column(
            modifier = maxSize.padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = spacedBy(8.dp)
        ) {
            Row(modifier = maxWidth, horizontalArrangement = spacedBy(8.dp)) {
                Text("Ingredient", modifier = Mod.weight(1f))
                Text("Qty", modifier = Mod.weight(1f))
            }

            state.ingredients.forEach {
                IngredientRow(
                    ingredient = it.name,
                    quantity = it.quantity
                )
            }

            NewIngredientRow(onAdd = viewModel::onIngredientAdded)
        }
    }
}

@Composable
private fun IngredientRow(ingredient: String, quantity: Int) {

    Row(modifier = maxWidth, horizontalArrangement = spacedBy(8.dp)) {
        OutlinedTextField(
            value = ingredient,
            enabled = false,
            onValueChange = { },
            modifier = Mod.weight(2f, fill = false)
        )
        OutlinedTextField(
            value = quantity.toString(),
            enabled = false,
            onValueChange = { },
            modifier = Mod.weight(1f, fill = false)
        )
    }
}

@Composable
private fun NewIngredientRow(onAdd: (String, Int) -> Unit) {
    var ingredient by remember { mutableStateOf("") }
    var quantity: Int? by remember { mutableStateOf(null) }

    Row(modifier = maxWidth, horizontalArrangement = spacedBy(8.dp)) {
        OutlinedTextField(
            value = ingredient,
            onValueChange = { ingredient = it },
            modifier = Mod.weight(2f)
        )
        OutlinedTextField(
            value = quantity?.toString() ?: "",
            onValueChange = { quantity = it.toIntOrNull() },
            modifier = Mod.weight(1f)
        )
        Button(
            onClick = { onAdd(ingredient, quantity!!) },
            enabled = ingredient.isNotNullOrBlank() && quantity != null,
            modifier = Mod.weight(1f)
        ) {
            Text("Add", modifier = Mod.padding(4.dp))
        }
    }
}

fun String?.isNotNullOrBlank(): Boolean = this != null && this.isNotBlank()