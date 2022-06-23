package com.pustovit.pdp.character.di

import com.pustovit.pdp.character_api.CharacterApi
import com.pustovit.pdp.injector.ComponentHolder

object CharacterComponentHolder : ComponentHolder<CharacterApi, CharacterFeatureDependencies> {

    @Volatile
    private var characterComponent: CharacterComponent? = null

    override fun init(dependencies: CharacterFeatureDependencies) {
        synchronized(CharacterComponentHolder::class) {
            if (characterComponent == null) {
                characterComponent = DaggerCharacterComponent.builder()
                    .dependencies(dependencies)
                    .build()
            }
        }
    }

    override fun get(): CharacterApi {
        requireNotNull(characterComponent) { "CharacterComponent not initialised!" }
        return characterComponent!!
    }

    internal fun getComponent(): CharacterComponent {
        requireNotNull(characterComponent) { "EventComponent was not initialized!" }
        return characterComponent!!
    }

    override fun reset() {
        characterComponent = null
    }
}