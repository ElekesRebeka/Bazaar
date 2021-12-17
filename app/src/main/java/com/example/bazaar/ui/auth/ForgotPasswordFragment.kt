package com.example.bazaar.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.*
import kotlinx.coroutines.launch

class ForgotPasswordFragment : Fragment() {
    private lateinit var resetPasswordViewModel: ResetPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ResetPasswordViewModelFactory(Repository())
        resetPasswordViewModel = ViewModelProvider(this, factory).get(ResetPasswordViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)
        val email: EditText = view.findViewById(R.id.emailInput)
        val username: EditText = view.findViewById(R.id.usernameInput)
        val emailMeButton: Button = view.findViewById(R.id.loginButton)

        emailMeButton.setOnClickListener {
            resetPasswordViewModel.user.value.let {
                if (it != null) {
                    it.email = email.text.toString()
                }
                if (it != null) {
                    it.username = username.text.toString()
                }
            }
            lifecycleScope.launch {
                resetPasswordViewModel.resetPassword()
            }
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }

        return view;
    }

    companion object {

    }
}