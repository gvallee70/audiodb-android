package com.artfelt.theaudiodb.ui.search

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.api.TheAudioDBClient
import com.artfelt.theaudiodb.models.album.Album
import com.artfelt.theaudiodb.models.artist.Artist
import com.artfelt.theaudiodb.ui.ranking.RankingFragment
import com.artfelt.theaudiodb.ui.ranking.artistdetails.ArtistDetailsFragment
import com.artfelt.theaudiodb.ui.ranking.rankingsingle.RankingSinglesAdapter
import com.artfelt.theaudiodb.ui.search.album.AlbumAdapter
import com.artfelt.theaudiodb.ui.search.artist.ArtistAdapter
import com.artfelt.theaudiodb.ui.search.artist.ArtistDelegate
import com.artfelt.theaudiodb.utils.Toolbox
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment(), ArtistDelegate {


    companion object {
        const val ARTIST = "artist"
    }

    private lateinit var artistName: String

    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var albumAdapter: AlbumAdapter

    private lateinit var mSearchTitleTextView: TextView
    private lateinit var mSearchView: SearchView
    private lateinit var mArtistTitleTextView: TextView
    private lateinit var mArtistRecyclerView: RecyclerView
    private lateinit var mAlbumTitleTextView: TextView
    private lateinit var mAlbumRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)

        bottomNavigationView?.visibility = View.VISIBLE

        mSearchTitleTextView = view.findViewById(R.id.textView_title_search)
        mSearchView = view.findViewById(R.id.search_view)
        mArtistTitleTextView = view.findViewById(R.id.textView_artist_title)
        mArtistRecyclerView = view.findViewById(R.id.recyclerView_artists)
        mAlbumTitleTextView = view.findViewById(R.id.textView_albums_title)
        mAlbumRecyclerView = view.findViewById(R.id.recyclerView_albums)

        initView()
    }

    private fun initView() {
        initSearchTitle()
        initSearchViewUI()
        initSearchViewUX()
        initArtistsTitle()
        initAlbumsTitle()
    }

    private fun initSearchTitle() {
        mSearchTitleTextView.setTextColor(resources.getColor(R.color.black))
        mSearchTitleTextView.typeface = Typeface.DEFAULT_BOLD
        mSearchTitleTextView.text = getString(R.string.title_search)
    }

    private fun initSearchViewUI() {

        mSearchView.queryHint = getString(R.string.placeholder_search)

        val searchIcon = mSearchView.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setImageResource(R.drawable.ic_search_search_view)

        val cancelIcon = mSearchView.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setImageResource(R.drawable.ic_search_cancel)

        val textView = mSearchView.findViewById<TextView>(R.id.search_src_text)
        textView.textSize = 16f

    }

    private fun initSearchViewUX(){
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty()) {
                    artistName = newText!!
                    getArtistDetailsAPICall {
                        initArtistsRecyclerView(it)
                    }

                    getArtistAlbumsDetailsAPICall {
                        initAlbumsRecyclerView(it)
                    }
                } else {
                    mArtistRecyclerView.visibility = View.INVISIBLE
                    mAlbumRecyclerView.visibility = View.INVISIBLE
                }
                return false
            }
        })
    }


    private fun initArtistsTitle() {
        mArtistTitleTextView.text = getString(R.string.label_artists)
    }

    private fun initArtistsRecyclerView(artists: ArrayList<Artist>) {
        mArtistRecyclerView.visibility = View.VISIBLE

        artistAdapter = ArtistAdapter(this.requireContext(), artists, this)

        mArtistRecyclerView.removeAllViews()
        mArtistRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        mArtistRecyclerView.adapter = artistAdapter
    }



    private fun initAlbumsTitle() {
        mAlbumTitleTextView.text = getString(R.string.label_albums)
    }

    private fun initAlbumsRecyclerView(albums: ArrayList<Album>) {
        mAlbumRecyclerView.visibility = View.VISIBLE

        albumAdapter = AlbumAdapter(this.requireContext(), albums)

        mAlbumRecyclerView.removeAllViews()
        mAlbumRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        mAlbumRecyclerView.adapter = albumAdapter
    }


    private fun getArtistDetailsAPICall(complete: (ArrayList<Artist>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val getArtistDetailsResponse = TheAudioDBClient().getApiService(this@SearchFragment.requireContext()).getArtistDetails(artistName)

                if (getArtistDetailsResponse.isSuccessful && getArtistDetailsResponse.body() != null) {

                    getArtistDetailsResponse.body()?.let {
                        complete(it.artists!!)
                    }
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }


    private fun getArtistAlbumsDetailsAPICall(complete: (ArrayList<Album>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val getArtistAlbumsDetailsResponse = TheAudioDBClient().getApiService(this@SearchFragment.requireContext()).getArtistAlbumsDetails(artistName)

                if (getArtistAlbumsDetailsResponse.isSuccessful && getArtistAlbumsDetailsResponse.body() != null) {

                    getArtistAlbumsDetailsResponse.body()?.let {
                        complete(it.albums!!)
                    }
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    override fun onClickArtist(artist: Artist) {
        val fragment = ArtistDetailsFragment()
        val args = Bundle()
        args.putString(ARTIST, artist.name)
        fragment.arguments = args

        parentFragmentManager
                .beginTransaction()
                .addToBackStack("SearchFragment")
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
    }


}