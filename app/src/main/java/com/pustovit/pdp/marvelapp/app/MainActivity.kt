package com.pustovit.pdp.marvelapp.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.app.di.module.MainNavigatorHolder
import com.pustovit.pdp.marvelapp.app.di.module.MainRouter
import com.pustovit.pdp.marvelapp.databinding.ActivityMainBinding
import com.pustovit.pdp.marvelapp.navigation.RouterProvider
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.navigation.TabNavigation
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), RouterProvider {

    private var binding: ActivityMainBinding? = null

    @Inject
    @MainRouter
    override lateinit var router: Router

    @Inject
    @MainNavigatorHolder
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: AppNavigator = AppNavigator(this, R.id.mainContainerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            binding = this
            setContentView(this.root)
        }
        appComponent().inject(this)
        binding?.bottomNavigationView?.let {
            setupBottomNavigation(it, savedInstanceState)
        }

    }

    private fun setupBottomNavigation(
        bottomNavigationView: BottomNavigationView,
        savedInstanceState: Bundle?
    ) {
        bottomNavigationView.setOnItemSelectedListener {
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
            bottomNavigationView.selectedItemId = R.id.navigation_events
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

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
