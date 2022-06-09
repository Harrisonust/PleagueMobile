package com.example.gamechangermobile.user

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.R
import com.example.gamechangermobile.TeamActivity
import com.example.gamechangermobile.models.TeamID
import com.example.gamechangermobile.models.getTeamById


class FavTeamsAdapter(
    private val teamList: List<TeamID>,
    val isFavList: Boolean,
    val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<FavTeamsAdapter.ViewHolder>() {
    private var delegate: ItemClickListener? = null

    init {
        delegate = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClickListener()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teamImageView: ImageView = itemView.findViewById(R.id.team_icon)
        val teamNameTextView: TextView = itemView.findViewById(R.id.team_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fav_team_item, parent, false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val pos: Int = viewHolder.adapterPosition
            if (isFavList) {
                removeFromFavTeam(it, teamList[pos])
            } else {
                addToFavTeam(it, teamList[pos])
            }
            delegate?.onItemClickListener()
        }

        viewHolder.itemView.setOnLongClickListener {
            val pos: Int = viewHolder.adapterPosition
            val team = teamList[pos]
            val intent = Intent(view.context, TeamActivity::class.java)
            intent.putExtra("SELECTED_TEAM", team)
            view.context.startActivity(intent)
            return@setOnLongClickListener true
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = getTeamById(teamList[position])
        team?.profilePic?.let {
            holder.teamImageView.setImageResource(it)
        }
        team?.name?.let {
            holder.teamNameTextView.text = it
        }
    }

    override fun getItemCount() = teamList.size
}
