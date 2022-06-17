package com.pustovit.pdp.marvelapp.ui.events.di

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.marvelapp.app.di.module.ViewModelKey
import com.pustovit.pdp.marvelapp.navigation.TabNavigationEvents
import com.pustovit.pdp.marvelapp.ui.events.EventsFragment
import com.pustovit.pdp.marvelapp.ui.events.EventsViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope

@EventsScope
@Component(dependencies = [AppComponent::class],
modules = [ViewModelModule::class])
interface EventsComponent {

    fun inject(eventsFragment: EventsFragment)

    @Component.Builder
    interface Builder {

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): EventsComponent
    }

}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class EventsScope

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    fun bindEventsViewModel(viewModel: EventsViewModel): ViewModel

}

