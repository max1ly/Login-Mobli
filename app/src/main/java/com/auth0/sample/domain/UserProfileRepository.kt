package com.auth0.sample.domain

import com.auth0.android.result.UserProfile

interface UserProfileRepository {

    fun saveUserData(profile: UserProfile)

    fun removeUserData()

}
