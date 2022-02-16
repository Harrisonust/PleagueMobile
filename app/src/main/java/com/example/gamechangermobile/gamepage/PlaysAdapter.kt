package com.example.gamechangermobile.gamepage

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Play
import com.example.gamechangermobile.models.getTeamById
import com.example.gamechangermobile.playerpage.PlayerActivity


class PlaysAdapter(val plays: List<Play>) : RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {
    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val teamIcon: ImageView = itemview.findViewById(R.id.team_icon)
        val eventTimeTextview: TextView = itemview.findViewById(R.id.event_time_textview)
        val scoreTextView: TextView = itemview.findViewById(R.id.score_textview)
        val eventTextView: TextView = itemview.findViewById(R.id.event_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.plays_item, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            Toast.makeText(view.context, "dummy", Toast.LENGTH_SHORT).show()
            // TODO link this to the clip of this event
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val play = plays[position]
        getTeamById(play.teamId)?.profilePic?.let {
            holder.teamIcon.setImageResource(it)
        }
        holder.eventTimeTextview.text = play.timeStamp
        holder.scoreTextView.text = play.score
        holder.eventTextView.text = play.eventDescription
    }

    override fun getItemCount(): Int = plays.size

}