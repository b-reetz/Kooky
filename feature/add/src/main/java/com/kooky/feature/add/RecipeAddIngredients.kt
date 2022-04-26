@file:OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)

package com.kooky.feature.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.DismissDirection.StartToEnd
import androidx.compose.material.DismissValue.Default
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kooky.navigation.LocalToolbar
import com.kooky.navigation.ToolbarProps
import com.kooky.utilities.maxSize
import com.kooky.utilities.maxWidth
import com.kooky.utilities.pluralResource
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize
class NewIngredientAddKey(val ingredients: List<IngredientsTest>) : NavigationKey.WithResult<List<IngredientsTest>>

@Composable
@ExperimentalComposableDestination
@NavigationDestination(NewIngredientAddKey::class)
fun AddIngredientsScreen() {
    Scaffold {
        Column(
            modifier = maxSize.padding(vertical = 16.dp),
            verticalArrangement = spacedBy(16.dp)
        ) {
            IngredientTitles()
            IngredientsList()
        }
    }
}

@Composable
private fun IngredientTitles() {
    Row(
        modifier = maxWidth.padding(horizontal = 16.dp),
        horizontalArrangement = spacedBy(8.dp)
    ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text("Quantity")
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text("Unit")
        }
        Box(modifier = Modifier.weight(2f), contentAlignment = Alignment.Center) {
            Text("Ingredient")
        }
    }
}

@Composable
private fun IngredientsList() {
    val viewModel: AddIngredientsViewModel = viewModel()
    val state by viewModel.stateFlow.collectAsState()

    LocalToolbar.current.value = ToolbarProps("Add Ingredients") {
        TextButton(onClick = viewModel::save) {
            Text("Save", color = Color.White)
        }
    }

    LazyColumn(modifier = maxWidth) {
        items(state.ingredients, key = { it.id }) {
            SwipeableIngredientRow(
                ingredient = it,
                onDismiss = { viewModel.onIngredientDismissed(it) },
                onChange = viewModel::onIngredientUpdated,
                modifier = Modifier.animateItemPlacement(),
                enabled = state.ingredients.last() != it
            )
        }
    }
}

@Composable
private fun SwipeableIngredientRow(
    ingredient: IngredientsTest,
    onDismiss: () -> Unit,
    onChange: (IngredientsTest) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val state = rememberDismissState()
    val visibleState = remember { MutableTransitionState(true) }

    key(state.currentValue, visibleState.currentState) {
        if (!visibleState.currentState && visibleState.isIdle) onDismiss()
        visibleState.targetState = state.currentValue == Default
    }

    AnimatedVisibility(
        visibleState = visibleState,
        modifier = modifier,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        SwipeToDismiss(
            state = state,
            background = { DismissBackground(state.dismissDirection) },
            directions = if (enabled) DismissDirection.values().toSet() else setOf()
        ) {
            IngredientRow(
                ingredient = ingredient,
                onUpdated = onChange
            )
        }
    }
}

@Composable
private fun DismissBackground(dismissDirection: DismissDirection?) {
    val align = if (dismissDirection == StartToEnd) CenterStart else CenterEnd

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(align)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun IngredientRow(
    ingredient: IngredientsTest,
    onUpdated: (IngredientsTest) -> Unit,
) {
    Box(modifier = Modifier.background(Color.White)) {
        Row(
            modifier = maxWidth.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = ingredient.quantity.orEmpty(),
                onValueChange = { onUpdated(ingredient.copy(quantity = it)) },
                modifier = Modifier.weight(1f),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            )
            MeasureSelector(
                currentMeasure = ingredient.measure,
                modifier = Modifier.weight(1f),
            ) {
                onUpdated(ingredient.copy(measure = it))
            }
            OutlinedTextField(
                value = ingredient.name.orEmpty(),
                onValueChange = { onUpdated(ingredient.copy(name = it)) },
                modifier = Modifier.weight(2f),
                singleLine = true
            )
        }
    }
}

@Composable
fun MeasureSelector(
    currentMeasure: Measure,
    modifier: Modifier = Modifier,
    onUpdated: (Measure) -> Unit
) {
    //TODO make this auto-complete field
    var isExpanded by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(percent = 50),
        color = Color.LightGray.copy(alpha = 0.5f),
        modifier = modifier.clickable { isExpanded = true }
    ) {
        Text(
            text = currentMeasure.abbreviation,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            Column {

                Measure.values().forEach {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            isExpanded = false
                            onUpdated(it)
                        }
                    ) {
                        Text(it.displayName)
                    }
                }
            }
        }
    }
}