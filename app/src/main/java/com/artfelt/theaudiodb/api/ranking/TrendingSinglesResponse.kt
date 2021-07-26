package com.artfelt.theaudiodb.api.ranking

import com.artfelt.theaudiodb.models.RankingSingle
import com.google.gson.annotations.SerializedName

data class TrendingSinglesResponse(
    @SerializedName("trending")
    var trending: ArrayList<RankingSingle>? = null,
)
