package com.pustovit.pdp.marvelapp.ui.tabcontainer

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.app.appComponent
import com.pustovit.pdp.marvelapp.navigation.CiceroneHolder
import com.pustovit.pdp.marvelapp.navigation.RouterProvider
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.navigation.TabNavigation
import com.pustovit.pdp.marvelapp.ui.common.extensions.mainRouter
import javax.inject.Inject

/**
 * Created by Pustovit V.V.
 * Date: 29.05.2022
 * Time: 16:00
 */
class TabContainerFragment : Fragment(R.layout.fragment_tab_container),
    RouterProvider {

    @Inject
    lateinit var ciceroneHolder: CiceroneHolder

    private val navigator: Navigator by lazy {
        AppNavigator(requireActivity(), R.id.tabContainer, childFragmentManager)
    }

    private val tabNavigation: TabNavigation
        get() = requireArguments().getParcelable<TabNavigation>(EXTRA_TAB)!!

    private val cicerone: Cicerone<Router>
        get() = ciceroneHolder.getCicerone(tabNavigation)

    override val router: Router
        get() = cicerone.router

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val fragment = childFragmentManager.findFragmentById(R.id.tabContainer)
            if (fragment != null) {
                router.exit()
            } else {
                mainRouter().exit()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBackPressedDispatcher()
        setFirstScreen()
    }

    private fun setFirstScreen() {
        if (childFragmentManager.findFragmentById(R.id.tabContainer) == null) {
            when (tabNavigation) {
                TabNavigation.CHARACTERS -> router.replaceScreen(Screens.charactersScreen())
                TabNavigation.EVENTS -> router.replaceScreen(Screens.eventsScreen())
            }
        }
    }

    private fun setOnBackPressedDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            backPressedCallback
        )
    }

    override fun onHiddenChanged(hidden: Boolean) {
        backPressedCallback.isEnabled = !hidden
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    companion object {
        private const val EXTRA_TAB = "tab"

        fun newInstance(tabNavigation: TabNavigation) =
            TabContainerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_TAB, tabNavigation)
                }
            }
    }
}