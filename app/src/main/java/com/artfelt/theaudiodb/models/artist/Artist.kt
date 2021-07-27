package com.artfelt.theaudiodb.models.artist

import com.google.gson.annotations.SerializedName

data class Artist(
        @SerializedName("idArtist")
        var id: String? = null,

        @SerializedName("strArtist")
        var name: String? = null,

        @SerializedName("strCountry")
        var country: String? = null,

        @SerializedName("strStyle")
        var style: String? = null,

        @SerializedName("strBiographyEN")
        var biography: String? = null,

        @SerializedName("strArtistThumb")
        var thumbnail: String? = null,
)
