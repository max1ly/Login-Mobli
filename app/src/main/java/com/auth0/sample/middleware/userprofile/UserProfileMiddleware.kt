package com.auth0.sample.middleware.userprofile

import com.auth0.sample.domain.AuthRepository
import com.auth0.sample.domain.JourneyRepository
import com.auth0.sample.domain.UserProfileRepository
import com.auth0.sample.middleware.Middleware
import com.auth0.sample.redux.Store
import com.auth0.sample.ui.userprofile.UserProfileAction
import com.auth0.sample.ui.userprofile.UserProfileViewState
import javax.inject.Inject
import timber.log.Timber

class UserProfileMiddleware @Inject constructor(
    private val journeyRepository: JourneyRepository,
    private val authRepository: AuthRepository,
    private val userProfileRepository: UserProfileRepository,
) : Middleware<UserProfileViewState, UserProfileAction> {

    override suspend fun process(
        currentState: UserProfileViewState,
        action: UserProfileAction,
        store: Store<UserProfileViewState, UserProfileAction>,
    ) {
        when (action) {
            is UserProfileAction.StartFetchingUserStops -> {
                store.dispatch(UserProfileAction.FetchingUserStopsStarted)

                authRepository.getAccessToken()?.let { accessToken ->
                    val journey = journeyRepository.fetchJourneyData(accessToken)
                    store.dispatch(UserProfileAction.FetchingUserStopsSuccessful(journey.stops))

                } ?: run {
                    Timber.e("==== Error getting access token")
                    store.dispatch(UserProfileAction.FetchingUserStopsFailed("Error fetching user journey"))
                }
            }
            is UserProfileAction.LogoutButtonClicked -> {
                userProfileRepository.removeUserData()
                authRepository.invalidateToken()
                store.dispatch(UserProfileAction.ClosingUserProfileScreen(authRepository.account()))
            }
            else -> { /** skip action */ }
        }
    }
}
