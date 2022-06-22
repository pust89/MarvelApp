package com.pustovit.pdp.character.di

import coil.ImageLoader
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository

interface CharacterFeatureDependencies {

    fun repository(): CharactersRepository

    fun imageLoader(): ImageLoader
}