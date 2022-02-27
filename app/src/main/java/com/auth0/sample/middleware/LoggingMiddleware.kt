package com.auth0.sample.middleware

import com.auth0.sample.redux.Action
import com.auth0.sample.redux.State
import com.auth0.sample.redux.Store
import javax.inject.Inject
import timber.log.Timber

class LoggingMiddleware<S: State, A: Action> @Inject constructor() : Middleware<S, A> {

    override suspend fun process(currentState: S, action: A, store: Store<S, A>) {
        Timber.i("==== Processing action: $action; Current state: $currentState")
    }
}
