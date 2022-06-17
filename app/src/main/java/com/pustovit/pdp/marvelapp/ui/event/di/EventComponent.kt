package com.pustovit.pdp.marvelapp.ui.event.di

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.marvelapp.app.di.module.ViewModelKey
import com.pustovit.pdp.marvelapp.navigation.TabNavigationEvents
import com.pustovit.pdp.marvelapp.ui.event.EventFragment
import com.pustovit.pdp.marvelapp.ui.event.EventViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope


@EventScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ViewModelModule::class]
)
interface EventComponent {

    fun inject(eventFragment: EventFragment)

    @Component.Builder
    interface Builder {

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): EventComponent
    }

}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class EventScope

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventViewModel::class)
    fun bindEventViewModel(viewModel: EventViewModel): ViewModel

}