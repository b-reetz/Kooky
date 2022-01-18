package com.kooky.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

data class ToolbarProps(
    val title: String,
    val actions: @Composable RowScope.() -> Unit = {}
)

val LocalToolbar = compositionLocalOf { mutableStateOf(ToolbarProps("Kooky")) }
val toolbarTitle @Composable get() = LocalToolbar.current.value.title
val toolbarActions @Composable get() = LocalToolbar.current.value.actions