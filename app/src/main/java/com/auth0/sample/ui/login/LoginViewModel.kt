package com.auth0.sample.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.result.Credentials
import com.auth0.sample.middleware.LoggingMiddleware
import com.auth0.sample.middleware.login.LoginMiddleware
import com.auth0.sample.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    loggingMiddleware: LoggingMiddleware<LoginViewState, LoginAction>,
    loginMiddleware: LoginMiddleware,
) : ViewModel() {

    private val store = Store(
        initialState = LoginViewState(),
        reducer = LoginReducer(),
        middlewares = listOf(
            loggingMiddleware,
            loginMiddleware,
        ),
    )

    val viewState: StateFlow<LoginViewState> = store.state

    fun authenticateButtonClicked() {
        dispatchAction(LoginAction.AuthenticateButtonClicked)
    }

    fun authenticationError(error: AuthenticationException) {
        dispatchAction(LoginAction.AuthenticationFailed(error))
    }

    fun authenticationSuccess(credentials: Credentials) {
        dispatchAction(LoginAction.AuthenticationSucceed(credentials))
    }

    fun requestAccess() {
        dispatchAction(LoginAction.CheckAuthentication)
    }

    private fun dispatchAction(action: LoginAction) {
        viewModelScope.launch { store.dispatch(action) }
    }
}
