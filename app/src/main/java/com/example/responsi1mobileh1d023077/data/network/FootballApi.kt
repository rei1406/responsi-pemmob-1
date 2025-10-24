package com.example.responsi1mobileh1d023077.data.network

import com.example.responsi1mobileh1d023077.data.model.ClubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface FootballApi {
    @GET("teams/341")
    @Headers("X-Auth-Token: 6505db15c226437c84a23df9a2556c3a")
    suspend fun getClubInfo(): Response<ClubResponse>
}