package com.pustovit.pdp.character.di

import com.pustovit.pdp.character_api.CharacterApi
import com.pustovit.pdp.injector.ComponentHolder
import com.pustovit.pdp.injector.ComponentHolderDelegate

object CharacterComponentHolder : ComponentHolder<CharacterApi, CharacterFeatureDependencies> {

    override var dependencyProvider: (() -> CharacterFeatureDependencies)? = null

    internal val component by ComponentHolderDelegate<CharacterFeatureDependencies, CharacterComponent> { dependencies ->
        DaggerCharacterComponent.builder().characterFeatureDependencies(dependencies).build()
    }

    override fun get(): CharacterApi {
        return component
    }

}