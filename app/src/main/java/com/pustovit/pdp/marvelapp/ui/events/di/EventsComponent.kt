package com.pustovit.pdp.marvelapp.ui.events.di

import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.marvelapp.ui.events.EventsFragment
import dagger.Component
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

