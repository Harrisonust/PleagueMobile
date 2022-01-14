package com.example.gamechangermobile.gametab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Game

class GameAdapter(val gameList: List<Game>): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val matchScore: TextView = itemView.findViewById(R.id.match_score)
        val remainingTime: TextView = itemView.findViewById(R.id.remaining_time)
        val hostImg: ImageView = itemView.findViewById(R.id.host_image)
        val hostName: TextView = itemView.findViewById(R.id.host_name)
        val hostRecord: TextView = itemView.findViewById(R.id.host_record)
        val guestImg: ImageView = itemView.findViewById(R.id.guest_image)
        val guestName: TextView = itemView.findViewById(R.id.guest_name)
        val guestRecord: TextView = itemView.findViewById(R.id.guest_record)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gameList[position]
        holder.matchScore.text = game.matchScore
        holder.remainingTime.text = game.remainingTime
        holder.hostImg.setImageResource(R.mipmap.ic_launcher)
        holder.hostName.text = game.hostName
        holder.hostRecord.text = game.hostRecord
        holder.guestImg.setImageResource(R.mipmap.ic_launcher)
        holder.guestName.text = game.guestName
        holder.guestRecord.text = game.guestRecord
    }

    override fun getItemCount() = gameList.size
}