package com.pustovit.pdp.characters.di

import androidx.recyclerview.widget.DiffUtil
import com.pustovit.pdp.characters_api.api.adapter.CharactersListAdapter
import com.pustovit.pdp.characters_api.api.model.Character
import com.pustovit.pdp.characters.ui.CharacterDiffUtilItemCallback
import com.pustovit.pdp.characters.ui.CharactersListAdapterImpl
import dagger.Binds
import dagger.Module

@Module
interface UiModule {

    @Binds
    fun bindCharactersListAdapterImpl(adapter: CharactersListAdapterImpl)
            : CharactersListAdapter

    @Binds
    fun bindCharacterDiffUtilItemCallback(diffUtil: CharacterDiffUtilItemCallback)
            : DiffUtil.ItemCallback<Character>

}