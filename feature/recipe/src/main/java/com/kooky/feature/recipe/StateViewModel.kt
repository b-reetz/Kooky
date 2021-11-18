package com.kooky.feature.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

abstract class StateViewModel<State : Any>(initialState: State) : ViewModel() {
    private val _flow = MutableStateFlow(initialState)

    val stateFlow: StateFlow<State> by lazy { _flow.asStateFlow() }

    var state: State
        get() = _flow.value
        protected set(value) {
            _flow.value = value
        }

    protected fun updateState(block: State.() -> State) {
        state = block(state)
    }

    protected fun <T> Flow<T>.onEachUpdateState(block: State.(T) -> State) {
        onEach { updateState { block(it) } }.launchIn(viewModelScope)
    }
}