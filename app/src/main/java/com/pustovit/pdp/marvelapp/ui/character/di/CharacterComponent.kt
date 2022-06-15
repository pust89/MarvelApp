package com.pustovit.pdp.marvelapp.ui.character.di

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.marvelapp.app.di.module.ViewModelKey
import com.pustovit.pdp.marvelapp.navigation.TabNavigationCharacters
import com.pustovit.pdp.marvelapp.ui.character.CharacterFragment
import com.pustovit.pdp.marvelapp.ui.character.CharacterViewModel
import com.pustovit.pdp.marvelapp.ui.character.summary.SummaryFragment
import com.pustovit.pdp.marvelapp.ui.character.summary.SummaryViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope


@CharacterScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ViewModelModule::class]
)
interface CharacterComponent {

    fun inject(characterFragment: CharacterFragment)

    fun inject(summaryFragment: SummaryFragment)

    @Component.Builder
    interface Builder {

        fun appComponent(appComponent: AppComponent): Builder

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

    @Binds
    fun bindRouter(@TabNavigationCharacters router: Router): Router
}