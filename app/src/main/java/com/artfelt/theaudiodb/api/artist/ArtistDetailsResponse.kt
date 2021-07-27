package com.artfelt.theaudiodb.api.artist

import com.artfelt.theaudiodb.models.artist.Artist
import com.google.gson.annotations.SerializedName

data class ArtistDetailsResponse(
        @SerializedName("artists")
        var artists: ArrayList<Artist>? = null,
)
