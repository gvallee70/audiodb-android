package com.artfelt.theaudiodb.api.artist

import com.artfelt.theaudiodb.models.album.Album
import com.google.gson.annotations.SerializedName

data class AlbumDetailsResponse(
        @SerializedName("album")
        var albums: ArrayList<Album>? = null,
)
