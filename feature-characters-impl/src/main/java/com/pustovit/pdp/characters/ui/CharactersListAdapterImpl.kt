package com.pustovit.pdp.characters.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.pustovit.pdp.characters.api.CharactersListAdapter
import com.pustovit.pdp.characters.api.model.Character
import com.pustovit.pdp.characters.databinding.LayoutItemCharacterBinding
import javax.inject.Inject

class CharactersListAdapterImpl @Inject constructor(
    private val imageLoader: ImageLoader,
    private val diffUtil: DiffUtil.ItemCallback<Character>
) : CharactersListAdapter(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val binding = LayoutItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)
        (holder as?CharacterItemViewHolder)?.let {
            holder.bind(character)
        }
    }

     inner class CharacterItemViewHolder(private val binding: LayoutItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {

            binding.containerView.setOnClickListener {
                onItemClick?.invoke(character)
            }

            val request = ImageRequest.Builder(binding.root.context)
                .data(character.thumbnail.url)
                .transformations(CircleCropTransformation())
                .target(binding.photoImageView)
                .build()
            imageLoader.enqueue(request)
            binding.nameTextView.text = character.name
        }
    }
}



