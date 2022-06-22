package com.pustovit.pdp.character.di

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.character.CharacterFragment
import com.pustovit.pdp.character.CharacterViewModel
import com.pustovit.pdp.utils.di.FeatureScope
import com.pustovit.pdp.common_ui.di.ViewModelKey
import com.pustovit.pdp.character.summary.SummaryFragment
import com.pustovit.pdp.character.summary.SummaryViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap


@FeatureScope
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