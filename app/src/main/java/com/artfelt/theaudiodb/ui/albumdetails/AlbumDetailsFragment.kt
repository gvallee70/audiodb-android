package com.artfelt.theaudiodb.ui.albumdetails

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.api.TheAudioDBClient
import com.artfelt.theaudiodb.models.album.Album
import com.artfelt.theaudiodb.models.single.Single
import com.artfelt.theaudiodb.ui.artistdetails.singles.ArtistSingleAdapter
import com.artfelt.theaudiodb.utils.Toolbox
import com.artfelt.theaudiodb.utils.setImageURL
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class AlbumDetailsFragment: Fragment() {

    private lateinit var albumSingleAdapter: ArtistSingleAdapter

    private lateinit var mAlbum: Album
    private lateinit var mAlbumId: String

    private lateinit var mAlbumHeaderImageView: ImageView
    private lateinit var mAlbumThumbnailImageView: ImageView
    private lateinit var mBackArrowImageView: ImageView
    private lateinit var mArtistNameTextView: TextView
    private lateinit var mAlbumNameTextView: TextView
    private lateinit var mAlbumSinglesCountTextView: TextView
    private lateinit var mAlbumNotationTextView: TextView
    private lateinit var mAlbumVotesCountTextView: TextView
    private lateinit var mAlbumDescriptionTextView: TextView
    private lateinit var mAlbumSinglesTitle: TextView
    private lateinit var mSinglesRecyclerView: RecyclerView
    private lateinit var mScoreLayout: ConstraintLayout

    companion object {
        const val ALBUM = "album"
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAlbumId = arguments?.getString(ALBUM)!!

        mAlbumHeaderImageView = view.findViewById(R.id.imageView_album_header)
        mAlbumThumbnailImageView = view.findViewById(R.id.imageView_album_thumbnail)
        mBackArrowImageView = view.findViewById(R.id.imageView_back_arrow)
        mArtistNameTextView = view.findViewById(R.id.textView_artist_name)
        mAlbumNameTextView = view.findViewById(R.id.textView_album_name)
        mAlbumDescriptionTextView = view.findViewById(R.id.textView_album_description)
        mAlbumSinglesCountTextView = view.findViewById(R.id.textView_album_singles_count)
        mAlbumNotationTextView = view.findViewById(R.id.textView_album_notation)
        mAlbumVotesCountTextView = view.findViewById(R.id.textView_album_votes_count)
        mAlbumSinglesTitle = view.findViewById(R.id.textView_album_singles_title)
        mSinglesRecyclerView = view.findViewById(R.id.recyclerView_album_singles)
        mScoreLayout = view.findViewById(R.id.layout_notation_parent)


        var bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)

        bottomNavigationView?.visibility = View.GONE

        getAlbumDetailsAPICall {
            mAlbum = it.get(0)
            initView()
        }

        getAlbumSinglesDetailsAPICall {
            initSinglesTitle()
            initAlbumSinglesCountTextView(it)
            initAlbumSinglesRecyclerView(it)
        }

    }


    private fun initView() {
        initAlbumHeaderView()
        initAlbumThumbnail()
        initAlbumName()
        initAlbumArtist()
        initAlbumScoreView()
        initAlbumDescription()
        initSinglesTitle()

        manageOnClickBackArrow()
    }

    private fun initAlbumHeaderView() {
        mAlbumHeaderImageView.setColorFilter(Color.GRAY, PorterDuff.Mode.DARKEN)
        if (!mAlbum.thumbnail.isNullOrEmpty()) {
            mAlbumHeaderImageView.setImageURL(mAlbum.thumbnail!!)
        }
    }

    private fun initAlbumThumbnail() {
        mAlbumThumbnailImageView.clipToOutline = true

        if (!mAlbum.thumbnail.isNullOrEmpty()) {
            mAlbumThumbnailImageView.setImageURL(mAlbum.thumbnail!!)
        }
    }

    private fun initAlbumName() {
        mAlbum.name.let {
            mAlbumNameTextView.text = it
        }
    }


    private fun initAlbumArtist() {
        mAlbum.artist.let {
            mArtistNameTextView.text = it
        }
    }

    private fun initAlbumScoreView() {
        if(mAlbum.votes.isNullOrEmpty() || mAlbum.score.isNullOrEmpty()) {
            mScoreLayout.visibility = View.GONE
        } else {
            mAlbumVotesCountTextView.text = getString(R.string.label_votes_count).format(mAlbum.votes)
            mAlbumNotationTextView.text = mAlbum.score

        }
    }


    private fun initAlbumSinglesCountTextView(singles: ArrayList<Single>) {
        mAlbumSinglesCountTextView.text = getString(R.string.label_singles_count).format(singles.size)
    }

    private fun initAlbumDescription() {
        mAlbum.description.let {
            mAlbumDescriptionTextView.text = it
        }
    }

    private fun initSinglesTitle() {
        mAlbumSinglesTitle.text = getString(R.string.label_titles)
    }



    private fun initAlbumSinglesRecyclerView(singles: ArrayList<Single>) {
        albumSingleAdapter = ArtistSingleAdapter(this.requireContext(), singles)

        val itemDecoration = DividerItemDecoration(this.requireContext(), LinearLayoutManager.VERTICAL)

        mSinglesRecyclerView.addItemDecoration(itemDecoration)
        mSinglesRecyclerView.removeAllViews()
        mSinglesRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        mSinglesRecyclerView.adapter = albumSingleAdapter
    }


    private fun manageOnClickBackArrow() {
        mBackArrowImageView.setOnClickListener {
            parentFragmentManager
                    .popBackStack()
        }
    }

    private fun getAlbumDetailsAPICall(complete: (ArrayList<Album>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val getAlbumDetailsResponse = TheAudioDBClient().getApiService(this@AlbumDetailsFragment.requireContext()).getAlbumDetails(mAlbumId)

                if (getAlbumDetailsResponse.isSuccessful && getAlbumDetailsResponse.body() != null) {

                    getAlbumDetailsResponse.body()?.let {
                        complete(it.albums!!)
                    }
                }
            } catch (e: Exception) {
                println(e.message)
                Toolbox.showSimpleCustomDialog(this@AlbumDetailsFragment.requireContext(),
                        getString(R.string.label_error_api),
                        getString(R.string.action_retry),
                        null
                ) {
                    getAlbumDetailsAPICall {}
                }
            }
        }
    }




    private fun getAlbumSinglesDetailsAPICall(complete: (ArrayList<Single>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val getAlbumSinglesDetailsResponse = TheAudioDBClient().getApiService(this@AlbumDetailsFragment.requireContext()).getAlbumSinglesDetails(mAlbumId!!)

                if (getAlbumSinglesDetailsResponse.isSuccessful && getAlbumSinglesDetailsResponse.body() != null) {

                    getAlbumSinglesDetailsResponse.body()?.let {
                        it.singles?.sortByDescending {
                            it.score?.toFloat()
                        }
                        complete(it.singles!!)
                    }
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

}