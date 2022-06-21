package com.pustovit.pdp.events.di

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.di.ViewModelKey
import com.pustovit.pdp.events.ui.EventsFragment
import com.pustovit.pdp.events.ui.EventsViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope

@EventsScope
@Component(dependencies = [EventsFeatureDependencies::class],
modules = [ViewModelModule::class])
interface EventsComponent {

    fun inject(eventsFragment: EventsFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: EventsFeatureDependencies): Builder

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

