package com.pustovit.pdp.marvelapp.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pustivut.pdp.core_navigation.Screens
import com.pustivut.pdp.core_navigation.TabNavigation
import com.pustovit.pdp.character.CharacterFragment
import com.pustovit.pdp.characters.ui.CharactersFragment
import com.pustovit.pdp.event.EventFragment
import com.pustovit.pdp.events.ui.EventsFragment
import com.pustovit.pdp.marvelapp.app.TabContainerFragment
import javax.inject.Inject

class ScreensImpl @Inject constructor() : Screens {

    override fun charactersScreen() = FragmentScreen {
        CharactersFragment()
    }

    override fun eventsScreen() = FragmentScreen {
        EventsFragment()
    }

    override fun characterScreen(characterId: Int) = FragmentScreen {
        CharacterFragment.newInstance(characterId)
    }

    override fun eventScreen(eventId: Int) = FragmentScreen {
        EventFragment.newInstance(eventId)
    }

    override fun tabScreen(tab: TabNavigation) = FragmentScreen {
        TabContainerFragment.newInstance(tab)
    }
}
