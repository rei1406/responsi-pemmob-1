package com.example.responsi1mobileh1d023077.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.football-data.org/v4/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val footballApi: FootballApi by lazy {
        retrofit.create(FootballApi::class.java)
    }
}