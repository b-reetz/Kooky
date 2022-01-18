package com.kooky

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.kooky.navigation.RecipeListKey
import com.kooky.navigation.toolbarActions
import com.kooky.navigation.toolbarTitle
import dagger.hilt.android.AndroidEntryPoint
import dev.enro.core.EmptyBehavior
import dev.enro.core.NavigationInstruction
import dev.enro.core.compose.EnroContainer
import dev.enro.core.compose.rememberEnroContainerController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ProvideWindowInsets {
                MainContent()
            }
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
                initialBackstack = listOf(NavigationInstruction.Forward(RecipeListKey())),
                emptyBehavior = EmptyBehavior.CloseParent
            )
            EnroContainer(controller = controller)
        }
    }
}