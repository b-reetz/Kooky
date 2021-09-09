package com.kooky.feature.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.kooky.navigation.RecipeListKey
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.compose.navigationHandle
import dev.enro.core.forward

@Composable
@ExperimentalComposableDestination
@NavigationDestination(RecipeListKey::class)
fun RecipeList() {
    val navigation = navigationHandle()
    val recipes = (1..20).map {
        Triple("My name", "30 - 40 minutes", "6 servings")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(Modifier) }
        items(recipes) {
            Box(modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable { navigation.forward(RecipeAddKey()) }
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
        item { Spacer(Modifier) }
    }
}