package com.kooky.feature.recipe

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import com.kooky.navigation.LocalToolbar
import com.kooky.navigation.ToolbarProps
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize class IngredientAddKey : NavigationKey

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


}