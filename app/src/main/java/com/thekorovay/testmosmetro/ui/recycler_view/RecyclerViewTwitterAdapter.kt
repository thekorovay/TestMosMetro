package com.thekorovay.testmosmetro.ui.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thekorovay.testmosmetro.model.domain_model.Tweet

class RecyclerViewTwitterAdapter(
    private val tweetClickListener: (tweet: Tweet) -> Unit
): ListAdapter<TwitterListItem, RecyclerView.ViewHolder>(TweetDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TwitterListItem.TYPE_FIRST_ITEM -> FirstItemViewHolder.from(parent)
        TwitterListItem.TYPE_TWEET -> TweetViewHolder.from(parent)
        TwitterListItem.TYPE_TWEET_WITH_IMAGE -> TweetWithImageViewHolder.from(parent)
        else -> throw IllegalArgumentException("Unknown viewType: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TweetViewHolder -> {
                val listItem = getItem(position) as TwitterListItem.TweetItem
                holder.bind(listItem.tweet, tweetClickListener)
            }
            is TweetWithImageViewHolder -> {
                val listItem = getItem(position) as TwitterListItem.TweetItemWithImage
                holder.bind(listItem.tweet, tweetClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int) = getItem(position).type
}