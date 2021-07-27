package com.artfelt.theaudiodb.ui.search.artist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.artist.Artist
import com.artfelt.theaudiodb.models.single.RankingSingle
import com.artfelt.theaudiodb.ui.ranking.rankingsingle.RankingSingleDelegate
import com.artfelt.theaudiodb.ui.ranking.rankingsingle.RankingSingleViewHolder

class ArtistAdapter(val context: Context,
                    private var artists: ArrayList<Artist>,
                    private val listener: ArtistDelegate
) : RecyclerView.Adapter<ArtistViewHolder>() {

    var artistsList = ArrayList<Artist>(artists)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_artist, parent, false),
                listener
        )
    }


    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artistsList[position]

        holder.bindView(artist)
    }


    override fun getItemCount(): Int {
        return artistsList.size
    }
}