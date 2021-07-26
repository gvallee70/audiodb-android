package com.artfelt.theaudiodb.api

import com.artfelt.theaudiodb.api.ranking.TrendingAlbumsResponse
import com.artfelt.theaudiodb.api.ranking.TrendingSinglesResponse
import com.artfelt.theaudiodb.models.RankingSingle
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TheAudioDBApiService {
    @GET("trending.php?country=us&type=itunes&format=singles")
    suspend fun getUSTrendingSingles(): Response<TrendingSinglesResponse>


    @GET("trending.php?country=us&type=itunes&format=albums")
    suspend fun getUSTrendingAlbums(): Response<TrendingAlbumsResponse>
}