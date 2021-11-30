package com.example.bazaar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView
    private lateinit var topAppBar: AppBarLayout
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private val topDestinationIds = setOf(
        R.id.forgotPasswordFragment,
//        R.id.loginFragment,
        R.id.registerFragment
    )

    private fun initialize(){
        navView = findViewById(R.id.bottom_navigation)
        topAppBar = findViewById(R.id.appbar)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        configNavController()
    }

    private fun hide(){
        navView.visibility = View.GONE
        topAppBar.visibility = View.GONE
    }

    private fun visible(){
        navView.visibility = View.VISIBLE
        topAppBar.visibility = View.VISIBLE
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