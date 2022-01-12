package com.kooky.feature.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kooky.navigation.LocalToolbar
import com.kooky.navigation.ToolbarProps
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize
class IngredientAddKey : NavigationKey

@Composable
@ExperimentalComposableDestination
@NavigationDestination(IngredientAddKey::class)
fun IngredientAdd() {
    val viewModel: AddIngredientsViewModel = viewModel()
    val state by viewModel.stateFlow.collectAsState()

    LocalToolbar.current.value = ToolbarProps(
        title = "Ingredients",
        actions = {
            IconButton(onClick = { viewModel.saveSelected() }) {
                Icon(Icons.Default.Done, null)
            }
        }
    )

    val placeholder = "Ingredient name.."

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var expanded by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = Modifier.onKeyboardEnter(viewModel::onKeyboardDone),
            value = state.ingredientText,
            onValueChange = viewModel::onTextValueChanged,
            singleLine = true,
            placeholder = { Text(placeholder) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { viewModel.onKeyboardDone() }
            )
        )


//        if (state.existingIngredients.isNotEmpty() && state.ingredientText.isNotEmpty()) {
            DropdownMenu(
                expanded = state.ingredientText.isNotEmpty() && state.existingIngredients.isNotEmpty(),
                onDismissRequest = { }
            ) {
                state.existingIngredients.forEach {
                    DropdownMenuItem(onClick = { /*TODO*/ }) {
                        Text(it)
                    }
                }
            }
//        }
    }
}

@Preview
@Composable
fun IngredientsPreview() {
    IngredientAdd()
}

fun Modifier.onKeyboardEnter(block: () -> Unit) = onKeyEvent {
    if (it.nativeKeyEvent.keyCode == NativeKeyEvent.KEYCODE_ENTER) {
        block()
    }
    false
}