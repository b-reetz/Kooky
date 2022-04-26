package com.kooky

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.kooky.navigation.RecipeListKey
import com.kooky.navigation.ShoppingListKey
import com.kooky.navigation.toolbarActions
import com.kooky.navigation.toolbarTitle
import dagger.hilt.android.AndroidEntryPoint
import dev.enro.core.NavigationInstruction
import dev.enro.core.compose.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideWindowInsets {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {

    val composableManager = localComposableManager

    val homeController = rememberEnroContainerController(
        initialState = listOf(NavigationInstruction.Forward(RecipeListKey())),
        emptyBehavior = EmptyBehavior.CloseParent
    )

    val shoppingListController = rememberEnroContainerController(
        accept = { false },
        initialState = listOf(NavigationInstruction.Replace(ShoppingListKey())),
        emptyBehavior = EmptyBehavior.Action {
            composableManager.setActiveContainer(homeController)
            true
        }
    )

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(toolbarTitle) },
                    actions = toolbarActions //FIXME why does this have the wrong content colour?
                )
            },
            bottomBar = {
                BottomNavigation {
                    BottomNavigationItem(
                        selected = composableManager.activeContainer == homeController,
                        onClick = { composableManager.setActiveContainer(homeController) },
                        label = { Text("Home") },
                        alwaysShowLabel = false,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null
                            )
                        }
                    )
                    BottomNavigationItem(
                        selected = composableManager.activeContainer == shoppingListController,
                        onClick = { composableManager.setActiveContainer(shoppingListController) },
                        label = { Text("Shopping List") },
                        alwaysShowLabel = false,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        ) {
            Crossfade(
                targetState = composableManager.activeContainer,
                animationSpec = tween(225)
            ) {
                if (it == null) return@Crossfade
                EnroContainer(controller = it)
            }
        }
    }
}