package com.auth0.sample.ui.login

import com.auth0.android.Auth0
import com.auth0.android.result.UserProfile
import com.auth0.sample.redux.State

data class LoginViewState(
    val isAuthenticationRequired: Boolean = true,
    val account: Auth0? = null,
    val isAuthenticationButtonEnabled: Boolean = true,
    val authenticationError: String? = null,
    val userProfile: UserProfile? = null,
    val loginError: String? = null,
) : State
