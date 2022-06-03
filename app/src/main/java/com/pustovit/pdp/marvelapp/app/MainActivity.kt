package com.pustovit.pdp.marvelapp.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.databinding.ActivityMainBinding
import com.pustovit.pdp.marvelapp.navigation.RouterProvider
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.navigation.TabNavigation
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), RouterProvider {

    private val binding by viewBinding(ActivityMainBinding::bind)

    @Inject
    override lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: AppNavigator = AppNavigator(this, R.id.mainContainerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
        setupBottomNavigation(savedInstanceState)

    }

    private fun setupBottomNavigation(savedInstanceState: Bundle?) {

        binding.bottomNavigationView.apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_characters -> {
                        onTabNavigationSelect(TabNavigation.CHARACTERS)
                        true
                    }
                    R.id.navigation_events -> {
                        onTabNavigationSelect(TabNavigation.EVENTS)
                        true
                    }
                    else -> false
                }
            }
            if (savedInstanceState == null) {
                selectedItemId = R.id.navigation_characters
            }
        }
    }

    private fun onTabNavigationSelect(tabNavigation: TabNavigation) {
        val tag = tabNavigation.tag
        val newFragment = supportFragmentManager.findFragmentByTag(tag)

        var currentFragment: Fragment? = null

        val fragments = supportFragmentManager.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }

        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return

        val transaction = supportFragmentManager.beginTransaction()

        if (newFragment == null) {
            transaction.add(
                R.id.mainContainerView,
                Screens.tabScreen(tabNavigation)
                    .createFragment(supportFragmentManager.fragmentFactory), tag
            )
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        if (newFragment != null) {
            transaction.show(newFragment)
        }

        transaction.commitNow()
    }


    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}
