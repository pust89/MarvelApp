package com.pustovit.pdp.event.di

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.common_ui.di.ViewModelKey
import com.pustovit.pdp.event.EventViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventViewModel::class)
    fun bindEventViewModel(viewModel: EventViewModel): ViewModel

}