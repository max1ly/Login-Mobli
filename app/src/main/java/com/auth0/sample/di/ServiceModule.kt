package com.auth0.sample.di

import com.auth0.sample.data.AuthRepositoryImpl
import com.auth0.sample.data.UserProfileRepositoryImpl
import com.auth0.sample.data.network.JourneyRepositoryImpl
import com.auth0.sample.domain.AuthRepository
import com.auth0.sample.domain.JourneyRepository
import com.auth0.sample.domain.UserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {

    @Binds
    @Singleton
    fun bindsAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindsUserProfileRepository(repository: UserProfileRepositoryImpl): UserProfileRepository

    @Binds
    @Singleton
    fun bindsJourneyRepository(repository: JourneyRepositoryImpl): JourneyRepository

}
