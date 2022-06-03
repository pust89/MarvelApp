package com.pustovit.pdp.marvelapp.ui.di

import com.pustovit.pdp.marvelapp.domain.repository.CharactersRepository
import com.pustovit.pdp.marvelapp.ui.characters.CharactersViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Pustovit V.V.
 * Date: 02.06.2022
 * Time: 20:27
 */
@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideCharactersViewModel(charactersRepository: CharactersRepository): CharactersViewModel {
        return CharactersViewModel(charactersRepository)
    }
}