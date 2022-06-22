package com.pustovit.pdp.marvelapp.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pustovit.pdp.character.CharacterFragment
import com.pustovit.pdp.characters.ui.CharactersFragment
import com.pustovit.pdp.event.EventFragment
import com.pustovit.pdp.events.ui.EventsFragment
import com.pustovit.pdp.marvelapp.app.TabContainerFragment

object Screens {

    fun charactersScreen() = FragmentScreen {
        CharactersFragment()
    }

    fun eventsScreen() = FragmentScreen {
        EventsFragment()
    }

    fun characterScreen(characterId: Int) = FragmentScreen {
        CharacterFragment.newInstance(characterId)
    }

    fun eventScreen(eventId: Int) = FragmentScreen {
        EventFragment.newInstance(eventId)
    }

    fun tabScreen(tab: TabNavigation) = FragmentScreen {
        TabContainerFragment.newInstance(tab)
    }
}