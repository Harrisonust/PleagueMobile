package com.example.gamechangermobile.teampage

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.playerpage.PlayerActivity
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Player

class RosterAdapter(val playerList: List<Player>) :
        RecyclerView.Adapter<RosterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roster_item_image = itemView.findViewById<ImageView>(R.id.roster_item_image)
        val roster_item_name = itemView.findViewById<TextView>(R.id.roster_item_name)
        val roster_item_position = itemView.findViewById<TextView>(R.id.roster_item_position)
        val roster_item_number = itemView.findViewById<TextView>(R.id.roster_item_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.team_page_roster_item, parent, false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val player = playerList[position]
            val intent = Intent(parent.context, PlayerActivity::class.java).apply {
                putExtra("SELECTED_PLAYER",player)
            }
            parent.context.startActivity(intent)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = playerList[position]
        holder.roster_item_image.setImageResource(player.ProfilePic)
        holder.roster_item_name.text =
                player.FirstName + " " + player.LastName
        holder.roster_item_number.text = "#" + player.number
        holder.roster_item_position.text = player.position
    }

    override fun getItemCount() = playerList.size
}
