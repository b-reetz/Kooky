package com.kooky.feature.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize
class RecipeDetailKey : NavigationKey

@Composable
@ExperimentalComposableDestination
@NavigationDestination(RecipeDetailKey::class)
fun RecipeDetailScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.eggplant_recipe),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, null)
                }

                Surface(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .clip(CircleShape),
                    shape = CircleShape
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.FavoriteBorder, null)
                    }
                }
            }
        }
    }


}