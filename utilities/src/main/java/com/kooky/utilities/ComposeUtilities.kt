package com.kooky.utilities

import androidx.annotation.PluralsRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

typealias Mod = Modifier

val maxWidth = Mod.maxWidth
val Modifier.maxWidth: Modifier
    get() = this.fillMaxWidth()

val maxHeight = Mod.maxHeight
val Modifier.maxHeight: Modifier
    get() = this.fillMaxHeight()

val maxSize = Mod.maxSize
val Modifier.maxSize: Modifier
    get() = this.fillMaxSize()


@OptIn(ExperimentalMaterialApi::class)
@Composable
public fun SwipeableRow(
    modifier: Modifier = Modifier,
    state: DismissState = rememberDismissState(),
    backgroundColor: Color = MaterialTheme.colors.surface,
    enabled: Boolean = true,
    onDismiss: () -> Unit,
    background: @Composable RowScope.() -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val visibleState = remember { MutableTransitionState(true) }

    key(state.currentValue, visibleState.currentState) {
        if (!visibleState.currentState && visibleState.isIdle) onDismiss()
        visibleState.targetState = state.currentValue == DismissValue.Default
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        SwipeToDismiss(
            state = state,
            background = background,
            directions = if (enabled) DismissDirection.values().toSet() else setOf()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .then(modifier)) {
                content()
            }
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        modifier = modifier,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        SwipeToDismiss(
            state = state,
            background = {  },
            directions = if (enabled) DismissDirection.values().toSet() else setOf()
        ) {
            content()
        }
    }
}

@Composable
private fun DismissBackground(
    dismissDirection: DismissDirection,
    icon: @Composable () -> Unit
) {
    val align = if (dismissDirection == DismissDirection.StartToEnd) Alignment.CenterStart else Alignment.CenterEnd

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.error)
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.onError) {
            Box(modifier = Modifier.align(align)) {
                icon()
            }
        }
    }
}

@Composable
fun pluralResource(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg formatArgs: Any? = emptyArray()
): String {
    return LocalContext.current.resources
        .getQuantityString(resId, quantity, *formatArgs)
}