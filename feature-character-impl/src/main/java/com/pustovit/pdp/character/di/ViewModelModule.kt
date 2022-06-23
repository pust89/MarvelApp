package com.pustovit.pdp.character.di

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.character.CharacterViewModel
import com.pustovit.pdp.character.summary.SummaryViewModel
import com.pustovit.pdp.common_ui.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterViewModel::class)
    fun bindCharacterViewModel(viewModel: CharacterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SummaryViewModel::class)
    fun bindSummaryViewModel(viewModel: SummaryViewModel): ViewModel

}