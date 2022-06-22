package com.pustovit.pdp.events.di

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.common_ui.di.ViewModelKey
import com.pustovit.pdp.events.ui.EventsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    fun bindEventsViewModel(viewModel: EventsViewModel): ViewModel

}