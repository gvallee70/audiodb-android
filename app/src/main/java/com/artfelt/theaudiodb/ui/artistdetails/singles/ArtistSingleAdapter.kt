package com.artfelt.theaudiodb.ui.artistdetails.singles

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.single.Single

class ArtistSingleAdapter(val context: Context,
                          private var singles: ArrayList<Single>,
        //private val listener: RankingSingleDelegate
) : RecyclerView.Adapter<ArtistSingleViewHolder>() {

    var artistSinglesLikedList = ArrayList<Single>(singles)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistSingleViewHolder {
        return ArtistSingleViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_artist_single_liked, parent, false),
                //listener
        )
    }


    override fun onBindViewHolder(holder: ArtistSingleViewHolder, position: Int) {
        val singleLiked = artistSinglesLikedList[position]

        holder.bindView(singleLiked)
        holder.mSingleLikedId.text = "${position + 1}"
    }


    override fun getItemCount(): Int {
        return artistSinglesLikedList.size
    }

}