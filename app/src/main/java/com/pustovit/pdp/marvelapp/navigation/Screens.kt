package com.pustovit.pdp.marvelapp.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pustovit.pdp.marvelapp.ui.character.CharacterFragment
import com.pustovit.pdp.marvelapp.ui.characters.CharactersFragment
import com.pustovit.pdp.marvelapp.ui.comics.ComicsFragment
import com.pustovit.pdp.marvelapp.ui.event.EventFragment
import com.pustovit.pdp.marvelapp.ui.events.EventsFragment
import com.pustovit.pdp.marvelapp.ui.tabcontainer.TabContainerFragment
import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.domain.model.event.Event

/**
 * Created by Pustovit V.V.
 * Date: 29.05.2022
 * Time: 13:43
 */
object Screens {

    fun charactersScreen() = FragmentScreen {
        CharactersFragment()
    }

    fun characterScreen(character: Character) = FragmentScreen {
        CharacterFragment.newInstance(character)
    }

    fun comicsScreen() = FragmentScreen {
        ComicsFragment()
    }

    fun eventsScreen() = FragmentScreen {
        EventsFragment()
    }

    fun eventScreen(event: Event) = FragmentScreen {
        EventFragment.newInstance(event)
    }

    fun tabScreen(tab: TabNavigation) = FragmentScreen {
        TabContainerFragment.newInstance(tab)
    }
}