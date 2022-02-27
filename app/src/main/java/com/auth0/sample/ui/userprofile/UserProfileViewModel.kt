package com.auth0.sample.ui.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.sample.middleware.LoggingMiddleware
import com.auth0.sample.middleware.userprofile.UserProfileMiddleware
import com.auth0.sample.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    loggingMiddleware: LoggingMiddleware<UserProfileViewState, UserProfileAction>,
    userProfileMiddleware: UserProfileMiddleware,
) : ViewModel() {

    private val store = Store<UserProfileViewState, UserProfileAction>(
        initialState = UserProfileViewState(),
        reducer = UserProfileReducer(),
        middlewares = listOf(
            loggingMiddleware,
            userProfileMiddleware,
        ),
    )

    val viewState: StateFlow<UserProfileViewState> = store.state

    fun fetchStops() {
        val action = UserProfileAction.StartFetchingUserStops
        dispatchAction(action)
    }

    fun logoutButtonClicked() {
        val action = UserProfileAction.LogoutButtonClicked
        dispatchAction(action)
    }

    private fun dispatchAction(action: UserProfileAction) {
        viewModelScope.launch { store.dispatch(action) }
    }
}
