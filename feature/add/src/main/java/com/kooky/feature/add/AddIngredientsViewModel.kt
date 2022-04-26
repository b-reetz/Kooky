package com.kooky.feature.add

import android.os.Parcelable
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import com.kooky.utilities.doIf
import com.kooky.viewmodel.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.TypedNavigationHandle
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
    NONE("-", "-"),
    CUP("c", "cup"),
    TEASPOON("tsp", "teaspoon"),
    TABLESPOON("Tbsp", "tablespoon"),
    PINCH("pinch", "pinch")
}

@Parcelize
data class IngredientsTest(
    val name: String? = null,
    val quantity: String? = null,
    val measure: Measure = Measure.NONE,
    val id: String = UUID.randomUUID().toString()
) : Parcelable {
    val isEmpty: Boolean
        get() = name.isNullOrBlank() && quantity.isNullOrBlank() && measure == Measure.NONE
}

data class AddMyIngredientsState(
    val ingredients: List<IngredientsTest> = listOf(IngredientsTest())
)

fun List<IngredientsTest>.withoutEmpty() = filterNot { it.isEmpty }

@HiltViewModel
class AddIngredientsViewModel @Inject constructor(): StateViewModel<AddMyIngredientsState>() {
    private val nav: TypedNavigationHandle<NewIngredientAddKey> by navigationHandle {
        onCloseRequested {
            val hasChanged = state.ingredients.withoutEmpty() != nav.key.ingredients

            if (hasChanged) confirmationDialog.open(confirmationDialogKey) else close()
        }
    }

    override val config = configure(
        initialState = AddMyIngredientsState(
            ingredients = nav.key.ingredients
                .doIf(
                    predicate = { lastOrNull()?.isEmpty == false },
                    block = { plus(IngredientsTest()) }
                )
                .ifEmpty { listOf(IngredientsTest()) }
        )
    )

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
        if (!newList.last().isEmpty) newList.add(IngredientsTest())
        updateState { copy(ingredients = newList) }
    }

    fun onIngredientDismissed(ingredient: IngredientsTest) {
        val indexOfExisting = state.ingredients.indexOfFirst { it.id == ingredient.id }
        val newList = state.ingredients.toMutableList().apply { removeAt(indexOfExisting) }
        updateState { copy(ingredients = newList) }
    }

    fun onSaveClicked() {
        nav.closeWithResult(state.ingredients.withoutEmpty())
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