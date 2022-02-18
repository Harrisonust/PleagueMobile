package com.example.gamechangermobile.user

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.currentUser
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_user_fav_teams.*

class UserFavTeamsActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_fav_teams)

        val favTeam = currentUser.favTeam
        val otherTeam = getAllTeam().minus(favTeam)

        fav_teams_recycler_view.adapter = FavTeamsAdapter(favTeam.toList(), true)
        fav_teams_recycler_view.layoutManager = LinearLayoutManager(this)
        other_team_recycler_view.adapter = FavTeamsAdapter(otherTeam.toList(), false)
        other_team_recycler_view.layoutManager = LinearLayoutManager(this)

    }

    //    fun onAddToFavorite(team: TeamID) {
    //        user.favTeam.add(team)
    //        user.otherTeam.remove(team)
    //        fav_teams_recycler_view.adapter!!.notifyDataSetChanged()
    //        other_team_recycler_view.adapter!!.notifyDataSetChanged()
    //    }
    //
    //    fun onDropFromFavorite(team: TeamID) {
    //        user.otherTeam.add(team)
    //        user.favTeam.remove(team)
    //        fav_teams_recycler_view.adapter!!.notifyDataSetChanged()
    //        other_team_recycler_view.adapter!!.notifyDataSetChanged()
    //    }

}

fun addToFavTeam(view: View, teamId: TeamID) {
    currentUser.favTeam.add(teamId)
    Snackbar.make(view, "${getTeamById(teamId)?.name} is added to Favorite", Snackbar.LENGTH_SHORT)
        .setAction("Undo") { removeFromFavTeam(view, teamId) }
        .show()
}

fun removeFromFavTeam(view: View, teamId: TeamID) {
    currentUser.favTeam.remove(teamId)
    Snackbar.make(view, "${getTeamById(teamId)?.name} is removed from Favorite", Snackbar.LENGTH_SHORT)
        .setAction("Undo") { addToFavTeam(view, teamId) }
        .show()
}