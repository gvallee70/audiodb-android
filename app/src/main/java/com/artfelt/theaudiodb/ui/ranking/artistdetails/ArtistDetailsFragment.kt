package com.artfelt.theaudiodb.ui.ranking.artistdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.api.TheAudioDBClient
import com.artfelt.theaudiodb.models.album.Album
import com.artfelt.theaudiodb.models.artist.Artist
import com.artfelt.theaudiodb.models.single.LikedSingle
import com.artfelt.theaudiodb.ui.ranking.artistdetails.album.ArtistAlbumAdapter
import com.artfelt.theaudiodb.ui.ranking.artistdetails.likedsingles.ArtistLikedSingleAdapter
import com.artfelt.theaudiodb.utils.Toolbox
import com.artfelt.theaudiodb.utils.setImageURL
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ArtistDetailsFragment: Fragment() {

    private lateinit var artistAlbumsAdapter: ArtistAlbumAdapter
    private lateinit var artistLikedSingleAdapter: ArtistLikedSingleAdapter

    private lateinit var mArtist: Artist
    private lateinit var mArtistName: String

    private lateinit var mArtistHeaderImageView: ImageView
    private lateinit var mBackArrowImageView: ImageView
    private lateinit var mArtistNameTextView: TextView
    private lateinit var mArtistPlaceTextView: TextView
    private lateinit var mArtistDescriptionTextView: TextView
    private lateinit var mArtistAlbumTitle: TextView
    private lateinit var mArtistAlbumsRecyclerView: RecyclerView
    private lateinit var mArtistSinglesLikedTitle: TextView
    private lateinit var mArtistSinglesLikedRecyclerView: RecyclerView

    companion object {
        const val ARTIST = "artist"
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mArtistName = arguments?.getString(ARTIST)!!

        mArtistHeaderImageView = view.findViewById(R.id.imageView_artist_header)
        mBackArrowImageView = view.findViewById(R.id.imageView_back_arrow)
        mArtistNameTextView = view.findViewById(R.id.textView_artist_name)
        mArtistPlaceTextView = view.findViewById(R.id.textView_artist_place)
        mArtistDescriptionTextView = view.findViewById(R.id.textView_artist_description)
        mArtistAlbumTitle = view.findViewById(R.id.textView_artist_albums_title)
        mArtistAlbumsRecyclerView = view.findViewById(R.id.recyclerView_artist_albums)
        mArtistSinglesLikedTitle = view.findViewById(R.id.textView_artist_singles_liked_title)
        mArtistSinglesLikedRecyclerView = view.findViewById(R.id.recyclerView_artist_singles_liked)


        var bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)

        bottomNavigationView?.visibility = View.GONE

        getArtistDetailsAPICall {
            mArtist = it.get(0)
            initView()
        }

        getArtistAlbumsAPICall {
            initArtistAlbumsTitle(it)
            initArtistAlbumsRecyclerView(it)
        }

        getArtistLikedSinglesAPICall {
            initArtistSinglesLikedTitle()
            initArtistSinglesLikedRecyclerView(it)
        }

    }


    private fun initView(){
        initArtistHeaderView()
        initArtistNameTextView()
        initArtistPlaceTextView()
        initArtistDescriptionTextView()

        manageOnClickBackArrow()
    }

    private fun initArtistHeaderView() {
        if(!mArtist.thumbnail.isNullOrEmpty()) {
            mArtistHeaderImageView.setImageURL(mArtist.thumbnail!!)
        }
    }

    private fun initArtistNameTextView() {
        mArtist.name.let {
            mArtistNameTextView.text = mArtist.name
        }
    }

    private fun initArtistPlaceTextView() {
        if (!(mArtist.country.isNullOrEmpty()) && !(mArtist.style.isNullOrEmpty())) {
            mArtistPlaceTextView.text = "${mArtist.country} - ${mArtist.style}"
        }
    }

    private fun initArtistDescriptionTextView() {
        mArtist.biography.let {
            mArtistDescriptionTextView.text = mArtist.biography?.format(Locale.getDefault().displayLanguage)
        }
    }


    private fun initArtistAlbumsTitle(albums: ArrayList<Album>) {
        mArtistAlbumTitle.text = getString(R.string.title_albums_count).format(albums.size)
    }


    private fun initArtistAlbumsRecyclerView(albums: ArrayList<Album>) {
        artistAlbumsAdapter = ArtistAlbumAdapter(this.requireContext(), albums)

        mArtistAlbumsRecyclerView.removeAllViews()
        mArtistAlbumsRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        mArtistAlbumsRecyclerView.adapter = artistAlbumsAdapter
    }


    private fun initArtistSinglesLikedTitle() {
        mArtistSinglesLikedTitle.text = getString(R.string.title_singles_liked)
    }


    private fun initArtistSinglesLikedRecyclerView(singlesLiked: ArrayList<LikedSingle>) {
        artistLikedSingleAdapter = ArtistLikedSingleAdapter(this.requireContext(), singlesLiked)

        val itemDecoration = DividerItemDecoration(this.requireContext(), LinearLayoutManager.VERTICAL)

        mArtistSinglesLikedRecyclerView.addItemDecoration(itemDecoration)
        mArtistSinglesLikedRecyclerView.removeAllViews()
        mArtistSinglesLikedRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        mArtistSinglesLikedRecyclerView.adapter = artistLikedSingleAdapter
    }


    private fun manageOnClickBackArrow() {
        mBackArrowImageView.setOnClickListener {
            parentFragmentManager
                    .popBackStack()
        }
    }

    private fun getArtistDetailsAPICall(complete: (ArrayList<Artist>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val getArtistDetailsResponse = TheAudioDBClient().getApiService(this@ArtistDetailsFragment.requireContext()).getArtistDetails(mArtistName!!)

                if (getArtistDetailsResponse.isSuccessful && getArtistDetailsResponse.body() != null) {

                    getArtistDetailsResponse.body()?.let {
                        complete(it.artists!!)
                    }
                }
            } catch (e: Exception) {
                println(e.message)
                Toolbox.showSimpleCustomDialog(this@ArtistDetailsFragment.requireContext(),
                        getString(R.string.label_error_api),
                        getString(R.string.action_retry),
                        null
                ) {
                    getArtistDetailsAPICall {}
                }
            }
        }
    }


    private fun getArtistAlbumsAPICall(complete: (ArrayList<Album>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val getArtistDiscographyResponse = TheAudioDBClient().getApiService(this@ArtistDetailsFragment.requireContext()).getArtistDiscography(mArtistName!!)

                if (getArtistDiscographyResponse.isSuccessful && getArtistDiscographyResponse.body() != null) {

                    getArtistDiscographyResponse.body()?.let {
                        complete(it.albums!!)
                    }
                }
            } catch (e: Exception) {
                println(e.message)
                Toolbox.showSimpleCustomDialog(this@ArtistDetailsFragment.requireContext(),
                        getString(R.string.label_error_api),
                        getString(R.string.action_retry),
                        null
                ) {
                    getArtistAlbumsAPICall {}
                }
            }
        }
    }


    private fun getArtistLikedSinglesAPICall(complete: (ArrayList<LikedSingle>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val getArtistLikedSinglesResponse = TheAudioDBClient().getApiService(this@ArtistDetailsFragment.requireContext()).getArtistLikedSingles(mArtistName!!)

                if (getArtistLikedSinglesResponse.isSuccessful && getArtistLikedSinglesResponse.body() != null) {

                    getArtistLikedSinglesResponse.body()?.let {
                        it.likedSingles?.sortByDescending {
                            it.score?.toFloat()
                        }
                        complete(it.likedSingles!!)
                    }
                }
            } catch (e: Exception) {
                println(e.message)
                Toolbox.showSimpleCustomDialog(this@ArtistDetailsFragment.requireContext(),
                        getString(R.string.label_error_api),
                        getString(R.string.action_retry),
                        null
                ) {
                    getArtistLikedSinglesAPICall {}
                }
            }
        }
    }



}