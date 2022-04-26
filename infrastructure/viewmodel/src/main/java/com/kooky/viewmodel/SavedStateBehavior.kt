package com.kooky.viewmodel

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle

private const val SAVED_STATE_BUNDLE_KEY = "com.kooky.viewmodel.SavedStateBehavior.SAVED_STATE_BUNDLE_KEY"
private const val SAVED_STATE_OBJECT_KEY = "com.kooky.viewmodel.SavedStateBehavior.SAVED_STATE_OBJECT_KEY"

interface SavedStateBehavior<State: Any> {
    fun restoreSavedState(): State?
}

fun <State: Any> doNotSaveState(): SavedStateBehavior<State> = object : SavedStateBehavior<State> {
    override fun restoreSavedState(): State? = null
}

fun <State: Parcelable> StateViewModel<State>.saveState(
    savedStateHandle: SavedStateHandle
): SavedStateBehavior<State> = saveMappedState(
    savedStateHandle = savedStateHandle,
    mapToSavedState = { it },
    mapFromSavedState = { it }
)

fun <State: Any, SavedState: Parcelable> StateViewModel<State>.saveMappedState(
    savedStateHandle: SavedStateHandle,
    mapToSavedState: (State) -> SavedState,
    mapFromSavedState: (SavedState) -> State
): SavedStateBehavior<State> = object : SavedStateBehavior<State> {
    init {
        savedStateHandle.setSavedStateProvider(SAVED_STATE_BUNDLE_KEY) {
            Bundle().apply {
                putParcelable(SAVED_STATE_OBJECT_KEY, mapToSavedState(state))
            }
        }
    }

    override fun restoreSavedState(): State? {
        val savedBundle = savedStateHandle.get<Bundle>(SAVED_STATE_BUNDLE_KEY)
            ?: return null

        val savedState = savedBundle.getParcelable<SavedState>(SAVED_STATE_OBJECT_KEY)
            ?: return null

        return mapFromSavedState(savedState)
    }
}