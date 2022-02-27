package com.auth0.sample.di

import com.auth0.sample.middleware.LoggingMiddleware
import com.auth0.sample.middleware.Middleware
import com.auth0.sample.middleware.login.LoginMiddleware
import com.auth0.sample.middleware.userprofile.UserProfileMiddleware
import com.auth0.sample.redux.Action
import com.auth0.sample.redux.State
import com.auth0.sample.ui.login.LoginAction
import com.auth0.sample.ui.login.LoginViewState
import com.auth0.sample.ui.userprofile.UserProfileAction
import com.auth0.sample.ui.userprofile.UserProfileViewState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface MiddlewareModule {

    @Binds
    @Singleton
    fun bindsLoggingMiddleware(middleware: LoggingMiddleware<State, Action>): Middleware<State, Action>

    @Binds
    @Singleton
    fun bindsLoginMiddleware(middleware: LoginMiddleware): Middleware<LoginViewState, LoginAction>

    @Binds
    @Singleton
    fun bindsUserProfileMiddleware(middleware: UserProfileMiddleware): Middleware<UserProfileViewState, UserProfileAction>
}
