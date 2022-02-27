package com.auth0.sample.ui.userprofile

import com.auth0.android.Auth0
import com.auth0.sample.domain.Stop
import com.auth0.sample.redux.State

data class UserProfileViewState(
    val isLogOutButtonEnabled: Boolean = true,
    val isStopListLoading: Boolean = false,
    val stops: List<Stop> = listOf(),
    val isClosingScreen: Boolean = false,
    val account: Auth0? = null,
) : State
