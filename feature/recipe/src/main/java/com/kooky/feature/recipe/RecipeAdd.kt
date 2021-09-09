package com.kooky.feature.recipe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize class RecipeAddKey: NavigationKey

@Composable
@ExperimentalComposableDestination
@NavigationDestination(RecipeAddKey::class)
fun RecipeAdd() {
    Text(text = "Hi", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
}