package com.thekorovay.testmosmetro.ui.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.thekorovay.testmosmetro.databinding.RvFirstItemBinding
import com.thekorovay.testmosmetro.databinding.RvTweetItemBinding
import com.thekorovay.testmosmetro.databinding.RvTweetItemWithImageBinding
import com.thekorovay.testmosmetro.model.domain_model.Tweet
import com.thekorovay.testmosmetro.tools.setPublishedDateFrom

class TweetViewHolder private constructor(
    private val binding: RvTweetItemBinding
): RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): TweetViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = RvTweetItemBinding.inflate(inflater, parent, false)
            return TweetViewHolder(binding)
        }
    }

    fun bind(tweet: Tweet, clickListener: (tweet: Tweet) -> Unit) {
        binding.run {
            tvDescription.text = tweet.text
            tvRetweets.text = tweet.retweetCount.toString()
            tvFavs.text = tweet.favoriteCount.toString()
            tvPublishedAt.setPublishedDateFrom(tweet.createdAt)

            root.setOnClickListener { clickListener(tweet) }
        }
    }
}

class TweetWithImageViewHolder private constructor(
    private val binding: RvTweetItemWithImageBinding
): RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): TweetWithImageViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = RvTweetItemWithImageBinding.inflate(inflater, parent, false)
            return TweetWithImageViewHolder(binding)
        }
    }

    fun bind(tweet: Tweet, clickListener: (tweet: Tweet) -> Unit) {
        binding.run {
            tvDescription.text = tweet.text
            tvRetweets.text = tweet.retweetCount.toString()
            tvFavs.text = tweet.favoriteCount.toString()
            tvPublishedAt.setPublishedDateFrom(tweet.createdAt)
            Glide
                .with(binding.root)
                .load(tweet.imageUrl)
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(24)))
                .into(ivPicture)

            root.setOnClickListener { clickListener(tweet) }
        }
    }
}

class FirstItemViewHolder private constructor(
    binding: RvFirstItemBinding
): RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): FirstItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = RvFirstItemBinding.inflate(inflater, parent, false)
            return FirstItemViewHolder(binding)
        }
    }
}