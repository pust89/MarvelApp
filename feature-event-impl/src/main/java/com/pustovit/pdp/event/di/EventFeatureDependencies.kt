package com.pustovit.pdp.event.di

import coil.ImageLoader
import com.pustoivt.pdp.events_api.repository.EventsRepository
import com.pustovit.pdp.characters_api.api.adapter.CharactersListAdapter
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository
import com.pustovit.pdp.injector.FeatureDependencies

interface EventFeatureDependencies : FeatureDependencies {

    fun charactersRepository(): CharactersRepository

    fun charactersListAdapter(): CharactersListAdapter

    fun eventsRepository(): EventsRepository

    fun imageLoader(): ImageLoader

}