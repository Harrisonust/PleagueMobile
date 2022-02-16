package com.example.gamechangermobile.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.*
import kotlinx.android.synthetic.main.activity_user_fav_teams.*

class UserFavTeamsActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_fav_teams)
        val user_data = intent.getParcelableExtra<User>("USER")

        if (user_data != null) {
            fav_teams_recycler_view.adapter = FavTeamsAdapter(user_data.favTeam, true)
            fav_teams_recycler_view.layoutManager = LinearLayoutManager(this)

            other_team_recycler_view.adapter = FavTeamsAdapter(user_data.otherTeam, false)
            other_team_recycler_view.layoutManager = LinearLayoutManager(this)
        }
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