package com.example.effectivemobile

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.effectivemobile.databinding.ActivityMainBinding
import com.example.effectivemobile.ui.favourites.FavouritesFragment
import com.example.effectivemobile.ui.messages.MessagesFragment
import com.example.effectivemobile.ui.profile.ProfileFragment
import com.example.effectivemobile.ui.responses.ResponsesFragment
import com.example.effectivemobile.ui.search.SearchFragment


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration;
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_search -> replaceFragment(SearchFragment())
                R.id.navigation_favorites -> replaceFragment(FavouritesFragment())
                R.id.navigation_responses -> replaceFragment(ResponsesFragment())
                R.id.navigation_message -> replaceFragment(MessagesFragment())
                R.id.navigation_profile -> replaceFragment(ProfileFragment())
            }
            var badge = binding.bottomNavigation.getOrCreateBadge(item.itemId)
            badge.isVisible = true
            // An icon only badge will be displayed unless a number or text is set:
            badge.number = 99
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.nav_host, fragment
        )
        transaction.commit()
    }

}