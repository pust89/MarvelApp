package com.pustovit.pdp.characters.di

import com.pustovit.pdp.characters.data.source.remote.service.CharactersService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ServiceModule {

    @Provides
    fun provideCharactersService(retrofit: Retrofit): CharactersService {
        return retrofit.create(CharactersService::class.java)
    }

}