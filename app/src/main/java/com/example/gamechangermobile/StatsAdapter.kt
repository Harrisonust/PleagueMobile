package com.example.gamechangermobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.models.Player

class StatsAdapter(val PlayerList : List<Player>) : RecyclerView.Adapter<StatsAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val Player_Profile_Pic:ImageView = view.findViewById(R.id.player_stats_profile_pic)
        val Player_Name:TextView = view.findViewById(R.id.player_stats_name)
        val Player_Points:TextView = view.findViewById(R.id.player_stats_pts)
        val Player_Rebound:TextView = view.findViewById(R.id.player_stats_reb)
        val Player_Assist:TextView = view.findViewById(R.id.player_stats_ast)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_stats_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player_item = PlayerList[position]
        holder.Player_Profile_Pic.setImageResource(player_item.ProfilePic)
        holder.Player_Name.text = player_item.FirstName + " " + player_item.LastName
        holder.Player_Points.text = player_item.pts.toString()
        holder.Player_Rebound.text = player_item.reb.toString()
        holder.Player_Assist.text = player_item.ast.toString()
    }

    override fun getItemCount(): Int = PlayerList.size
}

