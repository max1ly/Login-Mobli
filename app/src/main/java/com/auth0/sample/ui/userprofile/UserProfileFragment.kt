package com.auth0.sample.ui.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.sample.R
import com.auth0.sample.databinding.FragmentUserProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

    private lateinit var binding: FragmentUserProfileBinding
    private val viewModel by viewModels<UserProfileViewModel>()

    private lateinit var stopsAdapter: StopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect {
                handleUserProfileViewState(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stopsAdapter = StopListAdapter()
        stopsAdapter.onClick = { stop ->
            findNavController().navigate(
                R.id.action_userProfileFragment_to_stopDetailsDialog,
                StopDetailsDialog.bundle(stop)
            )
        }
        binding.apply {
            userProfile.text = getString(
                R.string.userProfile,
                arguments?.getString(KEY_USER_NAME, UNKNOWN),
                arguments?.getString(KEY_USER_EMAIL, UNKNOWN)
            )
            stopsList.adapter = stopsAdapter
        }
        binding.buttonLogout.setOnClickListener {
            viewModel.logoutButtonClicked()
        }

        viewModel.fetchStops()
    }

    private fun handleUserProfileViewState(state: UserProfileViewState) {
        binding.apply {
            buttonLogout.isEnabled = state.isLogOutButtonEnabled

            if (state.isStopListLoading) {
                stopListLoadingProgress.visibility = View.VISIBLE
                stopsList.visibility = View.GONE
            } else {
                stopListLoadingProgress.visibility = View.GONE
            }

            if (state.stops.isNotEmpty()) {
                stopsList.visibility = View.VISIBLE
                stopsAdapter.submitList(state.stops)
            }

            if (state.isClosingScreen && state.account != null) {
                logoutWithBrowser(state.account)
                findNavController().navigate(R.id.action_userProfileFragment_to_loginFragment)
            }
        }
    }

    private fun logoutWithBrowser(account: Auth0) {
        WebAuthProvider.logout(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .start(requireActivity(), object: Callback<Void?, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                }

                override fun onSuccess(result: Void?) {
                }
            })
    }

    companion object {
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_EMAIL = "user_email"
        private const val UNKNOWN = "Unknown"

        fun bundle(name: String?, email: String?): Bundle {
            return Bundle().apply {
                putString(KEY_USER_NAME, name)
                putString(KEY_USER_EMAIL, email)
            }
        }
    }
}
