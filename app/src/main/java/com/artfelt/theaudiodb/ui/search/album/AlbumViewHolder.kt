package com.artfelt.theaudiodb.ui.search.album

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.album.Album
import com.artfelt.theaudiodb.models.artist.Artist
import com.artfelt.theaudiodb.models.single.RankingSingle
import com.artfelt.theaudiodb.ui.ranking.rankingsingle.RankingSingleDelegate
import com.artfelt.theaudiodb.utils.setImageURL

class AlbumViewHolder(
        val container: View,
        private val listener: AlbumDelegate
) : RecyclerView.ViewHolder(container) {

    private var mAlbumImage: ImageView = container.findViewById(R.id.imageView_artist_album_cell)
    private var mAlbumTitle: TextView = container.findViewById(R.id.textView_artist_name)
    private var mAlbumArtist: TextView = container.findViewById(R.id.textView_album_subtitle)


    fun bindView(album: Album) {
        initView(album)

        manageOnClickAlbum(album)
    }


    private fun initView(album: Album) {
        initAlbumImage(album)
        initAlbumName(album)
        initAlbumArtistName(album)
    }


    private fun initAlbumImage(album: Album) {
        mAlbumImage.clipToOutline = true

        if(!album.thumbnail.isNullOrEmpty()) {
            mAlbumImage.setImageURL(album.thumbnail!!)
        }
    }

    private fun initAlbumName(album: Album) {
        mAlbumTitle.text = album.name
    }

    private fun initAlbumArtistName(album: Album) {
        mAlbumArtist.text = album.artist
    }


    private fun manageOnClickAlbum(album: Album) {
        container.setOnClickListener {
            listener.onClickAlbum(album)
        }
    }
}