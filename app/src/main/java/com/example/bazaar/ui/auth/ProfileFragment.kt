package com.example.bazaar.ui.auth

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var phone: EditText
    private lateinit var name: TextView
    private lateinit var updateButton: Button

    val factoryLogin = LoginViewModelFactory( Repository())
    private val loginViewModel: LoginViewModel by lazy{
        ViewModelProvider(requireActivity(),factoryLogin).get((LoginViewModel::class.java))
    }

    val factoryUpdate = UpdateViewModelFactory( Repository())
    private val updateViewModel: UpdateViewModel by lazy{
        ViewModelProvider(requireActivity(), factoryUpdate).get((UpdateViewModel::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        email = view.findViewById(R.id.email)
        username = view.findViewById(R.id.username)
        phone = view.findViewById(R.id.phoneNumber)
        updateButton = view.findViewById(R.id.button)
        name = view.findViewById(R.id.nameTextView)

        updateViewModel.user.observe(viewLifecycleOwner) {
            name.text = loginViewModel.user.value!!.username
            username.setText(updateViewModel.user.value!!.username)
            email.setText(updateViewModel.user.value!!.email)
            phone.setText(updateViewModel.user.value!!.phone_number.toString())
        }

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
                        it.phone_number = phone.text.toString().toInt()
                        Log.d("xxx", it.phone_number.toString())
                        //Log.d("xxx", phone.text.toString())
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