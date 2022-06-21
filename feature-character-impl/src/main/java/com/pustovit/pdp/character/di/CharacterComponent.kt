package com.pustovit.pdp.character.di

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.character.CharacterFragment
import com.pustovit.pdp.character.CharacterViewModel
import com.pustovit.pdp.di.ViewModelKey
import com.pustovit.pdp.summary.SummaryFragment
import com.pustovit.pdp.summary.SummaryViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope


@CharacterScope
@Component(
    dependencies = [CharacterFeatureDependencies::class],
    modules = [ViewModelModule::class]
)
interface CharacterComponent {

    fun inject(characterFragment: CharacterFragment)

    fun inject(summaryFragment: SummaryFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: CharacterFeatureDependencies): Builder

        fun build(): CharacterComponent
    }

}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class CharacterScope

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