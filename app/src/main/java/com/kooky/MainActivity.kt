package com.kooky

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.kooky.feature.recipe.IngredientAdd
import com.kooky.feature.recipe.IngredientAddKey
import com.kooky.navigation.RecipeListKey
import com.kooky.navigation.toolbarActions
import com.kooky.navigation.toolbarTitle
import dagger.hilt.android.AndroidEntryPoint
import dev.enro.core.NavigationInstruction
import dev.enro.core.compose.EmptyBehavior
import dev.enro.core.compose.EnroContainer
import dev.enro.core.compose.rememberEnroContainerController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    MaterialTheme {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(toolbarTitle) },
                actions = toolbarActions //FIXME why does this have the wrong content colour?
            )
        }) {
            val controller = rememberEnroContainerController(
                initialState = listOf(NavigationInstruction.Forward(IngredientAddKey())),
                emptyBehavior = EmptyBehavior.CloseParent
            )
            EnroContainer(controller = controller)
        }
    }
}