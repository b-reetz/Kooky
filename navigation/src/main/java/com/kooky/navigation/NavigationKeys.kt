package com.kooky.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize public class RecipeListKey: NavigationKey

@Composable
private fun MyTHing() {

}

data class ToolbarProps(
    val title: String,
    val actions: @Composable RowScope.() -> Unit = {}
)

val LocalToolbar = compositionLocalOf { mutableStateOf(ToolbarProps("Kooky")) }
val toolbarTitle @Composable get() = LocalToolbar.current.value.title
val toolbarActions @Composable get() = LocalToolbar.current.value.actions