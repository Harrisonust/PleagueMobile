package com.example.gamechangermobile.teampage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Team
import com.example.gamechangermobile.models.TeamID
import com.example.gamechangermobile.models.getTeamById
import kotlinx.android.synthetic.main.fragment_team_page_info.*
import java.text.SimpleDateFormat


class TeamPageInfoFragment(private val teamID: TeamID) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_team_page_info, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val team = getTeamById(teamID)
        if (team != null) {
            total_record.text = team.totalRecord.getRecord()
            home_record.text = team.homeRecord.getRecord()
            away_record.text = team.awayRecord.getRecord()
            streak.text = team.streak
            arena.text = team.arena
            last10.text = team.last10.getRecord()
            founding_date.text = SimpleDateFormat("yyyy/M/dd").format(team.foundingDate)
        }
    }
}