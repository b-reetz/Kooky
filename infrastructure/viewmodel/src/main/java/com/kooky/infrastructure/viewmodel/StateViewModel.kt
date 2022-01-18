package com.kooky.infrastructure.viewmodel

import androidx.lifecycle.ViewModel
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

    protected fun <T> Flow<T>.onEachUpdateState(block: State.(T) -> State): Flow<T> {
        return onEach { updateState { block(it) } }
    }
}