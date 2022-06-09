package com.example.gamechangermobile.gametab

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.red
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
import com.example.gamechangermobile.models.GameStatus
import com.example.gamechangermobile.models.getTeamById
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

        val gameType: TextView = itemView.findViewById(R.id.game_type)
        val game_description: TextView = itemView.findViewById(R.id.game_description)
        val status: TextView = itemView.findViewById(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_card, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(view.context, GameActivity::class.java).apply {
                putExtra("SELECTED_GAME", gameList[viewHolder.adapterPosition].gameId)
                putExtra("SELECTED_GAME_PLGID", gameList[viewHolder.adapterPosition].plgGameID)
            }
            view.context.startActivity(intent)
        }

        viewHolder.guestImg.setOnLongClickListener {
            val intent = Intent(view.context, TeamActivity::class.java)
            val team = gameList[viewHolder.adapterPosition].guestTeam
            intent.putExtra("SELECTED_TEAM", team)
            view.context.startActivity(intent)
            return@setOnLongClickListener true
        }

        viewHolder.hostImg.setOnLongClickListener {
            val intent = Intent(view.context, TeamActivity::class.java)
            val team = gameList[viewHolder.adapterPosition].hostTeam
            intent.putExtra("SELECTED_TEAM", team)
            view.context.startActivity(intent)
            return@setOnLongClickListener true
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gameList[position]
        val hostTeam = getTeamById(game.hostTeam)!!
        val guestTeam = getTeamById(game.guestTeam)!!

        holder.hostScore.text = game.hostStats.data["points"]!!.toInt().toString()
        holder.hostImg.setImageResource(hostTeam.profilePic)
        holder.hostName.text = hostTeam.name
        holder.hostRecord.text = hostTeam.totalRecord.getRecord()

        holder.guestScore.text = game.guestStats.data["points"]!!.toInt().toString()
        holder.guestImg.setImageResource(guestTeam.profilePic)
        holder.guestName.text = guestTeam.name
        holder.guestRecord.text = guestTeam.totalRecord.getRecord()
        holder.game_description.text = game.description
        holder.gameType.text = game.gameType
        if (game.status == GameStatus.INGAME) {
            holder.status.text = " LIVE "
            holder.status.setBackgroundColor(Color.parseColor("#FF0000"))
            holder.status.setTextColor(Color.parseColor("#FFFFFF"))
        } else if (game.status == GameStatus.NOT_YET_START) {
            holder.status.text = SimpleDateFormat("HH:mm").format(game.date)
        } else {
            holder.status.text = "END"
        }
    }

    override fun getItemCount() = gameList.size
}