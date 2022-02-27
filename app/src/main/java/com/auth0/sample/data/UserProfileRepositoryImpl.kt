package com.auth0.sample.data

import android.content.Context
import androidx.core.content.edit
import com.auth0.android.result.UserProfile
import com.auth0.sample.domain.UserProfileRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import timber.log.Timber

class UserProfileRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
): UserProfileRepository {

    private val sharedPrefsUserProfile = context.getSharedPreferences(USER_PROFILE_FILE_NAME, Context.MODE_PRIVATE)


    override fun saveUserData(profile: UserProfile) {
        Timber.i("==== Saving user profile: $profile")
        sharedPrefsUserProfile.edit {
            putString(KEY_USER_NAME, profile.name)
            putString(KEY_USER_EMAIL, profile.email)
            apply()
        }
    }

    override fun removeUserData() {
        sharedPrefsUserProfile.edit {
            remove(KEY_USER_NAME)
            remove(KEY_USER_EMAIL)
            apply()
        }
    }

    companion object {
        private const val USER_PROFILE_FILE_NAME = "user_profile"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_EMAIL = "user_email"
    }
}
