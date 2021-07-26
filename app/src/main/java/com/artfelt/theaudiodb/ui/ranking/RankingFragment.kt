package com.artfelt.theaudiodb.ui.ranking

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.api.TheAudioDBClient
import com.artfelt.theaudiodb.models.RankingAlbum
import com.artfelt.theaudiodb.models.RankingSingle
import com.artfelt.theaudiodb.utils.Toolbox
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RankingFragment : Fragment() {

    private lateinit var rankingViewModel: RankingViewModel
    private lateinit var rankingSinglesAdapter: RankingSinglesAdapter
    private lateinit var rankingAlbumsAdapter: RankingAlbumsAdapter

    private lateinit var mRankingTitleTextView: TextView
    private lateinit var mRankingLoadingTextView: TextView
    private lateinit var mRankingLoadingProgressBar: ProgressBar
    private lateinit var mRankingRecyclerView: RecyclerView

    private lateinit var mTabLayout: TabLayout
    private lateinit var mTabItemTitles: TabItem
    private lateinit var mTabItemAlbums: TabItem


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rankingViewModel = ViewModelProvider(this).get(RankingViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_ranking, container, false)

        mRankingTitleTextView = view.findViewById(R.id.textView_title_ranking)
        mRankingLoadingTextView = view.findViewById(R.id.textView_titles_loading)
        mRankingLoadingProgressBar = view.findViewById(R.id.progressBar_titles_loading)
        mRankingRecyclerView = view.findViewById(R.id.recyclerView_ranking)
        mTabLayout = view.findViewById(R.id.tabLayout_ranking)

        initView()

        return view
    }

    private fun initView() {
        initRankingTitle()

        getUSTrendingSinglesAPICall()
        getUSTrendingAlbumsAPICall()
    }

    private fun initRankingTitle() {
        mRankingTitleTextView.setTextColor(resources.getColor(R.color.black))
        mRankingTitleTextView.typeface = Typeface.DEFAULT_BOLD
        mRankingTitleTextView.text = getString(R.string.title_ranking)
    }

    private fun initTabBarTitles() {


    }


    private fun initSinglesRecyclerView(rankingSingles: ArrayList<RankingSingle>) {
        rankingSinglesAdapter = RankingSinglesAdapter(this.requireContext(), rankingSingles)

        mRankingRecyclerView.removeAllViews()
        mRankingRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        mRankingRecyclerView.adapter = rankingSinglesAdapter
    }


    private fun initAlbumsRecyclerView(rankingAlbums: ArrayList<RankingAlbum>) {
        rankingAlbumsAdapter = RankingAlbumsAdapter(this.requireContext(), rankingAlbums)

        mRankingRecyclerView.removeAllViews()
        mRankingRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        mRankingRecyclerView.adapter = rankingAlbumsAdapter
    }

    private fun initLoadingDataView() {
        mRankingLoadingTextView.text = getString(R.string.label_loading_data)
        mRankingLoadingTextView.visibility = View.VISIBLE
        mRankingLoadingProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoadingDataView() {
        mRankingLoadingTextView.visibility = View.GONE
        mRankingLoadingProgressBar.visibility = View.GONE
    }


    private fun manageOnClickTabLayout(rankingSingles: ArrayList<RankingSingle>? = null, rankingAlbums: ArrayList<RankingAlbum>? = null) {
        mTabLayout.addOnTabSelectedListener (object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        rankingSingles?.let {
                            initSinglesRecyclerView(it)
                        }
                    }
                    1 -> {
                        rankingAlbums?.let {
                            initAlbumsRecyclerView(it)
                        }
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }

    private fun getUSTrendingSinglesAPICall() {
        initLoadingDataView()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val getTrendingResponse = TheAudioDBClient().getApiService(this@RankingFragment.requireContext()).getUSTrendingSingles()

                if (getTrendingResponse.isSuccessful && getTrendingResponse.body() != null) {
                    hideLoadingDataView()

                    getTrendingResponse.body()?.let {
                        it.trending?.sortBy {
                            it.chartPlace?.toInt()
                        }
                        manageOnClickTabLayout(rankingSingles = it.trending!!)
                        initSinglesRecyclerView(it.trending!!)
                    }
                }
            } catch (e: Exception) {
                println(e.message)
                Toolbox.showSimpleCustomDialog(this@RankingFragment.requireContext(),
                    getString(R.string.label_error_api),
                    getString(R.string.action_retry),
                    null,
                    {
                        getUSTrendingSinglesAPICall()
                    }
                )
            }
        }
    }


    private fun getUSTrendingAlbumsAPICall() {
        initLoadingDataView()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val getTrendingResponse = TheAudioDBClient().getApiService(this@RankingFragment.requireContext()).getUSTrendingAlbums()

                if (getTrendingResponse.isSuccessful && getTrendingResponse.body() != null) {
                    hideLoadingDataView()

                    getTrendingResponse.body()?.let {
                        it.trending?.sortBy {
                            it.chartPlace?.toInt()
                        }

                        manageOnClickTabLayout(rankingAlbums = it.trending!!)
                        initAlbumsRecyclerView(it.trending!!)
                    }
                }
            } catch (e: Exception) {
                println(e.message)
                Toolbox.showSimpleCustomDialog(this@RankingFragment.requireContext(),
                    getString(R.string.label_error_api),
                    getString(R.string.action_retry),
                    null,
                    {
                        getUSTrendingAlbumsAPICall()
                    }
                )
            }
        }
    }
}