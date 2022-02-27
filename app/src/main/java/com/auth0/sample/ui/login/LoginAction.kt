package com.auth0.sample.ui.login

import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.auth0.sample.redux.Action

sealed class LoginAction : Action {
    object CheckAuthentication : LoginAction()
    object AuthenticateButtonClicked : LoginAction()
    data class AuthenticationStarted(val account: Auth0) : LoginAction()
    data class AuthenticationSucceed(val credentials: Credentials) : LoginAction()
    data class AuthenticationFailed(val error: AuthenticationException) : LoginAction()
    object AuthenticationCompleted : LoginAction()
    object AuthenticationUncompleted : LoginAction()
    object LoginStarted : LoginAction()
    data class LoginSucceed(val userProfile: UserProfile) : LoginAction()
    data class LoginFailed(val message: String) : LoginAction()
}
