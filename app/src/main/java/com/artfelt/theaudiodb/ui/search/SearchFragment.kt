package com.artfelt.theaudiodb.ui.search

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artfelt.theaudiodb.R

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var searchTitleTextView: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchTitleTextView = view.findViewById(R.id.textView_title_search)


       /* searchViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        initView()

        return view
    }

    private fun initView() {
        initSearchTitle()
    }

    private fun initSearchTitle() {
        searchTitleTextView.setTextColor(resources.getColor(R.color.black))
        searchTitleTextView.typeface = Typeface.DEFAULT_BOLD
        searchTitleTextView.text = getString(R.string.title_search)
    }
}