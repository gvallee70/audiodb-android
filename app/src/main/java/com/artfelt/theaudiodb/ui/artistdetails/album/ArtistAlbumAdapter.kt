package com.artfelt.theaudiodb.ui.artistdetails.album

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.album.Album
import com.artfelt.theaudiodb.ui.ranking.rankingsingle.RankingSingleDelegate
import com.artfelt.theaudiodb.ui.search.album.AlbumDelegate

class ArtistAlbumAdapter(val context: Context,
                         private var albums: ArrayList<Album>,
                         private val listener: AlbumDelegate
) : RecyclerView.Adapter<ArtistAlbumViewHolder>() {

    var artistAlbumsList = ArrayList<Album>(albums)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumViewHolder {
        return ArtistAlbumViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_album, parent, false),
                listener
        )
    }


    override fun onBindViewHolder(holder: ArtistAlbumViewHolder, position: Int) {
        val album = artistAlbumsList[position]

        holder.bindView(album)
    }


    override fun getItemCount(): Int {
        return artistAlbumsList.size
    }

}