package com.thekorovay.testmosmetro.model.domain_model

import android.net.Uri

data class Tweet(
    val id: Long,
    val text: String,
    val createdAt: Long,
    val retweetCount: Int,
    val favoriteCount: Int,
    val imageUrl: Uri?
)