package com.pustovit.pdp.character.di

import com.pustovit.pdp.characters.api.repository.CharactersRepository

interface CharacterFeatureDependencies {

    fun repository(): CharactersRepository
}