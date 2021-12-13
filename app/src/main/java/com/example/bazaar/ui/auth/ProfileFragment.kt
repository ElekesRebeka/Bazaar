package com.example.bazaar.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bazaar.MyApplication
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.*
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    val factoryLogin = LoginViewModelFactory( Repository())
    private val loginViewModel: LoginViewModel by lazy{
        ViewModelProvider(requireActivity(),factoryLogin).get((LoginViewModel::class.java))
    }

    val factoryUpdate = UpdateViewModelFactory( Repository())
    private val updateViewModel: UpdateViewModel by lazy{
        ViewModelProvider(requireActivity(), factoryUpdate).get((UpdateViewModel::class.java))
    }

    //lateinit var updateViewModel: UpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val factory = UpdateViewModelFactory(this.requireContext(), Repository())
        //updateViewModel = ViewModelProvider(this, factory).get(UpdateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val email: EditText = view.findViewById(R.id.email)
        val username: EditText = view.findViewById(R.id.username)
        val phone: EditText = view.findViewById(R.id.phoneNumber)
        val updateButton: Button = view.findViewById(R.id.button)

        updateButton.setOnClickListener {
            updateViewModel.user.value.let {
                if (it != null) {
                    it.username = username.text.toString()
                    Log.d("xxx", it.username)
                }
                if (it != null) {
                    it.email = email.text.toString()
                    Log.d("xxx", it.email)
                }
                if (it != null) {
                    it.phone_number = phone.text.toString()
                    Log.d("xxx", it.phone_number)
                }
            }
            lifecycleScope.launch {
                updateViewModel.update()
            }
            findNavController().navigate(R.id.action_profileFragment_to_listFragment)
        }

        return view;
    }

    companion object {

    }
}