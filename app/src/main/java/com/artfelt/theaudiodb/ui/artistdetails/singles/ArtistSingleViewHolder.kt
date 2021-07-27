package com.artfelt.theaudiodb.ui.artistdetails.singles

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.single.Single

class ArtistSingleViewHolder(
        val container: View,
        //private val listener: RankingSingleDelegate
) : RecyclerView.ViewHolder(container) {


    var mSingleLikedId: TextView = container.findViewById(R.id.textView_id_single_liked)
    private var mSingleLikedName: TextView = container.findViewById(R.id.textView_title_single_liked)


    fun bindView(single: Single) {
        initView(single)
    }


    private fun initView(single: Single) {
        initSingleLikedName(single)
    }


    private fun initSingleLikedName(single: Single) {
        single.name.let {
            mSingleLikedName.text = it
        }
    }

}