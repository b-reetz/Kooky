package com.kooky.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import dev.enro.core.compose.navigationHandle
import dev.enro.core.requestClose

data class ToolbarProps(
    val title: String,
    val actions: @Composable RowScope.() -> Unit = {}
)

val LocalToolbar = compositionLocalOf { mutableStateOf(ToolbarProps("Kooky")) }
val toolbarTitle @Composable get() = LocalToolbar.current.value.title
val toolbarActions @Composable get() = LocalToolbar.current.value.actions

@Composable
fun KookyAppBar(
    toolbarProps: ProvidableCompositionLocal<MutableState<ToolbarProps>> = LocalToolbar,
    navigateBackEnabled: Boolean = true
) {
    val navHandle = navigationHandle()
    val navigateBack: @Composable (() -> Unit)? = if (navigateBackEnabled) {
        {
            IconButton(onClick = { navHandle.requestClose() }) {
                Icon(Icons.Default.ArrowBack, null)
            }
        }
    } else null

    androidx.compose.material.TopAppBar(
        title = { Text(toolbarProps.current.value.title) },
        actions = toolbarProps.current.value.actions,
        navigationIcon = navigateBack
    )
}