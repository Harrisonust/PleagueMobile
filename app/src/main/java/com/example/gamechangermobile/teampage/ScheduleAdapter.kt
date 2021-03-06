package com.example.gamechangermobile.teampage

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.R
import com.example.gamechangermobile.TeamActivity
import com.example.gamechangermobile.gamepage.GameActivity
import com.example.gamechangermobile.models.*
import java.text.SimpleDateFormat

class ScheduleAdapter(val myteam: Team, val gameScheduleList: List<Game>) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day: TextView = itemView.findViewById(R.id.day)
        val date: TextView = itemView.findViewById(R.id.date)
        val opponent_image: ImageView = itemView.findViewById(R.id.opponentImage)
        val opponent_name: TextView = itemView.findViewById(R.id.opponent)
        val win_lose: TextView = itemView.findViewById(R.id.win_lose)
        val score: TextView = itemView.findViewById(R.id.score)
        val vs_or_at: TextView = itemView.findViewById(R.id.vs_or_at)
        val gameType: TextView = itemView.findViewById(R.id.game_type)
        val game_item: LinearLayout = itemView.findViewById(R.id.game_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.schedule_item, parent, false)
        val viewHolder = ViewHolder(view)

        fun startTeamPage() {
            val position = viewHolder.adapterPosition
            val game = gameScheduleList[position]
            val opponent =
                if (getTeamById(game.guestTeam)!!.teamId == myteam.teamId) getTeamById(game.hostTeam)
                else getTeamById(game.guestTeam)
            val intent = Intent(parent.context, TeamActivity::class.java)
            intent.putExtra("SELECTED_TEAM", opponent?.teamId)
            parent.context.startActivity(intent)
        }

        fun startGamePage() {
            val position = viewHolder.adapterPosition
            val game = getGameById(gameScheduleList[position].gameId)
            val intent = Intent(parent.context, GameActivity::class.java)
            if (game != null){
                intent.putExtra("SELECTED_GAME", game.gameId)
                intent.putExtra("SELECTED_GAME_PLGID", game.plgGameID)
            }
            else
                Toast.makeText(parent.context, "Game not found", Toast.LENGTH_SHORT).show()
            parent.context.startActivity(intent)
        }

        viewHolder.game_item.setOnClickListener {
            startGamePage()
        }

        viewHolder.game_item.setOnLongClickListener{
            startTeamPage()
            true
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gameScheduleList[position]
        holder.day.text = SimpleDateFormat("EE").format(game.date)
        holder.date.text = SimpleDateFormat("MM/dd").format(game.date)
        holder.vs_or_at.text = if (game.hostTeam == myteam.teamId) "vs" else "@"
        val opponent =
            if (game.guestTeam == myteam.teamId) getTeamById(game.hostTeam)
            else getTeamById(game.guestTeam)

        opponent?.name?.let {
            holder.opponent_name.text = it
        }
        holder.opponent_image.setImageResource(
            opponent?.profilePic ?: R.drawable.ic_baseline_sports_basketball_24
        )

        holder.gameType.text = game.gameType
        var result = ""
        if (game.status == GameStatus.END) {
            holder.score.text = "${game.guestScore} - ${game.hostScore}"
            if (getTeamById(game.hostTeam)?.teamId == myteam.teamId) {
                result = if (game.hostScore > game.guestScore) "W" else "L"
            } else {
                result = if (game.hostScore > game.guestScore) "L" else "W"
            }
            holder.win_lose.text = result
        } else {
//            holder.win_lose.text = SimpleDateFormat("MM/DD HH:mm").format(game.date)
        }


    }

    override fun getItemCount(): Int = gameScheduleList.size
}
