package com.pustovit.pdp.marvelapp.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pustovit.pdp.marvelapp.ui.character.CharacterFragment
import com.pustovit.pdp.marvelapp.ui.characters.CharactersFragment
import com.pustovit.pdp.marvelapp.ui.comics.ComicsFragment
import com.pustovit.pdp.marvelapp.ui.event.EventFragment
import com.pustovit.pdp.marvelapp.ui.events.EventsFragment
import com.pustovit.pdp.marvelapp.ui.tabcontainer.TabContainerFragment

/**
 * Created by Pustovit V.V.
 * Date: 29.05.2022
 * Time: 13:43
 */
object Screens {

    fun charactersScreen() = FragmentScreen {
        CharactersFragment()
    }

    fun characterScreen() = FragmentScreen {
        CharacterFragment()
    }

    fun comicsScreen() = FragmentScreen {
        ComicsFragment()
    }

    fun eventsScreen() = FragmentScreen {
        EventsFragment()
    }

    fun eventScreen() = FragmentScreen {
        EventFragment()
    }

    fun tabScreen(tab: TabNavigation) = FragmentScreen {
        TabContainerFragment.newInstance(tab)
    }
}