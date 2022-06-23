package com.pustovit.pdp.marvelapp.app.di.module

import coil.ImageLoader
import com.pustoivt.pdp.events_api.EventsApi
import com.pustoivt.pdp.events_api.repository.EventsRepository
import com.pustovit.pdp.character.di.CharacterFeatureDependencies
import com.pustovit.pdp.characters.di.CharactersFeatureDependencies
import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.characters_api.api.adapter.CharactersListAdapter
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository
import com.pustovit.pdp.event.di.EventFeatureDependencies
import com.pustovit.pdp.events.di.EventsFeatureDependencies
import com.pustovit.pdp.network_api.CoreNetworkApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class FeatureDependenciesModule {

    @Provides
    @Singleton
    fun provideCharactersFeatureDependencies(
        coreNetworkApi: CoreNetworkApi,
        imageLoader: ImageLoader
    ): CharactersFeatureDependencies {
        return object : CharactersFeatureDependencies {
            override fun retrofit(): Retrofit {
                return coreNetworkApi.retrofit()
            }

            override fun imageLoader(): ImageLoader {
                return imageLoader
            }
        }
    }

    @Provides
    @Singleton
    fun provideEventsFeatureDependencies(
        coreNetworkApi: CoreNetworkApi,
        imageLoader: ImageLoader
    ): EventsFeatureDependencies {
        return object : EventsFeatureDependencies {
            override fun retrofit(): Retrofit {
                return coreNetworkApi.retrofit()
            }

            override fun imageLoader(): ImageLoader {
                return imageLoader
            }
        }
    }

    @Provides
    @Singleton
    fun provideCharacterFeatureDependencies(
        charactersApi: CharactersApi,
        imageLoader: ImageLoader
    ): CharacterFeatureDependencies {
        return object : CharacterFeatureDependencies {
            override fun repository(): CharactersRepository {
                return charactersApi.repository()
            }

            override fun imageLoader(): ImageLoader {
                return imageLoader
            }
        }
    }

    @Provides
    @Singleton
    fun provideEventFeatureDependencies(
        eventsApi: EventsApi,
        charactersApi: CharactersApi,
        imageLoader: ImageLoader
    ): EventFeatureDependencies {
        return object : EventFeatureDependencies {
            override fun charactersRepository(): CharactersRepository {
                return charactersApi.repository()
            }

            override fun charactersListAdapter(): CharactersListAdapter {
                return charactersApi.listAdapter()
            }

            override fun eventsRepository(): EventsRepository {
                return eventsApi.repository()
            }

            override fun imageLoader(): ImageLoader {
                return imageLoader
            }
        }
    }

}
