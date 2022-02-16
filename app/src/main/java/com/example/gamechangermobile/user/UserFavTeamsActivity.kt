package com.example.gamechangermobile.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity
import com.example.gamechangermobile.R
import com.example.gamechangermobile.database.Database
import com.example.gamechangermobile.models.*
import kotlinx.android.synthetic.main.activity_user_fav_players.*
import kotlinx.android.synthetic.main.activity_user_fav_teams.*

class UserFavTeamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_fav_teams)
        val favTeam = listOf<TeamID>(getTeamIdByName(TeamName.KINGS))
        val otherTeam = listOf<TeamID>(
            getTeamIdByName(TeamName.BRAVES),
            getTeamIdByName(TeamName.PILOTS),
            getTeamIdByName(TeamName.STEELERS),
            getTeamIdByName(TeamName.DREAMERS),
            getTeamIdByName(TeamName.LIONEERS),
        )

        fav_teams_recycler_view.adapter = FavTeamsAdapter(favTeam)
        fav_teams_recycler_view.layoutManager = LinearLayoutManager(this)

        other_team_recycler_view.adapter = FavTeamsAdapter(otherTeam)
        other_team_recycler_view.layoutManager = LinearLayoutManager(this)
    }
}