package com.artfelt.theaudiodb.models.single

import com.google.gson.annotations.SerializedName

data class LikedSingle(
        @SerializedName("intScore")
        var score: String? = null,

        @SerializedName("strTrack")
        var name: String? = null,
)
