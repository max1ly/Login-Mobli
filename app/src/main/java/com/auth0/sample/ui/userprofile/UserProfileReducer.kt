package com.auth0.sample.ui.userprofile

import com.auth0.sample.redux.Reducer

class UserProfileReducer : Reducer<UserProfileViewState, UserProfileAction> {

    override fun reduce(
        currentState: UserProfileViewState,
        action: UserProfileAction,
    ): UserProfileViewState {
        return when(action) {
            is UserProfileAction.StartFetchingUserStops -> {
                currentState.copy(isLogOutButtonEnabled = true)
            }
            is UserProfileAction.FetchingUserStopsStarted -> {
                currentState.copy(isStopListLoading = true)
            }
            is UserProfileAction.FetchingUserStopsSuccessful -> {
                currentState.copy(
                    stops = action.stops,
                    isStopListLoading = false,
                )
            }
            is UserProfileAction.ClosingUserProfileScreen -> {
                currentState.copy(
                    isClosingScreen = true,
                    account = action.account,
                )
            }
            else -> currentState
        }
    }
}
