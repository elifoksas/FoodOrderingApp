package com.elifoksas.foodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.elifoksas.foodorderingapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setBottomNavigation()
    }

    private fun setBottomNavigation(){
        bottomNavigationView = binding.bottomNavigation
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottomNavigationView,navController)

        navController.addOnDestinationChangedListener{_ , destination, _->
            bottomNavigationView.visibility = if (destination.id == R.id.loginFragment || destination.id == R.id.signUpFragment){
                View.GONE
            }else{
                View.VISIBLE
            }
        }
    }
}