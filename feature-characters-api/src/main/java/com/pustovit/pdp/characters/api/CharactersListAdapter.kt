package com.pustovit.pdp.characters.api


import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.pdp.characters.api.model.Character

abstract class CharactersListAdapter(
    private val diffUtil: DiffUtil.ItemCallback<Character>
) : ListAdapter<Character, RecyclerView.ViewHolder>(diffUtil) {

    var onItemClick: ((item: Character) -> Unit)? = null
}