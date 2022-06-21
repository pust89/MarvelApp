package com.pustovit.pdp.event.di

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.di.ViewModelKey
import com.pustovit.pdp.event.EventFragment
import com.pustovit.pdp.event.EventViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope


@EventScope
@Component(
    dependencies = [EventFeatureDependencies::class],
    modules = [ViewModelModule::class]
)
interface EventComponent {

    fun inject(eventFragment: EventFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: EventFeatureDependencies): Builder

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