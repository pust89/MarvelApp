package com.pustovit.pdp.marvelapp.app.di

import coil.ImageLoader
import com.pustoivt.pdp.events_api.EventsApi
import com.pustoivt.pdp.events_api.repository.EventsRepository
import com.pustovit.pdp.character.di.CharacterComponentHolder
import com.pustovit.pdp.character.di.CharacterFeatureDependencies
import com.pustovit.pdp.characters.di.CharactersComponentHolder
import com.pustovit.pdp.characters.di.CharactersFeatureDependencies
import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.characters_api.api.adapter.CharactersListAdapter
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository
import com.pustovit.pdp.event.di.EventComponentHolder
import com.pustovit.pdp.event.di.EventFeatureDependencies
import com.pustovit.pdp.events.di.EventsComponentHolder
import com.pustovit.pdp.events.di.EventsFeatureDependencies
import com.pustovit.pdp.injector.*
import com.pustovit.pdp.marvelapp.app.FeatureApi
import retrofit2.Retrofit

fun setFeatureDependencies(featureApi: FeatureApi) {
    featureEvents(featureApi)
    featureEvent(featureApi)
    featureCharacters(featureApi)
    featureCharacter(featureApi)
}

private fun featureEvents(featureApi: FeatureApi) {
    EventsComponentHolder.dependencyProvider = {

        class EventsDependenciesHolder(override val block: (DependencyHolder<EventsFeatureDependencies>) -> EventsFeatureDependencies) :
            DependencyHolder0<EventsFeatureDependencies>()

        EventsDependenciesHolder { dependencyHolder ->
            object : EventsFeatureDependencies {
                override fun retrofit(): Retrofit {
                   return featureApi.coreNetworkApi.retrofit()
                }

                override fun imageLoader(): ImageLoader {
                    return featureApi.imageLoader
                }

                override val dependencyHolder: DependencyHolder<out FeatureDependencies>
                    get() = dependencyHolder
            }
        }.dependencies

    }
}

private fun featureEvent(featureApi: FeatureApi) {
    EventComponentHolder.dependencyProvider = {

        class EventDependenciesHolder(override val block: (DependencyHolder<EventFeatureDependencies>, CharactersApi, EventsApi) -> EventFeatureDependencies) :
            DependencyHolder2<CharactersApi, EventsApi, EventFeatureDependencies>(
                api1 = featureApi.charactersApi.get(),
                api2 = featureApi.eventsApi.get()
            )

        EventDependenciesHolder { dependencyHolder, charactersApi, eventsApi ->

            object : EventFeatureDependencies {

                override val dependencyHolder: DependencyHolder<out FeatureDependencies>
                    get() = dependencyHolder

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
                    return featureApi.imageLoader
                }
            }
        }.dependencies
    }
}

private fun featureCharacters(featureApi: FeatureApi) {
    CharactersComponentHolder.dependencyProvider = {

        class CharactersDependenciesHolder(override val block: (DependencyHolder<CharactersFeatureDependencies>) -> CharactersFeatureDependencies) :
            DependencyHolder0<CharactersFeatureDependencies>()

        CharactersDependenciesHolder { dependencyHolder ->
            object : CharactersFeatureDependencies {
                override fun retrofit(): Retrofit {
                    return featureApi.coreNetworkApi.retrofit()
                }

                override fun imageLoader(): ImageLoader {
                    return featureApi.imageLoader
                }

                override val dependencyHolder: DependencyHolder<out FeatureDependencies>
                    get() = dependencyHolder
            }
        }.dependencies
    }
}


private fun featureCharacter(featureApi: FeatureApi) {
    CharacterComponentHolder.dependencyProvider = {

        class CharacterDependenciesHolder(override val block: (DependencyHolder<CharacterFeatureDependencies>, CharactersApi) -> CharacterFeatureDependencies) :
            DependencyHolder1<CharactersApi, CharacterFeatureDependencies>(
                api1 = featureApi.charactersApi.get()
            )
        CharacterDependenciesHolder { dependencyHolder, charactersApi ->
            object : CharacterFeatureDependencies {
                override fun repository(): CharactersRepository {
                    return charactersApi.repository()
                }

                override fun imageLoader(): ImageLoader {
                    return featureApi.imageLoader
                }

                override val dependencyHolder: DependencyHolder<out FeatureDependencies>
                    get() = dependencyHolder
            }
        }.dependencies

    }
}

