package com.artfelt.theaudiodb.api

import com.artfelt.theaudiodb.api.ranking.TrendingAlbumsResponse
import com.artfelt.theaudiodb.api.ranking.TrendingSinglesResponse
import com.artfelt.theaudiodb.api.album.AlbumDetailsResponse
import com.artfelt.theaudiodb.api.artist.ArtistDetailsResponse
import com.artfelt.theaudiodb.api.single.SingleDetailsResponse
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


    @GET("searchalbum.php")
    suspend fun getArtistAlbumsDetails(@Query("s") artistName: String): Response<AlbumDetailsResponse>


    @GET("track-top10.php")
    suspend fun getArtistLikedSingles(@Query("s") artistName: String): Response<SingleDetailsResponse>


    @GET("album.php")
    suspend fun getAlbumDetails(@Query("m") albumId: String): Response<AlbumDetailsResponse>


    @GET("track.php")
    suspend fun getAlbumSinglesDetails(@Query("m") albumId: String): Response<SingleDetailsResponse>
}