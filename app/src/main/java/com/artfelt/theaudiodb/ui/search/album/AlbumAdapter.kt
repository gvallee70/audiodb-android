package com.artfelt.theaudiodb.ui.search.album

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.album.Album
import com.artfelt.theaudiodb.models.artist.Artist
import com.artfelt.theaudiodb.ui.search.artist.ArtistViewHolder

class AlbumAdapter(val context: Context,
                   private var albums: ArrayList<Album>,
        //private val listener: RankingSingleDelegate
) : RecyclerView.Adapter<AlbumViewHolder>() {

    var albumsList = ArrayList<Album>(albums)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_album, parent, false),
                //listener
        )
    }


    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumsList[position]

        holder.bindView(album)
    }


    override fun getItemCount(): Int {
        return albumsList.size
    }
}