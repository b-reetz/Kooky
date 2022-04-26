package com.kooky.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class StateViewModel<State : Any> : ViewModel() {

    protected abstract val config: StateViewModelConfig<State>
    protected fun configure(
        initialState: State,
        savedStateBehavior: SavedStateBehavior<State> = doNotSaveState()
    ): StateViewModelConfig<State> = StateViewModelConfig<State>(
        initialState = initialState,
        savedStateBehavior = savedStateBehavior
    )

    private val _flow by lazy { MutableStateFlow(config.initialState) }
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

class StateViewModelConfig<State: Any> internal constructor(
    val initialState: State,
    val savedStateBehavior: SavedStateBehavior<State>,
)