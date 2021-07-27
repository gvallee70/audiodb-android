package com.artfelt.theaudiodb.ui.search.artist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.artist.Artist
import com.artfelt.theaudiodb.models.single.RankingSingle
import com.artfelt.theaudiodb.ui.ranking.rankingsingle.RankingSingleDelegate
import com.artfelt.theaudiodb.utils.setImageURL

class ArtistViewHolder(
        val container: View,
        private val listener: ArtistDelegate
) : RecyclerView.ViewHolder(container) {

    private var mArtistImage: ImageView = container.findViewById(R.id.imageView_artist_cell)
    private var mArtistName: TextView = container.findViewById(R.id.textView_artist_name)


    fun bindView(artist: Artist) {
        initView(artist)

        manageOnClickArtist(artist)
    }


    private fun initView(artist: Artist) {
        initArtistImage(artist)
        initArtistName(artist)
    }


    private fun initArtistImage(artist: Artist) {
        mArtistImage.clipToOutline = true

        if(!artist.thumbnail.isNullOrEmpty()) {
            mArtistImage.setImageURL(artist.thumbnail!!)
        }
    }

    private fun initArtistName(artist: Artist) {
        mArtistName.text = artist.name
    }



    private fun manageOnClickArtist(artist: Artist) {
        container.setOnClickListener {
            listener.onClickArtist(artist)
        }
    }
}