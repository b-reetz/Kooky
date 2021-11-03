package com.kooky.feature.recipe

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kooky.navigation.LocalToolbar
import com.kooky.navigation.ToolbarProps
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize
class IngredientAddKey : NavigationKey

@OptIn(ExperimentalMaterialApi::class)
@Composable
@ExperimentalComposableDestination
@NavigationDestination(IngredientAddKey::class)
fun IngredientAdd() {
    LocalToolbar.current.value = ToolbarProps(
        title = "Ingredients",
        actions = {
            TextButton(onClick = { }) {
                Text("Save")
            }
        }
    )

    val placeholder = "Ingredient name.."
    var text by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    Surface {
        ExposedDropdownMenuBox(
            modifier = Modifier.padding(32.dp),
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text(placeholder) }
            )


            val filteredItems = dropDownItems.filter { it.first.contains(text, ignoreCase = true) }

            if (filteredItems.isNotEmpty()) {
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    filteredItems.forEach {
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                text = it.first
                            }) {
                            Text(it.first)
                        }
                    }
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