package com.example.gamechangermobile.teampage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.Team
import com.example.gamechangermobile.models.TeamID
import com.example.gamechangermobile.models.getTeamById
import kotlinx.android.synthetic.main.fragment_team_page_schedule.*


class TeamPageScheduleFragment(private val teamID: TeamID) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_page_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val team = getTeamById(teamID)
        if (team != null)
            schedule_recycler.adapter = ScheduleAdapter(team, team.getGameList().sortedBy { it.date })
        else
            schedule_recycler.adapter = ScheduleAdapter(Team(TeamID(-1)), arrayListOf<Game>())
        schedule_recycler.layoutManager = LinearLayoutManager(context)

    }

}
