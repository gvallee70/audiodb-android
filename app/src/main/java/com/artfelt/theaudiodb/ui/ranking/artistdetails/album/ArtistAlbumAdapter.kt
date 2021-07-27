package com.artfelt.theaudiodb.ui.ranking.artistdetails.album

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.album.Album

class ArtistAlbumAdapter(val context: Context,
                         private var albums: ArrayList<Album>,
                         //private val listener: RankingSingleDelegate
) : RecyclerView.Adapter<ArtistAlbumViewHolder>() {

    var artistAlbumsList = ArrayList<Album>(albums)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumViewHolder {
        return ArtistAlbumViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_artist_album, parent, false),
                //listener
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