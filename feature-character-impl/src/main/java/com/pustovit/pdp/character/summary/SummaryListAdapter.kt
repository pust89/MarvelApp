package com.pustovit.pdp.character.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.pdp.character.databinding.LayoutItemSummaryBinding
import com.pustovit.pdp.character.databinding.LayoutItemSummaryHeaderBinding
import com.pustovit.pdp.common_models.domain.Summary
import com.pustovit.pdp.character.summary.model.SummaryItem
import javax.inject.Inject

class SummaryListAdapter @Inject constructor() : ListAdapter<SummaryItem, RecyclerView.ViewHolder>(
    SummaryItemDiffUtilItemCallback()
) {

    var onItemClick: ((item: Summary) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_VIEW_TYPE_HEADER) {
            val binding = LayoutItemSummaryHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            SummaryHeaderViewHolder(binding)
        } else {
            val binding = LayoutItemSummaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            SummaryItemViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is SummaryItemViewHolder -> {
                holder.bind(item as SummaryItem.Item)
            }
            is SummaryHeaderViewHolder -> {
                holder.bind(item as SummaryItem.Header)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    inner class SummaryItemViewHolder(private val binding: LayoutItemSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(summaryItem: SummaryItem.Item) {
            binding.summaryTextView.setOnClickListener {
                onItemClick?.invoke(summaryItem.summary)
            }
            binding.summaryTextView.text = summaryItem.summary.name
        }
    }

    inner class SummaryHeaderViewHolder(private val binding: LayoutItemSummaryHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(summary: SummaryItem.Header) {
            binding.headerTextView.text = summary.title
        }
    }

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_ITEM = 1
    }

}


class SummaryItemDiffUtilItemCallback : DiffUtil.ItemCallback<SummaryItem>() {
    override fun areItemsTheSame(oldItem: SummaryItem, newItem: SummaryItem): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: SummaryItem, newItem: SummaryItem): Boolean {
        return oldItem == newItem
    }
}
