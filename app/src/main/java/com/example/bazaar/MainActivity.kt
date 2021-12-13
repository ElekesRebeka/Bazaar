package com.example.bazaar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bazaar.model.Product
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private fun initialize(){
        navView = findViewById(R.id.bottom_navigation)
        topAppBar = findViewById(R.id.topAppBar)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        configNavController()
        initMenu()
    }

    private fun hide(){
        navView.visibility = View.GONE
        topAppBar.visibility = View.GONE
    }

    private fun visible(){
        navView.visibility = View.VISIBLE
        topAppBar.visibility = View.VISIBLE
    }

    private fun initMenu(){
        topAppBar.setOnMenuItemClickListener{ menuItem ->
        when (menuItem.itemId) {
            R.id.profile -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
                true
            }
            R.id.search -> {
                // Handle search icon press
                true
            }
            R.id.filter -> {
                // Handle search icon press
                true
            }
            else -> false
            }
        }

        navView.setOnItemSelectedListener  { item ->
            when(item.itemId) {
                R.id.timeline -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.listFragment)
                    true
                }
                R.id.mymarket -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.myMarketFragment)
                    true
                }
                R.id.union -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }

    private fun configNavController() {

        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> hide()
                R.id.registerFragment -> hide()
                R.id.forgotPasswordFragment -> hide()
                else -> visible()
            }
        }
    }
}