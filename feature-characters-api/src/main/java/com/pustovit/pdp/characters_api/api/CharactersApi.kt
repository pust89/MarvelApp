package com.pustovit.pdp.characters_api.api

import com.pustovit.pdp.characters_api.api.adapter.CharactersListAdapter
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository

interface CharactersApi {

    fun repository():CharactersRepository

    fun listAdapter(): CharactersListAdapter
}