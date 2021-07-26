package com.artfelt.theaudiodb.ui.ranking

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artfelt.theaudiodb.R
import com.artfelt.theaudiodb.models.RankingSingle

class RankingSinglesAdapter(val context: Context,
                            private var singles: ArrayList<RankingSingle>,
                     //private val listener: ArtworkDelegate
) : RecyclerView.Adapter<RankingSingleViewHolder>() {

    var rankingSingleList = ArrayList<RankingSingle>(singles)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingSingleViewHolder {
        return RankingSingleViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_ranking_title, parent, false),
            //listener
        )
    }


    override fun onBindViewHolder(holder: RankingSingleViewHolder, position: Int) {
        val single = rankingSingleList[position]

        holder.bindView(single)
    }


    override fun getItemCount(): Int {
        return rankingSingleList.size
    }

}