package com.artfelt.theaudiodb.ui.ranking.artistdetails.album

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.album.Album

class ArtistAlbumViewHolder(
        val container: View,
        //private val listener: RankingSingleDelegate
) : RecyclerView.ViewHolder(container) {

    private var mAlbumImage: ImageView = container.findViewById(R.id.imageView_artist_album_cell)
    private var mAlbumName: TextView = container.findViewById(R.id.textView_artist_name)
    private var mAlbumReleasedYear: TextView = container.findViewById(R.id.textView_album_subtitle)


    fun bindView(album: Album) {
        initView(album)

        manageOnClickArtist(album)
    }


    private fun initView(album: Album) {
        initAlbumImage(album)
        initAlbumName(album)
        initAlbumDate(album)
    }


    private fun initAlbumImage(album: Album) {
        mAlbumImage.clipToOutline = true

        mAlbumImage.setImageResource(R.drawable.ic_placeholder_album)
    }

    private fun initAlbumName(album: Album) {
        album.name.let {
            mAlbumName.text = it
        }
    }

    private fun initAlbumDate(album: Album) {
        album.releasedYear.let {
            mAlbumReleasedYear.text = it
        }
    }

    private fun manageOnClickArtist(album: Album) {
        container.setOnClickListener {
            //listener.onClickArtist(album)
        }
    }
}