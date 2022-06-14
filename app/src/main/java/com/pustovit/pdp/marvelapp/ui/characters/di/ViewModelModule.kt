package com.pustovit.pdp.marvelapp.ui.characters.di

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.app.di.module.ViewModelKey
import com.pustovit.pdp.marvelapp.navigation.TabNavigationCharacters
import com.pustovit.pdp.marvelapp.navigation.TabNavigationEvents
import com.pustovit.pdp.marvelapp.ui.characters.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindCharacterViewModel(viewModel: CharactersViewModel): ViewModel

    @Binds
    fun bindRouter(@TabNavigationCharacters router: Router): Router
}