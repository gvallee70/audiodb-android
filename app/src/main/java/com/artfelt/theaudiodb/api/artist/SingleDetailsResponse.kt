package com.artfelt.theaudiodb.api.artist

import com.artfelt.theaudiodb.models.single.Single
import com.google.gson.annotations.SerializedName

data class SingleDetailsResponse(
        @SerializedName("track")
        var singles: ArrayList<Single>? = null,
)
