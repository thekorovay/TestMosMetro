package com.thekorovay.testmosmetro.model.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://devapp.mosmetro.ru/"

interface ServerApi {
    @GET("api/tweets/v1.0/")
    suspend fun requestTwitterFeed(): ServerApiResponse
}


fun getServerApi(): ServerApi {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    return retrofit.create(ServerApi::class.java)
}