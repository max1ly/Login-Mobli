package com.auth0.sample.redux

interface Reducer<S: State, A: Action> {

    fun reduce(currentState: S, action: A): S

}
