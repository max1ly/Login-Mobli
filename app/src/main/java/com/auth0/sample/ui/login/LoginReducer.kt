package com.auth0.sample.ui.login

import com.auth0.sample.redux.Reducer

class LoginReducer : Reducer<LoginViewState, LoginAction> {

    override fun reduce(currentState: LoginViewState, action: LoginAction): LoginViewState {
        return when(action) {
            is LoginAction.AuthenticationStarted -> {
                currentState.copy(
                    isAuthenticationButtonEnabled = false,
                    account = action.account,
                )
            }
            is LoginAction.AuthenticationSucceed -> {
                currentState.copy(
                    isAuthenticationRequired = false,
                )
            }
            is LoginAction.AuthenticationCompleted -> {
                currentState.copy(
                    isAuthenticationButtonEnabled = false,
                    isAuthenticationRequired = false,
                )
            }
            is LoginAction.AuthenticationUncompleted -> {
                currentState.copy(
                    isAuthenticationRequired = true,
                )
            }
            is LoginAction.AuthenticationFailed -> {
                currentState.copy(
                    isAuthenticationButtonEnabled = true,
                    authenticationError = action.error.message,
                )
            }
            is LoginAction.LoginSucceed -> {
                currentState.copy(
                    userProfile = action.userProfile,
                )
            }
            is LoginAction.LoginFailed -> {
                currentState.copy(
                    isAuthenticationButtonEnabled = true,
                    loginError = action.message,
                )
            }
            else -> currentState
        }
    }
}
