package com.pustovit.pdp.characters.di

import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.injector.ComponentHolderDelegate
import com.pustovit.pdp.injector.ComponentHolder

object CharactersComponentHolder : ComponentHolder<CharactersApi, CharactersFeatureDependencies> {

    override var dependencyProvider: (() -> CharactersFeatureDependencies)? = null

    internal val component by
    ComponentHolderDelegate<CharactersFeatureDependencies, CharactersComponent> { dependencies ->
        DaggerCharactersComponent.builder()
            .charactersFeatureDependencies(dependencies).build()
    }

    override fun get(): CharactersApi {
        return component
    }

}