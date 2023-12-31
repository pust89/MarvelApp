package com.pustovit.pdp.events.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.pustoivt.pdp.events_api.model.Event
import com.pustovit.pdp.events.databinding.LayoutItemEventBinding
import javax.inject.Inject

class EventsListAdapter @Inject constructor(
    private val imageLoader: ImageLoader
) : ListAdapter<Event, EventsListAdapter.EventItemViewHolder>(
    EventDiffUtilItemCallback()
) {

    var onItemClick: ((item: Event) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        val binding = LayoutItemEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    inner class EventItemViewHolder(private val binding: LayoutItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {

            binding.containerView.setOnClickListener {
                onItemClick?.invoke(event)
            }

            val request = ImageRequest.Builder(binding.root.context)
                .data(event.thumbnail.url)
                .transformations(RoundedCornersTransformation())
                .target(binding.photoImageView)
                .build()
            imageLoader.enqueue(request)
            binding.modifiedTextView.text = event.modified.take(10)
            binding.nameTextView.text = event.title
            binding.descriptionsTextView.text = event.description
        }
    }
}


class EventDiffUtilItemCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}
