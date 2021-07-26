package com.artfelt.theaudiodb.api.ranking

import com.artfelt.theaudiodb.models.RankingAlbum
import com.artfelt.theaudiodb.models.RankingSingle
import com.google.gson.annotations.SerializedName

data class TrendingAlbumsResponse(
    @SerializedName("trending")
    var trending: ArrayList<RankingAlbum>? = null,
)
