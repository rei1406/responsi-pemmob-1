package com.example.responsi1mobileh1d023077.data.repository

import com.example.responsi1mobileh1d023077.data.model.ClubResponse
import com.example.responsi1mobileh1d023077.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FootballRepository {
    
    suspend fun getClubInfo(): Result<ClubResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient.footballApi.getClubInfo()
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}