package com.pustovit.pdp.characters_api.api

import com.pustovit.pdp.characters_api.api.adapter.CharactersListAdapter
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository
import com.pustovit.pdp.injector.FeatureApi

interface CharactersApi : FeatureApi {

    fun repository(): CharactersRepository

    fun listAdapter(): CharactersListAdapter
}