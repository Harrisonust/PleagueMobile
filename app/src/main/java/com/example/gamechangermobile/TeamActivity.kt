package com.example.gamechangermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.Team
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.activity_team.*
import kotlinx.android.synthetic.main.activity_team.view.*

class TeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val teamData = intent.getParcelableExtra<Team>("SELECTED_TEAM")

        if (teamData != null){
            team_page_profile_pic.setImageResource(teamData!!.ProfilePic)
        }
        team_page_team_location.text = teamData?.Location
        team_page_team_name.text = teamData?.Name
        team_page_record.text = teamData?.record
        team_page_team_ranking.text = teamData?.ranking

        team_page_team_favorite_btn.setOnClickListener {view ->
            Snackbar.make(view, "Add to Favorite", Snackbar.LENGTH_SHORT)
                .setAction("Undo") { Log.i("SNACKBAR", "OK") }
                .show()
        }

        team_page_tab.addTab(team_page_tab.newTab().setText("INFO"))
        team_page_tab.addTab(team_page_tab.newTab().setText("SCHEDULE"))
        team_page_tab.addTab(team_page_tab.newTab().setText("ROSTER"))

        //TODO: player stats view
    }
}