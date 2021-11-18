package com.kooky.feature.recipe

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
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
import dev.enro.core.close
import dev.enro.core.compose.configure
import dev.enro.core.compose.navigationHandle
import dev.enro.core.compose.registerForNavigationResult
import dev.enro.core.requestClose
import kotlinx.parcelize.Parcelize

@Parcelize
class IngredientAddKey : NavigationKey

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
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

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
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
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(state.newIngredients, key = { it }) {
                    Text(it, modifier = Modifier
                        .background(Color.White)
                        .animateItemPlacement()
                    )
                }
            }
        }
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