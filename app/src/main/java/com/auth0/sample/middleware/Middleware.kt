package com.auth0.sample.middleware

import com.auth0.sample.redux.Action
import com.auth0.sample.redux.State
import com.auth0.sample.redux.Store

/**
 * Represents side asynchronous actions like network requests
 */
interface Middleware<S: State, A: Action> {

    /**
     * Processes current state with a *new* action
     */
    suspend fun process(
        currentState: S,
        action: A,
        store: Store<S, A>
    )
}
