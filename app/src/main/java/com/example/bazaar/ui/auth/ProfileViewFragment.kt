package com.example.bazaar.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.GetUserDataViewModel
import com.example.bazaar.viewmodels.GetUserDataViewModelFactory
import com.example.bazaar.viewmodels.UpdateViewModel
import com.example.bazaar.viewmodels.UpdateViewModelFactory
import kotlinx.coroutines.launch

class ProfileViewFragment : Fragment() {
    private lateinit var email: TextView
    private lateinit var username: TextView
    private lateinit var phone: TextView

    lateinit var getUserDataViewModel: GetUserDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = GetUserDataViewModelFactory(Repository())
        getUserDataViewModel = ViewModelProvider(this, factory).get(GetUserDataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_view, container, false)
        email = view.findViewById(R.id.emailView)
        phone = view.findViewById(R.id.phoneNumberView)
        username = view.findViewById(R.id.nameView)

        getUserDataViewModel.user.observe(viewLifecycleOwner) {
            username.setText(getUserDataViewModel.user.value!!.username)
            email.setText(getUserDataViewModel.user.value!!.email)
            phone.setText(getUserDataViewModel.user.value!!.phone_number.toString())
        }
        return view
    }

    companion object {
    }
}