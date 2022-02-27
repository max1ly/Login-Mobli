package com.auth0.sample.redux

import com.auth0.sample.middleware.Middleware
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Store<S: State, A: Action>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<Middleware<S, A>>,
) {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val currentState
        get() = _state.value

    suspend fun dispatch(action: A) {
        middlewares.forEach { middleware ->
            middleware.process(currentState, action, this)
        }
        val newState = reducer.reduce(currentState, action)
        _state.value = newState
    }

}
