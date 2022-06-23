package com.pustovit.pdp.marvelapp.app

import com.pustoivt.pdp.events_api.EventsApi
import com.pustovit.pdp.character_api.CharacterApi
import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.event_api.EventApi

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class Features @Inject constructor(
    private val eventsApi: EventsApi,
    private val charactersApi: CharactersApi,
    private val eventApi: Provider<EventApi>,
    private val characterApi: Provider<CharacterApi>,
)