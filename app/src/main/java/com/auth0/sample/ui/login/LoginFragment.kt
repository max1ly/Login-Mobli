package com.auth0.sample.ui.login

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
import com.auth0.android.result.Credentials
import com.auth0.sample.R
import com.auth0.sample.databinding.FragmentLoginBinding
import com.auth0.sample.ui.showSnackBar
import com.auth0.sample.ui.userprofile.UserProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.requestAccess()

        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect {
                handleLoginViewState(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            viewModel.authenticateButtonClicked()
        }
    }

    private fun handleLoginViewState(newState: LoginViewState) {
        binding.apply {

            buttonLogin.isEnabled = newState.isAuthenticationButtonEnabled
            // TODO: show also a progress bar

            if (newState.isAuthenticationRequired && newState.account != null) {
                loginWithBrowser(newState.account)
            }

            if (newState.authenticationError != null) {
                binding.root.showSnackBar("Authentication error:\n${newState.authenticationError}")
            }

            if (newState.loginError != null) {
                binding.root.showSnackBar("Login error:\n${newState.loginError}")
            }

            if (newState.userProfile != null) {
                navigateToUserProfile(
                    newState.userProfile.name,
                    newState.userProfile.email,
                )
            }
        }
    }

    private fun navigateToUserProfile(name: String?, email: String?) {
        findNavController().navigate(
            R.id.action_loginFragment_to_userProfileFragment,
            UserProfileFragment.bundle(name, email)
        )
    }

    private fun loginWithBrowser(account: Auth0) {
        WebAuthProvider.login(account)
            .withScope("openid profile")
            .withAudience("https://auth.dev.operator.mobli.io/oauth/login")
            .withScheme(getString(R.string.com_auth0_scheme))
            .start(requireActivity(), object : Callback<Credentials, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                    viewModel.authenticationError(error)
                }

                override fun onSuccess(result: Credentials) {
                    viewModel.authenticationSuccess(result)
                }
            })
    }
}
