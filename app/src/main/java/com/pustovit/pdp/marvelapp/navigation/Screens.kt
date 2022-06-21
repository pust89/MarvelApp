package com.pustovit.pdp.marvelapp.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pustovit.pdp.marvelapp.domain.model.common.Items
import com.pustovit.pdp.marvelapp.ui.character.CharacterFragment
import com.pustovit.pdp.marvelapp.ui.characters.CharactersFragment
import com.pustovit.pdp.marvelapp.ui.character.summary.SummaryFragment
import com.pustovit.pdp.marvelapp.ui.event.EventFragment
import com.pustovit.pdp.marvelapp.ui.events.EventsFragment
import com.pustovit.pdp.marvelapp.ui.tabcontainer.TabContainerFragment

object Screens {

    fun charactersScreen() = FragmentScreen {
        CharactersFragment()
    }

    fun characterScreen(characterId: Int) = FragmentScreen {
        CharacterFragment.newInstance(characterId)
    }

    fun summaryScreen(comics: Items) = FragmentScreen {
        SummaryFragment.newInstance(comics)
    }
    fun summaryScreen(stories: Stories) = FragmentScreen {
        SummaryFragment.newInstance(stories)
    }

    fun summaryScreen(series: Series) = FragmentScreen {
        SummaryFragment.newInstance(series)
    }

    fun eventsScreen() = FragmentScreen {
        EventsFragment()
    }

    fun eventScreen(eventId: Int) = FragmentScreen {
        EventFragment.newInstance(eventId)
    }

    fun tabScreen(tab: TabNavigation) = FragmentScreen {
        TabContainerFragment.newInstance(tab)
    }
}