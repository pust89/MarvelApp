package com.pustivut.pdp.core_navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface Screens {

    fun charactersScreen(): FragmentScreen

    fun eventsScreen(): FragmentScreen

    fun characterScreen(characterId: Int): FragmentScreen

    fun eventScreen(eventId: Int): FragmentScreen

    fun tabScreen(tab: TabNavigation): FragmentScreen

}