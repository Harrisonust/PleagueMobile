package com.example.gamechangermobile.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.getTeamById

class FavPlayersAdapter(private val playerList: List<Player>, val isFavList: Boolean) :
    RecyclerView.Adapter<FavPlayersAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerImageView: ImageView = itemView.findViewById<ImageView>(R.id.player_image)
        val playerNameView: TextView = itemView.findViewById<TextView>(R.id.player_name)
        val playerTeamView: TextView = itemView.findViewById<TextView>(R.id.player_team)
        val playerNumberView: TextView = itemView.findViewById<TextView>(R.id.player_number)
        val playerPosition: TextView = itemView.findViewById<TextView>(R.id.player_position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fav_player_item, parent, false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val pos: Int = viewHolder.adapterPosition

            if (isFavList) {
                removeFromFavPlayer(view, playerList[pos])
            } else {
                addToFavPlayer(view, playerList[pos])
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player: Player = playerList[position]
        holder.playerImageView.setImageResource(player.profilePic)
        holder.playerNameView.text = player.fullName
        holder.playerNumberView.text = "#" + player.number
        holder.playerTeamView.text = getTeamById(player.teamId)?.name
        holder.playerPosition.text = player.position
    }

    override fun getItemCount() = playerList.size
}