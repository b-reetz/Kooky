package com.kooky.feature.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kooky.navigation.LocalToolbar
import com.kooky.navigation.ToolbarProps
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.compose.navigationHandle
import kotlinx.parcelize.Parcelize

@Parcelize
class IngredientAddKey : NavigationKey

@OptIn(ExperimentalMaterialApi::class)
@Composable
@ExperimentalComposableDestination
@NavigationDestination(IngredientAddKey::class)
fun IngredientAdd() {
    val viewModel: AddIngredientsViewModel = viewModel()
    var ingredientList by remember { mutableStateOf(emptyList<String>())}

    LocalToolbar.current.value = ToolbarProps(
        title = "Ingredients",
        actions = {
            IconButton(onClick = {
                viewModel.saveIngredients(ingredientList)
            }) {
                Icon(Icons.Default.Done, null)
            }
        }
    )

    val placeholder = "Ingredient name.."
    var text by remember { mutableStateOf("") }


    var expanded by remember { mutableStateOf(false) }

    Surface {
        Column(
            modifier = Modifier.padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text(placeholder) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        ingredientList = ingredientList.plus(text)
                        text = ""
                    }
                )
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(ingredientList) {
                    Text(it)
                }
            }
        }

    }
}

val dropDownItems = listOf(
    Pair("My first item", 1),
    Pair("My second item", 2),
    Pair("My third item", 3),
    Pair("My fourth item", 4),
    Pair("My fifth item", 5),
    Pair("My sixth item", 6)
)

@Preview
@Composable
fun IngredientsPreview() {
    IngredientAdd()
}