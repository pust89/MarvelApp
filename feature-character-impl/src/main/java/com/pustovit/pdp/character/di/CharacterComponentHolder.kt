package com.pustovit.pdp.character.di

import com.pustovit.pdp.character_api.CharacterApi
import com.pustovit.pdp.injector.ComponentHolder

object CharacterComponentHolder:ComponentHolder<CharacterApi, CharacterFeatureDependencies> {

    override fun init(dependencies: CharacterFeatureDependencies) {
        TODO("Not yet implemented")
    }

    override fun get(): CharacterApi {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}