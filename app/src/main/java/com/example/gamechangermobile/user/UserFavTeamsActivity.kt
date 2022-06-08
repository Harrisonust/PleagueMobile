package com.example.gamechangermobile.user

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.currentUser
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.TeamID
import com.example.gamechangermobile.models.getAllTeam
import com.example.gamechangermobile.models.getTeamById
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_user_fav_teams.*

class UserFavTeamsActivity : AppCompatActivity() {
    private val favTeam = currentUser.favTeam
    private val otherTeam = getAllTeam().minus(favTeam)
    private val onItemClickCallback: FavTeamsAdapter.ItemClickListener = onItemCLickCallbackFunc()

    private fun onItemCLickCallbackFunc(): FavTeamsAdapter.ItemClickListener {
        return object : FavTeamsAdapter.ItemClickListener {
            override fun onItemClickListener() {
                fav_teams_recycler_view.adapter =
                    FavTeamsAdapter(favTeam.toList(), true, onItemClickCallback)
                fav_teams_recycler_view.adapter?.notifyDataSetChanged()
                other_team_recycler_view.adapter = FavTeamsAdapter(
                    getAllTeam().minus(favTeam).toList(),
                    false,
                    onItemClickCallback
                )
                other_team_recycler_view.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_fav_teams)

        fav_teams_recycler_view.adapter =
            FavTeamsAdapter(favTeam.toList(), true, onItemClickCallback)
        fav_teams_recycler_view.layoutManager = LinearLayoutManager(this)
        other_team_recycler_view.adapter =
            FavTeamsAdapter(otherTeam.toList(), false, onItemClickCallback)
        other_team_recycler_view.layoutManager = LinearLayoutManager(this)

    }
}

fun addToFavTeam(view: View, teamId: TeamID) {
    currentUser.favTeam.add(teamId)
    Snackbar.make(view, "${getTeamById(teamId)?.name} is added to Favorite", Snackbar.LENGTH_SHORT)
        .setAction("Undo") { removeFromFavTeam(view, teamId) }
        .show()
}

fun removeFromFavTeam(view: View, teamId: TeamID) {
    currentUser.favTeam.remove(teamId)
    Snackbar.make(
        view,
        "${getTeamById(teamId)?.name} is removed from Favorite",
        Snackbar.LENGTH_SHORT
    )
        .setAction("Undo") { addToFavTeam(view, teamId) }
        .show()
}