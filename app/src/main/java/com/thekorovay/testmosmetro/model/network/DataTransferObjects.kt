package com.thekorovay.testmosmetro.model.network

import androidx.core.net.toUri
import com.thekorovay.testmosmetro.model.domain_model.Tweet

data class ServerApiResponse(
    val success: Boolean,
    val data: List<NetworkTweet>
) {
    val tweets get() = data.map { it.toTweet() }
}

data class NetworkTweet(
    val id: Long,
    val text: String,
    val createdAt: Long,
    val retweetCount: Int,
    val favoriteCount: Int,
    val mediaEntities: List<String>
) {
    fun toTweet() = Tweet(
        id = this.id,
        text = this.text,
        createdAt = this.createdAt,
        retweetCount = this.retweetCount,
        favoriteCount = this.favoriteCount,
        imageUrl = this.mediaEntities.firstOrNull()?.toUri()
    )
}