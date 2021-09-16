package com.test.cleanarchitecturewithdi.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import com.test.cleanarchitecturewithdi.R
import com.test.cleanarchitecturewithdi.databinding.FragmentSignInBinding
import com.test.cleanarchitecturewithdi.view_models.SignInNavigationAction
import com.test.cleanarchitecturewithdi.view_models.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_in,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                launch {
                    binding.buttonSignIn.setOnClickListener {
                        viewModel.signIn()
                    }
                }
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                launch {
                    viewModel.message.collect {
                        Toasty.error(requireContext(), it, Toasty.LENGTH_SHORT).show()
                    }
                }

                launch {
                    viewModel.isLoggedIn.collect {
                        if (it) {
                            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                        }
                    }
                }

                launch {
                    viewModel.navigationActions.collect {
                        if (it == SignInNavigationAction.NavigateToHome) {
                            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                        }
                    }
                }
            }
        }
    }
}
