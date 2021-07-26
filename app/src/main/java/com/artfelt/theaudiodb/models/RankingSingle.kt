package com.artfelt.theaudiodb.models

import com.google.gson.annotations.SerializedName

data class RankingSingle(
    @SerializedName("intChartPlace")
    var chartPlace: String? = null,

    @SerializedName("strArtist")
    var artist: String? = null,

    @SerializedName("strTrack")
    var track: String? = null,

    @SerializedName("strTrackThumb")
    var trackThumbnail: String? = null,



)