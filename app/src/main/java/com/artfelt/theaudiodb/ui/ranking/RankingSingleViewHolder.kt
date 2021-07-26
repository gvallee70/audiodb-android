package com.artfelt.theaudiodb.ui.ranking

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.RankingSingle
import com.artfelt.theaudiodb.utils.setImageURL

class RankingSingleViewHolder(
    val container: View,
    //private val listener: ArtworkDelegate
) : RecyclerView.ViewHolder(container) {

    private var mSinglePosition: TextView = container.findViewById(R.id.textView_id_title_ranking)
    private var mSingleImage: ImageView = container.findViewById(R.id.imageView_title_ranking)
    private var mSingleArtist: TextView = container.findViewById(R.id.textView_title_artist_ranking)
    private var mSingleTitle: TextView = container.findViewById(R.id.textView_title_title_ranking)


    fun bindView(single: RankingSingle) {
        initView(single)

    }


    private fun initView(single: RankingSingle) {
        initSinglePosition(single)
        initSingleImage(single)
        initSingleArtist(single)
        initSingleTitle(single)
    }

    private fun initSinglePosition(single: RankingSingle) {
        mSinglePosition.text = single.chartPlace
    }

    private fun initSingleImage(single: RankingSingle) {
        mSingleImage.clipToOutline = true

        if(!single.trackThumbnail.isNullOrEmpty()) {
            mSingleImage.setImageURL(single.trackThumbnail!!)
        }
    }

    private fun initSingleArtist(single: RankingSingle) {
        mSingleArtist.text = single.artist
    }

    private fun initSingleTitle(single: RankingSingle) {
        mSingleTitle.text = single.track
    }
}