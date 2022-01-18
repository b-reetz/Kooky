package com.kooky.feature.add

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import com.kooky.infrastructure.viewmodel.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationHandle
import dev.enro.core.NavigationKey
import dev.enro.core.close
import dev.enro.core.compose.dialog.DialogDestination
import dev.enro.core.compose.navigationHandle
import dev.enro.viewmodel.navigationHandle
import dev.enro.core.result.closeWithResult
import dev.enro.core.result.registerForNavigationResult
import kotlinx.parcelize.Parcelize
import java.util.*
import javax.inject.Inject

enum class Measure(val abbreviation: String, val displayName: String) {
    CUP("c", "cup"),
    TEASPOON("tsp", "teaspoon"),
    TABLESPOON("Tbsp", "tablespoon"),
    PINCH("pinch", "pinch")
}

data class IngredientsTest(
    val name: String? = null,
    val quantity: String? = null,
    val measure: Measure = Measure.CUP,
    val id: String = UUID.randomUUID().toString()
) {
    fun getFields() = Triple(name, quantity, measure)
}

data class AddMyIngredientsState(
    val ingredients: List<IngredientsTest> = listOf(IngredientsTest())
)

@HiltViewModel
class AddIngredientsViewModel @Inject constructor() :
    StateViewModel<AddMyIngredientsState>(AddMyIngredientsState()) {

    private val nav: NavigationHandle by navigationHandle<NewIngredientAddKey> {
        onCloseRequested {
            if (state.ingredients.isNotEmpty()) {
                confirmationDialog.open(confirmationDialogKey)
            } else close()
        }
    }

    private val confirmationDialogKey = ConfirmationDialogKey(
        title = "You have unsaved ingredients",
        message = "Are you sure you'd like to discard your changes?"
    )

    private val confirmationDialog by registerForNavigationResult<Boolean>(nav) {
        if (it) nav.close()
    }

    fun onIngredientUpdated(ingredient: IngredientsTest) {
        val indexOfExisting = state.ingredients.indexOfFirst { it.id == ingredient.id }
        val newList = state.ingredients.toMutableList().apply { set(indexOfExisting, ingredient) }
        if (newList.last().quantity != null && newList.last().name.isNotNullOrBlank()) newList.add(
            IngredientsTest()
        )
        updateState { copy(ingredients = newList) }
    }

    fun onIngredientDismissed(ingredient: IngredientsTest) {
        val indexOfExisting = state.ingredients.indexOfFirst { it.id == ingredient.id }
        val newList = state.ingredients.toMutableList().apply { removeAt(indexOfExisting) }
        updateState { copy(ingredients = newList) }
    }
}

@Parcelize
data class ConfirmationDialogKey(
    val title: String,
    val message: String
) : NavigationKey.WithResult<Boolean>

@ExperimentalComposableDestination
@NavigationDestination(ConfirmationDialogKey::class)
@Composable
fun DialogDestination.DoDialog() {
    val nav = navigationHandle<ConfirmationDialogKey>()

    AlertDialog(
        confirmButton = {
            TextButton(onClick = { nav.closeWithResult(true) }) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = { nav.closeWithResult(false) }) {
                Text(text = "Cancel", color = MaterialTheme.colors.error)
            }
        },
        onDismissRequest = { nav.closeWithResult(false) },
        title = { Text(nav.key.title) },
        text = { Text(nav.key.message) }
    )
}