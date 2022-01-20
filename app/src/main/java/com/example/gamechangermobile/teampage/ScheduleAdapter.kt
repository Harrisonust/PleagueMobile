package com.example.gamechangermobile.teampage

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.R
import com.example.gamechangermobile.gametab.GameAdapter
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.Schedule

class ScheduleAdapter(val gameScheduleList: List<Game>):RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val day: TextView = itemView.findViewById(R.id.day)
        val date: TextView = itemView.findViewById(R.id.date)
        val opponent_image: ImageView = itemView.findViewById(R.id.opponentImage)
        val opponent: TextView = itemView.findViewById(R.id.opponent)
        val win_lose: TextView = itemView.findViewById(R.id.win_lose)
        val score: TextView = itemView.findViewById(R.id.score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gameScheduleList[position]
        holder.day.text = game.day
        holder.date.text = game.date
        holder.opponent.text = game.GuestTeam.Name
        holder.opponent_image.setImageResource(game.GuestTeam.ProfilePic)
        holder.win_lose.text = "W"
        holder.score.text = game.GuestStats.points.toString() + " : " + game.HostStats.points.toString()
    }

    override fun getItemCount(): Int = gameScheduleList.size
}
