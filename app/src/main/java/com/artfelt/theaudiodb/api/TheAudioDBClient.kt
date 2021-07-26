package com.artfelt.theaudiodb.api

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheAudioDBClient {
    private lateinit var apiService: TheAudioDBApiService

    fun getApiService(context: Context): TheAudioDBApiService {
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://theaudiodb.com/api/v1/json/523532/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(TheAudioDBApiService::class.java)
        }
        return apiService
    }
}