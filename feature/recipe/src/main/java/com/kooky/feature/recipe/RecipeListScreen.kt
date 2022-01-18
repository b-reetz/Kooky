package com.kooky.feature.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.kooky.navigation.LocalToolbar
import com.kooky.navigation.RecipeAddKey
import com.kooky.navigation.RecipeListKey
import com.kooky.navigation.ToolbarProps
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.compose.navigationHandle
import dev.enro.core.forward

@Composable
@ExperimentalComposableDestination
@NavigationDestination(RecipeListKey::class)
fun RecipeListScreen() { //wrap in scaffold with top app bar
    var dropdownVisible by remember { mutableStateOf(false) }
    val navigation = navigationHandle()

    LocalToolbar.current.value = ToolbarProps("Recipe List") {
        IconButton(onClick = { dropdownVisible = true }) {
            Icon(Icons.Default.Add, null)
        }

        DropdownMenu(
            expanded = dropdownVisible,
            onDismissRequest = { dropdownVisible = false }
        ) {
            DropdownMenuItem(onClick = { navigation.forward(RecipeAddKey()) }) {
                Text("Add Recipe")
            }
        }
    }

//    Scaffold(modifier = Modifier.fillMaxSize()) {
        RecipeList()
//    }
}

@Composable
fun RecipeList() {
    val navigation = navigationHandle()

    val recipes = (1..20).map {
        Triple("My name", "30 - 40 minutes", "$it servings")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(recipes, key = { it.third }) {
            Box(modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable { navigation.forward(RecipeDetailKey()) }
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.eggplant_recipe),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Column(modifier = Modifier.align(Alignment.BottomStart)) {
                    Text(it.first)
                    Text(it.second)
                }
            }
        }
    }
}