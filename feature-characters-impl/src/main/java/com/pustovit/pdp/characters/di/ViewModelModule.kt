package com.pustovit.pdp.characters.di

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.characters.ui.CharactersViewModel
import com.pustovit.pdp.common_ui.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

}