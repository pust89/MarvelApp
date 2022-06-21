package com.pustovit.pdp.characters.di

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.pustovit.pdp.characters.api.CharactersListAdapter
import com.pustovit.pdp.characters.api.model.Character
import com.pustovit.pdp.characters.ui.CharacterDiffUtilItemCallback
import com.pustovit.pdp.characters.ui.CharactersFragment
import com.pustovit.pdp.characters.ui.CharactersListAdapterImpl
import com.pustovit.pdp.characters.ui.CharactersViewModel
import com.pustovit.pdp.di.ViewModelKey
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope


@CharactersScope
@Component(
    dependencies = [CharactersFeatureDependencies::class],
    modules = [ViewModelModule::class, UiModule::class]
)
interface CharactersComponent {

    fun inject(charactersFragment: CharactersFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: CharactersFeatureDependencies): Builder

        fun build(): CharactersComponent
    }

}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class CharactersScope

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

}

@Module
interface UiModule {

    @Binds
    fun bindCharactersListAdapterImpl(adapter: CharactersListAdapterImpl)
            : CharactersListAdapter

    @Binds
    fun bindCharacterDiffUtilItemCallback(diffUtil: CharacterDiffUtilItemCallback)
            : DiffUtil.ItemCallback<Character>

}