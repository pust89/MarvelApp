package com.pustovit.pdp.marvelapp.ui.events.di

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.app.di.module.ViewModelKey
import com.pustovit.pdp.marvelapp.navigation.TabNavigationEvents
import com.pustovit.pdp.marvelapp.ui.events.EventsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    fun bindEventsViewModel(viewModel: EventsViewModel): ViewModel

    @Binds
    fun bindRouter(@TabNavigationEvents router: Router): Router

}