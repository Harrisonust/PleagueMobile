package com.example.gamechangermobile.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.TeamID
import com.example.gamechangermobile.models.getTeamById


class FavTeamsAdapter(val teamList: List<TeamID>) :
    RecyclerView.Adapter<FavTeamsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teamImageView = itemView.findViewById<ImageView>(R.id.team_icon)
        val teamNameTextView = itemView.findViewById<TextView>(R.id.team_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fav_team_item, parent, false)
        val viewHolder = ViewHolder(view)

//        viewHolder.itemView.setOnClickListener {
//
//        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = getTeamById(teamList[position])
        team?.profilePic?.let {
            holder.teamImageView.setImageResource(it)
        }
        team?.name?.let {
            holder.teamNameTextView.text = it
        }
    }

    override fun getItemCount() = teamList.size
}
