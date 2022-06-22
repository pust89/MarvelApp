package com.pustovit.pdp.marvelapp.app.di.module

import com.pustoivt.pdp.events_api.repository.EventsRepository
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository
import com.pustovit.pdp.characters.data.CharactersRepositoryImpl
import com.pustovit.pdp.events.data.EventsRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface FeatureDependenciesModule {

    @Binds
    @Singleton
    fun bindsCharactersRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    @Singleton
    fun bindsEventsRepository(repository: EventsRepositoryImpl): EventsRepository

}