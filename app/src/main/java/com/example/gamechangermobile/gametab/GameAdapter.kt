package com.example.gamechangermobile.gametab

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.gamepage.GameActivity
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.GameStatus
import java.text.SimpleDateFormat

class GameAdapter(val gameList: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hostScore: TextView = itemView.findViewById(R.id.host_score)
        val hostImg: ImageView = itemView.findViewById(R.id.host_image)
        val hostName: TextView = itemView.findViewById(R.id.host_name)
        val hostRecord: TextView = itemView.findViewById(R.id.host_record)

        val guestScore: TextView = itemView.findViewById(R.id.guest_score)
        val guestImg: ImageView = itemView.findViewById(R.id.guest_image)
        val guestName: TextView = itemView.findViewById(R.id.guest_name)
        val guestRecord: TextView = itemView.findViewById(R.id.guest_record)

        val remainingTime: TextView = itemView.findViewById(R.id.remaining_time)
        val quarter: TextView = itemView.findViewById(R.id.quarter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_card, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(view.context, GameActivity::class.java).apply {
                putExtra("SELECTED_GAME", gameList[viewHolder.adapterPosition])
            }
            view.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gameList[position]
        holder.hostScore.text = game.hostStats.data["points"]!!.toInt().toString()
        holder.hostImg.setImageResource(game.HostTeam.profilePic)
        holder.hostName.text = game.HostTeam.name
        holder.hostRecord.text = game.HostTeam.totalRecord.getRecord()

        holder.guestScore.text = game.guestStats.data["points"]!!.toInt().toString()
        holder.guestImg.setImageResource(game.GuestTeam.profilePic)
        holder.guestName.text = game.GuestTeam.name
        holder.guestRecord.text = game.GuestTeam.totalRecord.getRecord()

        if (game.status == GameStatus.INGAME) {
            holder.remainingTime.text = game.remainingTime
            holder.quarter.text = game.quarter
        } else if (game.status == GameStatus.NOT_YET_START) {
            holder.remainingTime.text = SimpleDateFormat("HH:mm").format(game.startingTime)
            holder.quarter.text = ""
        } else {
            holder.remainingTime.text = "END"
            holder.quarter.text = ""
        }
    }

    override fun getItemCount() = gameList.size
}