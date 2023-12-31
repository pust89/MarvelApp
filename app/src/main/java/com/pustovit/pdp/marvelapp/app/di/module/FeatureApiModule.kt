package com.pustovit.pdp.marvelapp.app.di.module

import com.pustoivt.pdp.events_api.EventsApi
import com.pustovit.pdp.character.di.CharacterComponentHolder
import com.pustovit.pdp.character.di.CharacterFeatureDependencies
import com.pustovit.pdp.character_api.CharacterApi
import com.pustovit.pdp.characters.di.CharactersComponentHolder
import com.pustovit.pdp.characters.di.CharactersFeatureDependencies
import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.event.di.EventComponentHolder
import com.pustovit.pdp.event.di.EventFeatureDependencies
import com.pustovit.pdp.event_api.EventApi
import com.pustovit.pdp.events.di.EventsComponentHolder
import com.pustovit.pdp.events.di.EventsFeatureDependencies
import com.pustovit.pdp.network.DaggerCoreNetworkComponent
import com.pustovit.pdp.network_api.CoreNetworkApi
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
class FeatureApiModule {

    @Provides
    @Singleton
    fun provideCoreNetworkApi(): CoreNetworkApi {
        return DaggerCoreNetworkComponent.builder().build()
    }

    @Provides
    fun provideCharactersApi(): CharactersApi {
        return CharactersComponentHolder.get()
    }

    @Provides
    fun provideCharacterApi(): CharacterApi {
        return CharacterComponentHolder.get()
    }

    @Provides
    fun provideEventsApi(): EventsApi {
        return EventsComponentHolder.get()
    }


    @Provides
    fun provideEventApi(): EventApi {
        return EventComponentHolder.get()
    }
}