package com.artfelt.theaudiodb.models.album

import com.artfelt.theaudiodb.models.artist.Artist
import com.google.gson.annotations.SerializedName

data class Album(
        @SerializedName("strAlbum")
        var name: String? = null,

        @SerializedName("intYearReleased")
        var releasedYear: String? = null,
)
