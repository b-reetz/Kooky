package com.kooky.feature.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize data class AddDescriptionKey(val description: List<String>)
    : NavigationKey.WithResult<List<String>>

@Composable
@ExperimentalComposableDestination
@NavigationDestination(AddDescriptionKey::class)
fun AddDescriptionScreen() {
    MaterialTheme {
        AddDescription()
    }
}

@Composable
fun AddDescription() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
//        OutlinedTextField
    }
}