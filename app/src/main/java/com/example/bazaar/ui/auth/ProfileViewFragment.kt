package com.example.bazaar.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.UpdateViewModel
import com.example.bazaar.viewmodels.UpdateViewModelFactory
import kotlinx.coroutines.launch

class ProfileViewFragment : Fragment() {
    lateinit var updateViewModel: UpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UpdateViewModelFactory(Repository())
        updateViewModel = ViewModelProvider(this, factory).get(UpdateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_view, container, false)

        lifecycleScope.launch {
            updateViewModel.getData()
        }

        return view
    }

    companion object {
    }
}