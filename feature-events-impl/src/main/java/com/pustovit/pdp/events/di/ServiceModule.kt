package com.pustovit.pdp.events.di

import com.pustovit.pdp.events.data.source.service.EventsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ServiceModule {

    @Provides
    fun provideEventsService(retrofit: Retrofit): EventsService {
        return retrofit.create(EventsService::class.java)
    }

}