package com.artfelt.theaudiodb.api.artist

import com.artfelt.theaudiodb.models.album.Album
import com.artfelt.theaudiodb.models.single.LikedSingle
import com.google.gson.annotations.SerializedName

data class ArtistLikedSinglesResponse(
        @SerializedName("track")
        var likedSingles: ArrayList<LikedSingle>? = null,
)
