package com.pustovit.pdp.event.di

import coil.ImageLoader
import com.pustoivt.pdp.events_api.repository.EventsRepository
import com.pustovit.pdp.characters_api.api.adapter.CharactersListAdapter
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository

interface EventFeatureDependencies {

    fun eventsRepository(): EventsRepository

    fun charactersRepository(): CharactersRepository

    fun charactersListAdapter(): CharactersListAdapter

    fun imageLoader(): ImageLoader

}