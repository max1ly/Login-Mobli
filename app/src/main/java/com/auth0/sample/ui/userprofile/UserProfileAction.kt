package com.auth0.sample.ui.userprofile

import com.auth0.android.Auth0
import com.auth0.sample.domain.Stop
import com.auth0.sample.redux.Action

sealed class UserProfileAction : Action {
    object StartFetchingUserStops : UserProfileAction()
    object FetchingUserStopsStarted : UserProfileAction()
    data class FetchingUserStopsSuccessful(val stops: List<Stop>) : UserProfileAction()
    data class FetchingUserStopsFailed(val message: String) : UserProfileAction()
    object LogoutButtonClicked : UserProfileAction()
    data class ClosingUserProfileScreen(val account: Auth0) : UserProfileAction()
}
