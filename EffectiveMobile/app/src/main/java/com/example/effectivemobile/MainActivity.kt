package com.example.effectivemobile

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobile.app.App
import com.example.effectivemobile.databinding.ActivityMainBinding
import com.example.effectivemobile.ui.favourites.FavouritesViewModel
import com.example.effectivemobile.ui.search.SearchViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration;
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels { searchViewModelFactory }

    @Inject
    lateinit var searchViewModelFactory: MainActivityViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as App).appComponent.inject(this)
        setSupportActionBar(binding.toolBar)

        val navView: BottomNavigationView = binding.bottomNavigation


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_search,
                R.id.navigation_favorites,
                R.id.navigation_responses,
                R.id.navigation_message,
                R.id.navigation_profile
            )
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolBar.visibility = View.INVISIBLE
            if (destination.id == R.id.navigation_favorites) {
                viewModel.getVacancy()
                viewModel.result.observe(this) { count ->
                    val badge = binding.bottomNavigation.getOrCreateBadge(R.id.navigation_favorites)
                    badge.isVisible = true
                    badge.number = count
                }
            }

        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    private fun getCountVacation(): Int? {
        viewModel.getVacancy()
        return viewModel.result.value
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}

