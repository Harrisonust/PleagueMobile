package com.example.gamechangermobile.gamepage

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.models.Highlight

//lass GameAdapter(val gameList: List<Game>): RecyclerView.Adapter<GameAdapter.ViewHolder>() {
class HighlightAdapter(val highlightList: List<Highlight>):RecyclerView.Adapter<HighlightAdapter.ViewHolder>() {

    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HighlightAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}