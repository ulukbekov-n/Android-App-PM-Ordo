package com.example.pmordo.presentation.ui.activity_main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pmordo.R
import com.example.pmordo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        binding.bottomNavigation.setupWithNavController(findNavController(R.id.fragmentContainerView))


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        binding.mainBottomNavView.setupWithNavController(findNavController(R.id.nav_host_fragment_activity_main))
    }
    override fun onBackPressed() {
        val currentFragment =
            findChildFragmentManagerById(R.id.nav_host_fragment_activity_main)
                .fragments[0]

        if (currentFragment is OnBackPressedListener) {
            if (currentFragment.onBackPressed()) {
                return
            }
        }
        super.onBackPressed()
    }

    private fun findChildFragmentManagerById(fragmentContainerId: Int): FragmentManager {
        val contentFragment = supportFragmentManager.findFragmentById(fragmentContainerId)
            ?: throw IllegalStateException("ChildFragmentManager is not initialized, because no found fragment")
        return contentFragment.childFragmentManager
    }

}