package com.artfelt.theaudiodb.ui.search.artist

import com.artfelt.theaudiodb.models.artist.Artist

interface ArtistDelegate {
    fun onClickArtist(artist: Artist)
}