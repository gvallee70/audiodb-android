package com.artfelt.theaudiodb.ui.ranking.rankingalbum

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.album.RankingAlbum
import com.artfelt.theaudiodb.models.single.RankingSingle
import com.artfelt.theaudiodb.utils.setImageURL

class RankingAlbumViewHolder(
    val container: View,
    private val listener: RankingAlbumDelegate
) : RecyclerView.ViewHolder(container) {

    private var mAlbumPosition: TextView = container.findViewById(R.id.textView_id_title_ranking)
    private var mAlbumImage: ImageView = container.findViewById(R.id.imageView_title_ranking)
    private var mAlbumArtist: TextView = container.findViewById(R.id.textView_title_artist_ranking)
    private var mAlbumTitle: TextView = container.findViewById(R.id.textView_title_title_ranking)


    fun bindView(album: RankingAlbum) {
        initView(album)

        manageOnClickArtist(album)
    }


    private fun initView(album: RankingAlbum) {
        initAlbumPosition(album)
        initAlbumImage(album)
        initAlbumArtist(album)
        initAlbumTitle(album)
    }

    private fun initAlbumPosition(album: RankingAlbum) {
        mAlbumPosition.text = album.chartPlace
    }

    private fun initAlbumImage(album: RankingAlbum) {
        mAlbumImage.clipToOutline = true

        if(!album.albumThumbnail.isNullOrEmpty()) {
            mAlbumImage.setImageURL(album.albumThumbnail!!)
        }
    }

    private fun initAlbumArtist(album: RankingAlbum) {
        mAlbumArtist.text = album.artist
    }

    private fun initAlbumTitle(album: RankingAlbum) {
        mAlbumTitle.text = album.album
    }

    private fun manageOnClickArtist(album: RankingAlbum) {
        container.setOnClickListener {
            listener.onClickArtist(album)
        }
    }
}