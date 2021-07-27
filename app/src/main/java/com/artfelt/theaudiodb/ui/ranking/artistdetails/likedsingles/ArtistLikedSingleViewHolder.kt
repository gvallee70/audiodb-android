package com.artfelt.theaudiodb.ui.ranking.artistdetails.likedsingles

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.album.Album
import com.artfelt.theaudiodb.models.single.LikedSingle

class ArtistLikedSingleViewHolder(
        val container: View,
        //private val listener: RankingSingleDelegate
) : RecyclerView.ViewHolder(container) {


    var mSingleLikedId: TextView = container.findViewById(R.id.textView_id_single_liked)
    private var mSingleLikedName: TextView = container.findViewById(R.id.textView_title_single_liked)


    fun bindView(singleLiked: LikedSingle) {
        initView(singleLiked)

        manageOnClickArtist(singleLiked)
    }


    private fun initView(singleLiked: LikedSingle) {
        initSingleLikedName(singleLiked)
    }


    private fun initSingleLikedName(singleLiked: LikedSingle) {
        singleLiked.name.let {
            mSingleLikedName.text = it
        }
    }


    private fun manageOnClickArtist(singleLiked: LikedSingle) {
        container.setOnClickListener {
            //listener.onClickArtist(album)
        }
    }
}