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
import com.example.bazaar.viewmodels.RegisterViewModel
import com.example.bazaar.viewmodels.RegisterViewModelFactory
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = RegisterViewModelFactory(this.requireContext(), Repository())
        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val email: EditText = view.findViewById(R.id.emailInput)
        val password: EditText = view.findViewById(R.id.passwordInput)
        val username: EditText = view.findViewById(R.id.editTextTextPersonName)
        val phone: EditText = view.findViewById(R.id.editTextTextPersonName2)
        val registerButton: Button = view.findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            registerViewModel.user.value.let {
                if (it != null) {
                    it.username = username.text.toString()
                }
                if (it != null) {
                    it.password = password.text.toString()
                }
                if (it != null) {
                    it.email = email.text.toString()
                }
                if (it != null) {
                    it.phone_number = phone.text.toString()
                }
            }
            lifecycleScope.launch {
                registerViewModel.register()
            }
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        val clickHere: TextView = view.findViewById(R.id.textView3)
        clickHere.setOnClickListener(){
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        return view;
    }

    companion object {

    }
}