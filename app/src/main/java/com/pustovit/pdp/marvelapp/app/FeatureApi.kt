package com.pustovit.pdp.marvelapp.app

import coil.ImageLoader
import com.pustoivt.pdp.events_api.EventsApi
import com.pustovit.pdp.character_api.CharacterApi
import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.event_api.EventApi
import com.pustovit.pdp.network_api.CoreNetworkApi

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class FeatureApi @Inject constructor(
    val imageLoader: ImageLoader,
    val coreNetworkApi: CoreNetworkApi,
    val eventsApi: Provider<EventsApi>,
    val charactersApi: Provider<CharactersApi>,
    val eventApi: Provider<EventApi>,
    val characterApi: Provider<CharacterApi>,
)