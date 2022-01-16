package com.example.gamechangermobile.teampage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.Team
import kotlinx.android.synthetic.main.fragment_team_page_roster.view.*


class TeamPageRosterFragment(val team: Team) : Fragment() {
    private var fakeRosterList = ArrayList<Player>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_team_page_roster, container, false)

        view.team_page_roster_recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RosterAdapter(team.playerList)
        }
        return view
    }
}