package com.example.pmordo.presentation.ui.activity_main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pmordo.R
import com.example.pmordo.databinding.ActivityMainBinding
import com.example.pmordo.presentation.di.appModule
import com.example.pmordo.presentation.ui.home.HomeFragment
import com.example.pmordo.presentation.ui.home.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        fragmentManager = supportFragmentManager

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainBottomNavView.setupWithNavController(navController)

        val toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener(this)
        binding.menuButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.homeFragment->{openFragment(HomeFragment())
                setDrawerVisibility(true)
            }
            R.id.profileFragment->openFragment(ProfileFragment())
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val currentFragment =
            findChildFragmentManagerById(R.id.nav_host_fragment_activity_main)
                .fragments[0]
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            (binding.drawerLayout.closeDrawer(GravityCompat.START))
        }else{
            super.onBackPressedDispatcher.onBackPressed()
        }

        if (currentFragment is OnBackPressedListener) {
            if (currentFragment.onBackPressed()) {
                return
            }
        }
        super.onBackPressed()
    }
    private fun openFragment(fragment:Fragment){
        val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment)
        fragmentTransaction.commit()
    }

    private fun findChildFragmentManagerById(fragmentContainerId: Int): FragmentManager {
        val contentFragment = supportFragmentManager.findFragmentById(fragmentContainerId)
            ?: throw IllegalStateException("ChildFragmentManager is not initialized, because no found fragment")
        return contentFragment.childFragmentManager
    }
    fun setDrawerVisibility(visible: Boolean) {
        if (visible) {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            binding.toolbar.visibility = View.VISIBLE
        } else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            binding.toolbar.visibility = View.GONE
        }
    }


}