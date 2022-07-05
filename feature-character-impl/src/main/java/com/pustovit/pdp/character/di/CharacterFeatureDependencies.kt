package com.pustovit.pdp.character.di

import coil.ImageLoader
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository
import com.pustovit.pdp.injector.FeatureDependencies

interface CharacterFeatureDependencies :FeatureDependencies{

    fun repository(): CharactersRepository

    fun imageLoader(): ImageLoader
}