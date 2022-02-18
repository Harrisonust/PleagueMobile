package com.example.gamechangermobile.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.currentUser
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.*
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

fun notifyFavTeamDataChanged(teamId: TeamID, addToFav: Boolean) {
    if (addToFav) {
        currentUser.favTeam.add(teamId)
    }else{
        currentUser.favTeam.remove(teamId)
    }
}