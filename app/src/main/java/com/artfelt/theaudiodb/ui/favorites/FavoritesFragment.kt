package com.artfelt.theaudiodb.ui.favorites

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoritesFragment : Fragment() {

    private lateinit var favoritesTitleTextView: TextView
    private lateinit var favoritesSubtitleTextView: TextView
    private lateinit var mArtistTitleTextView: TextView
    private lateinit var mArtistRecyclerView: RecyclerView
    private lateinit var mAlbumTitleTextView: TextView
    private lateinit var mAlbumRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favorites, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)

        bottomNavigationView?.visibility = View.VISIBLE

        favoritesTitleTextView = view.findViewById(R.id.textView_title_favorites)
        favoritesSubtitleTextView = view.findViewById(R.id.textView_favorites_subtitle)
        mArtistTitleTextView = view.findViewById(R.id.textView_artist_title)
        mArtistRecyclerView = view.findViewById(R.id.recyclerView_artists)
        mAlbumTitleTextView = view.findViewById(R.id.textView_albums_title)
        mAlbumRecyclerView = view.findViewById(R.id.recyclerView_albums)

        initView()

    }

    private fun initView() {
        initFavoritesTitle()
        initFavoritesSubtitle()
        initArtistsTitle()
        initAlbumsTitle()
    }

    private fun initFavoritesTitle() {
        favoritesTitleTextView.setTextColor(resources.getColor(R.color.black))
        favoritesTitleTextView.typeface = Typeface.DEFAULT_BOLD
        favoritesTitleTextView.text = getString(R.string.title_favorites)
    }

    private fun initFavoritesSubtitle() {
        favoritesSubtitleTextView.text = getString(R.string.label_my_favorites)
    }

    private fun initArtistsTitle() {
        mArtistTitleTextView.text = getString(R.string.label_artists)
    }

    private fun initAlbumsTitle() {
        mAlbumTitleTextView.text = getString(R.string.label_albums)
    }

}