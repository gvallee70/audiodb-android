package com.artfelt.theaudiodb.models.album

import com.artfelt.theaudiodb.models.artist.Artist
import com.google.gson.annotations.SerializedName

data class Album(
        @SerializedName("idAlbum")
        var id: String? = null,

        @SerializedName("strAlbum")
        var name: String? = null,

        @SerializedName("strArtist")
        var artist: String? = null,

        @SerializedName("strAlbumThumb")
        var thumbnail: String? = null,

        @SerializedName("intYearReleased")
        var releasedYear: String? = null,
)
