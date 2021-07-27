package com.artfelt.theaudiodb.ui.ranking.artistdetails.likedsingles

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.album.Album
import com.artfelt.theaudiodb.models.single.LikedSingle

class ArtistLikedSingleAdapter(val context: Context,
                               private var singlesLiked: ArrayList<LikedSingle>,
        //private val listener: RankingSingleDelegate
) : RecyclerView.Adapter<ArtistLikedSingleViewHolder>() {

    var artistSinglesLikedList = ArrayList<LikedSingle>(singlesLiked)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistLikedSingleViewHolder {
        return ArtistLikedSingleViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_artist_single_liked, parent, false),
                //listener
        )
    }


    override fun onBindViewHolder(holder: ArtistLikedSingleViewHolder, position: Int) {
        val singleLiked = artistSinglesLikedList[position]

        holder.bindView(singleLiked)
        holder.mSingleLikedId.text = "${position + 1}"
    }


    override fun getItemCount(): Int {
        return artistSinglesLikedList.size
    }

}