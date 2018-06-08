package com.example.workmanagerinputdatapropagation.ui.main

import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup

import com.example.workmanagerinputdatapropagation.databinding.SyncListItemBinding

class SyncListAdapter :
    BoundListAdapter<SyncPresenter, SyncListItemBinding>(diffCallback) {
    override fun bind(binding: SyncListItemBinding, item: SyncPresenter?) {
        binding.item = item
    }

    override fun createBinding(parent: ViewGroup): SyncListItemBinding {
        return SyncListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<SyncListItemBinding> {
        val binding = SyncListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return DataBoundViewHolder(binding)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<SyncPresenter>() {
            override fun areItemsTheSame(oldItem: SyncPresenter, newItem: SyncPresenter): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SyncPresenter, newItem: SyncPresenter): Boolean {
                return oldItem == newItem
            }
        }
    }

}