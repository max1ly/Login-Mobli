package com.auth0.sample.middleware.login

import com.auth0.sample.domain.AuthRepository
import com.auth0.sample.domain.UserProfileRepository
import com.auth0.sample.middleware.Middleware
import com.auth0.sample.redux.Store
import com.auth0.sample.ui.login.LoginAction
import com.auth0.sample.ui.login.LoginViewState
import javax.inject.Inject

class LoginMiddleware @Inject constructor(
    private val authRepository: AuthRepository,
    private val userProfileRepository: UserProfileRepository,
) : Middleware<LoginViewState, LoginAction> {

    override suspend fun process(
        currentState: LoginViewState,
        action: LoginAction,
        store: Store<LoginViewState, LoginAction>,
    ) {
        when (action) {
            is LoginAction.CheckAuthentication -> {
                if (authRepository.isAccessTokenSet()) {
                    store.dispatch(LoginAction.AuthenticationCompleted)
                    fetchUserProfile(store)
                }
            }
            is LoginAction.AuthenticateButtonClicked -> {
                val account = authRepository.account()
                store.dispatch(LoginAction.AuthenticationStarted(account))
            }
            is LoginAction.AuthenticationSucceed -> {
                authRepository.setAccessToken(action.credentials.accessToken)
                store.dispatch(LoginAction.AuthenticationCompleted)

                fetchUserProfile(store)
            }
            else -> { /* ignores */
            }
        }
    }

    private suspend fun fetchUserProfile(store: Store<LoginViewState, LoginAction>) {
        authRepository.getUserProfile()?.let { userProfile ->
            userProfileRepository.saveUserData(userProfile)
            store.dispatch(LoginAction.LoginSucceed(userProfile))
        } ?: run {
            store.dispatch(LoginAction.LoginFailed("Unable to load user data"))
        }
    }
}
