package com.thekorovay.testmosmetro.ui.recycler_view

import com.thekorovay.testmosmetro.model.domain_model.Tweet

sealed class TwitterListItem {
    object FirstItem: TwitterListItem() {
        override val id = -1L
        override val type = TYPE_FIRST_ITEM
    }
    data class TweetItem(val tweet: Tweet): TwitterListItem() {
        override val id = tweet.id
        override val type = TYPE_TWEET
    }
    data class TweetItemWithImage(val tweet: Tweet): TwitterListItem() {
        override val id = tweet.id
        override val type = TYPE_TWEET_WITH_IMAGE
    }

    abstract val id: Long
    abstract val type: Int

    companion object {
        const val TYPE_FIRST_ITEM = -1
        const val TYPE_TWEET = 0
        const val TYPE_TWEET_WITH_IMAGE = 1
    }
}