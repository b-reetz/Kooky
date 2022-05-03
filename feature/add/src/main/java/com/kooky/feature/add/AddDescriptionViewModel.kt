package com.kooky.feature.add

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.kooky.utilities.doIf
import com.kooky.viewmodel.StateViewModel
import com.kooky.viewmodel.StateViewModelConfig
import com.kooky.viewmodel.saveState
import dev.enro.core.TypedNavigationHandle
import dev.enro.core.close
import dev.enro.core.result.closeWithResult
import dev.enro.viewmodel.navigationHandle
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class AddDescriptionState(
    val steps: List<String> = emptyList()
) : Parcelable

class AddDescriptionViewModel @Inject constructor(savedStateHandle: SavedStateHandle)
    : StateViewModel<AddDescriptionState>() {

    private val nav: TypedNavigationHandle<AddDescriptionKey> by navigationHandle {
        onCloseRequested {
            //TODO do things here, confirmation dialog?
        }
    }

    override val config = configure(
        initialState = AddDescriptionState(
            steps = nav.key.description
                .doIf(
                    predicate = { !lastOrNull().isNullOrBlank() },
                    block = { plus("") }
                )
                .ifEmpty { listOf("") }
        ),
        savedStateBehavior = saveState(savedStateHandle)
    )

    fun onSaveClicked() {
        nav.closeWithResult(state.steps.filterNot { it.isBlank() })
    }
}