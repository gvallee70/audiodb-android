package com.artfelt.theaudiodb.ui.favorites

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
import com.artfelt.theaudiodb.ui.search.SearchViewModel

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel

    private lateinit var favoritesTitleTextView: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel =
                ViewModelProvider(this).get(FavoritesViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        favoritesTitleTextView =  view.findViewById(R.id.textView_title_favorites)

        /*favoritesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        initView()

        return view
    }

    private fun initView() {
        initFavoritesTitle()
    }

    private fun initFavoritesTitle() {
        favoritesTitleTextView.setTextColor(resources.getColor(R.color.black))
        favoritesTitleTextView.typeface = Typeface.DEFAULT_BOLD
        favoritesTitleTextView.text = getString(R.string.title_favorites)
    }
}