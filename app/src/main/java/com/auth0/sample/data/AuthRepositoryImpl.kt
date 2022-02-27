package com.auth0.sample.data

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.result.UserProfile
import com.auth0.sample.R
import com.auth0.sample.domain.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import timber.log.Timber

class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : AuthRepository {

    private val accountManager = AccountManager.get(context)
    private var accessToken: String? = null
    private val account: Account = Account(
        context.getString(R.string.account_name),
        context.getString(R.string.account_type),
    )

    init {
        accessToken = accountManager.peekAuthToken(account, context.getString(R.string.account_type))
    }

    override fun account(): Auth0 {
        return Auth0(
            clientId = context.getString(R.string.com_auth0_client_id),
            domain = context.getString(R.string.com_auth0_domain),
        )
    }

    override fun setAccessToken(authToken: String) {
        accessToken = authToken
        accountManager.addAccountExplicitly(account, null, null)
        accountManager.setAuthToken(account, context.getString(R.string.account_type), authToken)
    }

    override fun getAccessToken() = accessToken

    override fun isAccessTokenSet(): Boolean {
        return accessToken != null
    }

    override fun invalidateToken() {
        accountManager.invalidateAuthToken(context.getString(R.string.account_name), accessToken)
        accessToken = null
    }

    override suspend fun getUserProfile(): UserProfile? {
        accessToken?.let { setAccessToken(it) } ?: throw IllegalStateException("Access token is not set")
        return runCatching { getUserProfile(accessToken!!) }.getOrNull()
    }

    private suspend fun getUserProfile(accessToken: String): UserProfile {
        return suspendCoroutine { continuation ->
            AuthenticationAPIClient(account()).userInfo(accessToken)
                .start(object : Callback<UserProfile, AuthenticationException> {
                    override fun onFailure(error: AuthenticationException) {
                        Timber.e("==== Failed to load user profile")
                        error.printStackTrace()
                        continuation.resumeWithException(error)
                    }

                    override fun onSuccess(result: UserProfile) {
                        Timber.i("==== User profile loaded: ${result.name}, email: ${result.email}")
                        continuation.resumeWith(Result.success(result))
                    }
                })
        }
    }
}
