package com.auth0.sample.domain

import com.auth0.android.Auth0
import com.auth0.android.result.UserProfile

interface AuthRepository {

    fun account(): Auth0

    fun setAccessToken(accessToken: String)

    fun getAccessToken(): String?

    fun isAccessTokenSet(): Boolean

    suspend fun getUserProfile(): UserProfile?

    fun invalidateToken()
}
