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
import com.example.bazaar.viewmodels.UpdateViewModel
import com.example.bazaar.viewmodels.UpdateViewModelFactory
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    //private lateinit var loginViewModel: LoginViewModel

    val factoryLogin = LoginViewModelFactory(Repository())
    private val loginViewModel: LoginViewModel by lazy{
        ViewModelProvider(requireActivity(),factoryLogin).get((LoginViewModel::class.java))
    }

//    val factoryUpdate = UpdateViewModelFactory( Repository())
//    private val updateViewModel: UpdateViewModel by lazy{
//        ViewModelProvider(requireActivity(), factoryUpdate).get((UpdateViewModel::class.java))
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val factory = LoginViewModelFactory(Repository())
        //loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
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
                //updateViewModel.getData(email.text.toString())
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