package com.pustovit.pdp.events.di

import com.pustoivt.pdp.events_api.repository.EventsRepository
import com.pustovit.pdp.utils.di.FeatureScope
import com.pustovit.pdp.events.data.EventsRepositoryImpl
import com.pustovit.pdp.events.data.source.EventsRemoteDataSource
import com.pustovit.pdp.events.data.source.EventsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    @FeatureScope
    fun bindEventsRepository(repository: EventsRepositoryImpl)
            : EventsRepository

    @Binds
    fun bindEventsRemoteDataSource(source: EventsRemoteDataSourceImpl)
            : EventsRemoteDataSource
}