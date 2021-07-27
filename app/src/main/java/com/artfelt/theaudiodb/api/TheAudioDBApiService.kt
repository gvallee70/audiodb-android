package com.artfelt.theaudiodb.api

import com.artfelt.theaudiodb.api.ranking.TrendingAlbumsResponse
import com.artfelt.theaudiodb.api.ranking.TrendingSinglesResponse
import com.artfelt.theaudiodb.api.artist.ArtistAlbumResponse
import com.artfelt.theaudiodb.api.artist.ArtistDetailsResponse
import com.artfelt.theaudiodb.api.artist.ArtistLikedSinglesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheAudioDBApiService {
    @GET("trending.php?country=us&type=itunes&format=singles")
    suspend fun getUSTrendingSingles(): Response<TrendingSinglesResponse>


    @GET("trending.php?country=us&type=itunes&format=albums")
    suspend fun getUSTrendingAlbums(): Response<TrendingAlbumsResponse>


    @GET("search.php")
    suspend fun getArtistDetails(@Query("s") artistName: String): Response<ArtistDetailsResponse>


    @GET("discography.php")
    suspend fun getArtistDiscography(@Query("s") artistName: String): Response<ArtistAlbumResponse>


    @GET("track-top10.php")
    suspend fun getArtistLikedSingles(@Query("s") artistName: String): Response<ArtistLikedSinglesResponse>

}