package com.artfelt.theaudiodb.ui.ranking

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.RankingAlbum
import com.artfelt.theaudiodb.models.RankingSingle

class RankingAlbumsAdapter(val context: Context,
                            private var albums: ArrayList<RankingAlbum>,
    //private val listener: ArtworkDelegate
) : RecyclerView.Adapter<RankingAlbumViewHolder>() {

    var rankingAlbumList = ArrayList<RankingAlbum>(albums)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingAlbumViewHolder {
        return RankingAlbumViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_ranking_title, parent, false),
            //listener
        )
    }


    override fun onBindViewHolder(holder: RankingAlbumViewHolder, position: Int) {
        val album = rankingAlbumList[position]

        holder.bindView(album)
    }


    override fun getItemCount(): Int {
        return rankingAlbumList.size
    }

}