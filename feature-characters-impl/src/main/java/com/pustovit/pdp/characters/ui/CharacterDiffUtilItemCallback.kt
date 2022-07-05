package com.pustovit.pdp.characters.ui

import androidx.recyclerview.widget.DiffUtil
import com.pustovit.pdp.characters_api.api.model.Character
import javax.inject.Inject

class CharacterDiffUtilItemCallback @Inject constructor() : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}