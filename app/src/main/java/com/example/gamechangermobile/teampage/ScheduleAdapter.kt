package com.example.gamechangermobile.teampage

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.R
import com.example.gamechangermobile.TeamActivity
import com.example.gamechangermobile.gamepage.GameActivity
import com.example.gamechangermobile.models.Game
import java.text.SimpleDateFormat

class ScheduleAdapter(val gameScheduleList: List<Game>) :
        RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day: TextView = itemView.findViewById(R.id.day)
        val date: TextView = itemView.findViewById(R.id.date)
        val opponent_image: ImageView = itemView.findViewById(R.id.opponentImage)
        val opponent_name: TextView = itemView.findViewById(R.id.opponent)
        val win_lose: TextView = itemView.findViewById(R.id.win_lose)
        val score: TextView = itemView.findViewById(R.id.score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.schedule_item, parent, false)
        val viewHolder = ViewHolder(view)

        fun startTeamPage(){
            val position = viewHolder.adapterPosition
            val team = gameScheduleList[position].GuestTeam
            val intent = Intent(parent.context, TeamActivity::class.java)
            intent.putExtra("SELECTED_TEAM", team)
            parent.context.startActivity(intent)
        }

        fun startGamePage(){
            val position = viewHolder.adapterPosition
            val game = gameScheduleList[position]
            val intent = Intent(parent.context, GameActivity::class.java)
            intent.putExtra("SELECTED_GAME", game)
            parent.context.startActivity(intent)
        }

        viewHolder.opponent_image.setOnClickListener {
           startTeamPage()
        }

        viewHolder.opponent_name.setOnClickListener {
            startTeamPage()
        }

        viewHolder.win_lose.setOnClickListener {
            startGamePage()
        }

        viewHolder.score.setOnClickListener {
            startGamePage()
        }



        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gameScheduleList[position]
        holder.day.text = SimpleDateFormat("EE").format(game.date)
        holder.date.text = SimpleDateFormat("MM/dd").format(game.date)
        holder.opponent_name.text = game.GuestTeam.name
        holder.opponent_image.setImageResource(game.GuestTeam.profilePic)
        holder.win_lose.text = "W"
        holder.score.text =
                game.GuestStats.points.toInt().toString() +
                        " : " +
                        game.HostStats.points.toInt().toString()
    }

    override fun getItemCount(): Int = gameScheduleList.size
}