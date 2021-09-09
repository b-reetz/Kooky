package com.kooky

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.kooky.navigation.RecipeListKey
import dev.enro.core.NavigationInstruction
import dev.enro.core.compose.EmptyBehavior
import dev.enro.core.compose.EnroContainer
import dev.enro.core.compose.rememberEnroContainerController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val controller = rememberEnroContainerController(
                initialState = listOf(NavigationInstruction.Forward(RecipeListKey())),
                emptyBehavior = EmptyBehavior.CloseParent
            )
            EnroContainer(controller = controller)
        }
    }
}