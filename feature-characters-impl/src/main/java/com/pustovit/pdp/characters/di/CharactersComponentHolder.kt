package com.pustovit.pdp.characters.di

import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.injector.ComponentHolder

object CharactersComponentHolder : ComponentHolder<CharactersApi, CharactersFeatureDependencies> {

    @Volatile
    private var charactersComponent: CharactersComponent? = null

    override fun init(dependencies: CharactersFeatureDependencies) {
        synchronized(CharactersComponentHolder::class) {
            if (charactersComponent == null) {
                charactersComponent = DaggerCharactersComponent.builder()
                    .charactersFeatureDependencies(dependencies)
                    .build()
            }
        }
    }

    override fun get(): CharactersApi {
        requireNotNull(charactersComponent) { "CharactersComponent was not initialized!" }
        return charactersComponent!!
    }

    internal fun getComponent(): CharactersComponent {
        requireNotNull(charactersComponent) { "CharactersComponent was not initialized!" }
        return charactersComponent!!
    }

    override fun reset() {
        charactersComponent = null
    }
}