package com.artfelt.theaudiodb.ui.search.album

import com.artfelt.theaudiodb.models.album.Album

interface AlbumDelegate {
    fun onClickAlbum(album: Album)
}