package com.pustovit.pdp.marvelapp.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.pustovit.pdp.marvelapp.databinding.LayoutItemCharacterBinding
import com.pustovit.pdp.marvelapp.domain.model.characters.Character

class CharactersListAdapter(
    private val imageLoader: ImageLoader,
    private val onItemClick: (item: Character) -> Unit
) :
    ListAdapter<Character, CharactersListAdapter.CharacterItemViewHolder>(
        CharacterDiffUtilItemCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val binding = LayoutItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    inner class CharacterItemViewHolder(private val binding: LayoutItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {

            binding.containerView.setOnClickListener {
                onItemClick(character)
            }

            val request = ImageRequest.Builder(binding.root.context)
                .data(character.thumbnail.path+"."+character.thumbnail.extension)
                .target(binding.photoImageView)
                .build()
            imageLoader.enqueue(request)

            binding.nameTextView.text = character.name
            binding.descriptionsTextView.text = character.description
        }
    }
}


class CharacterDiffUtilItemCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}
