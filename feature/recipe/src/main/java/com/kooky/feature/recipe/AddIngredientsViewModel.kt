package com.kooky.feature.recipe

import android.widget.TextView
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.*
import dev.enro.core.compose.dialog.DialogDestination
import dev.enro.core.compose.navigationHandle
import dev.enro.core.compose.registerForNavigationResult
import dev.enro.core.result.closeWithResult
import dev.enro.core.result.registerForNavigationResult
import dev.enro.viewmodel.navigationHandle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

data class AddIngredientsState(
    val ingredientText: String = "",
    val newIngredients: List<String> = emptyList(),
    val existingIngredients: List<String> = emptyList()
)

@HiltViewModel
class AddIngredientsViewModel @Inject constructor(
    private val interactor: IngredientsInteractor
) : StateViewModel<AddIngredientsState>(AddIngredientsState()) {

    private val confirmationDialogKey = ConfirmationDialogKey(
        title = "You have unsaved ingredients",
        message = "Are you sure you'd like to discard your changes?"
    )

    private val nav: NavigationHandle by navigationHandle<IngredientAddKey> {
        onCloseRequested {
            if (state.newIngredients.isNotEmpty()) {
                confirmationDialog.open(confirmationDialogKey)
            } else close()
        }
    }

    private val confirmationDialog by registerForNavigationResult<Boolean>(nav) {
        if (it) nav.close()
    }

    fun onKeyboardDone() {
        updateState { copy(
            ingredientText = "",
            newIngredients = newIngredients.plus(state.ingredientText).sorted()
        ) }
    }

    fun onTextValueChanged(text: String) {
        updateState { copy(ingredientText = text) }
    }

    fun saveSelected() {
        interactor.saveIngredients(state.newIngredients)
        nav.close()
    }

    init {
        //TODO should this launch in scope here, or in StateViewModel
            interactor.getAllIngredientNames()
                .onEachUpdateState { copy(existingIngredients = it) }
//        }
    }
}

@Parcelize data class ConfirmationDialogKey(
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