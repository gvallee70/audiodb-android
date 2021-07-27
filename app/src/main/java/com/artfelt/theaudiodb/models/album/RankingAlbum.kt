package com.artfelt.theaudiodb.models.album

import com.google.gson.annotations.SerializedName

data class RankingAlbum(
    @SerializedName("intChartPlace")
    var chartPlace: String? = null,

    @SerializedName("strArtist")
    var artist: String? = null,

    @SerializedName("strAlbum")
    var album: String? = null,

    @SerializedName("strAlbumThumb")
    var albumThumbnail: String? = null,
)
