package com.thekorovay.testmosmetro.ui.recycler_view

import androidx.recyclerview.widget.DiffUtil

object TweetDiffCallback: DiffUtil.ItemCallback<TwitterListItem>() {
    override fun areItemsTheSame(oldItem: TwitterListItem, newItem: TwitterListItem) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: TwitterListItem, newItem: TwitterListItem) = oldItem == newItem
}