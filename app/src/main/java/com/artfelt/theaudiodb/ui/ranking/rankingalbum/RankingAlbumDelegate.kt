package com.artfelt.theaudiodb.ui.ranking.rankingalbum

import com.artfelt.theaudiodb.models.album.RankingAlbum
import com.artfelt.theaudiodb.models.single.RankingSingle

interface RankingAlbumDelegate {
    fun onClickAlbum(album: RankingAlbum)

}