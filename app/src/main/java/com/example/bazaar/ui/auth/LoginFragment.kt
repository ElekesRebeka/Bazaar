package com.example.bazaar.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.LoginViewModel
import com.example.bazaar.viewmodels.LoginViewModelFactory
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(this.requireContext(), Repository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val email: EditText = view.findViewById(R.id.emailInput)
        val password: EditText = view.findViewById(R.id.passwordInput)
        val loginButton: Button = view.findViewById(R.id.loginButton)
        val clickHere: TextView = view.findViewById(R.id.textView3)
        val signupButton: Button = view.findViewById(R.id.signUpButton)
        loginButton.setOnClickListener {
            loginViewModel.user.value.let {
                if (it != null) {
                    it.username = email.text.toString()
                }
                if (it != null) {
                    it.password = password.text.toString()
                }
            }
            lifecycleScope.launch {
                loginViewModel.login()
            }

        }
        loginViewModel.token.observe(viewLifecycleOwner){
            Log.d("xxx", "navigate to list")
            findNavController().navigate(R.id.action_loginFragment_to_listFragment)
        }
        clickHere.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
        signupButton.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return view
    }

    companion object {

    }
}