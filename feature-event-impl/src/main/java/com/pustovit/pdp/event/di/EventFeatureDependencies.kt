package com.pustovit.pdp.event.di

import coil.Coil
import com.pustoivt.pdp.events.api.repository.EventsRepository
import com.pustovit.pdp.characters.api.CharactersListAdapter
import com.pustovit.pdp.characters.api.repository.CharactersRepository

interface EventFeatureDependencies {

    fun eventsRepository(): EventsRepository

    fun charactersRepository(): CharactersRepository

    fun charactersListAdapter(): CharactersListAdapter

    fun coil(): Coil
}