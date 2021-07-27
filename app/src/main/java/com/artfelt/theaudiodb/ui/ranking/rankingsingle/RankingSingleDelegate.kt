package com.artfelt.theaudiodb.ui.ranking.rankingsingle

import com.artfelt.theaudiodb.models.single.RankingSingle

interface RankingSingleDelegate {
    fun onClickArtist(single: RankingSingle)
}