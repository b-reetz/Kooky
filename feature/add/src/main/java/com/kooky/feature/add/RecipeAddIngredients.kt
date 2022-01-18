@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)

package com.kooky.feature.add

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
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
import com.kooky.utilities.maxSize
import com.kooky.utilities.maxWidth
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize
class NewIngredientAddKey : NavigationKey

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
        Text("Ingredient", modifier = Modifier.weight(2f))
        Text("Qty", modifier = Modifier.weight(1f))
        Text("Unit", modifier = Modifier.weight(1f))
    }
}

@Composable
private fun IngredientsList() {
    val viewModel: AddIngredientsViewModel = viewModel()
    val state by viewModel.stateFlow.collectAsState()

    LazyColumn(modifier = maxWidth) {
        items(state.ingredients, key = { it.id }) {
            SwipeableIngredientRow(
                ingredient = it,
                onDismiss = { viewModel.onIngredientDismissed(it) },
                onChange = viewModel::onIngredientUpdated,
                modifier = Modifier.animateItemPlacement(),
                enabled = state.ingredients.size > 1
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
    val (name, quantity, measure) = ingredient.getFields()

    Box(modifier = Modifier.background(Color.White)) {
        Row(
            modifier = maxWidth.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = name.orEmpty(),
                onValueChange = { onUpdated(ingredient.copy(name = it)) },
                modifier = Modifier.weight(2f)
            )
            OutlinedTextField(
                value = quantity.orEmpty(),
                onValueChange = { onUpdated(ingredient.copy(quantity = it)) },
                modifier = Modifier.weight(1f)
            )
            MeasureSelector(measure, Modifier.weight(1f)) { onUpdated(ingredient.copy(measure = it)) }
        }
    }
}

@Composable
fun MeasureSelector(
    currentMeasure: Measure,
    modifier: Modifier = Modifier,
    onUpdated: (Measure) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.LightGray,
        modifier = modifier.clickable { isExpanded = true }
    ) {
        Text(
            text = currentMeasure.displayName,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.padding(2.dp)
        )
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            Column {
                Measure.values().forEach {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isExpanded = false
                            onUpdated(it)
                        }
                    ) {
                        Text(it.displayName, modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}

fun String?.isNotNullOrBlank(): Boolean = this != null && this.isNotBlank()