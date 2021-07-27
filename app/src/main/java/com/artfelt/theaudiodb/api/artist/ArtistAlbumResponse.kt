package com.artfelt.theaudiodb.api.artist

import com.artfelt.theaudiodb.models.album.Album
import com.google.gson.annotations.SerializedName

data class ArtistAlbumResponse(
        @SerializedName("album")
        var albums: ArrayList<Album>? = null,
)
