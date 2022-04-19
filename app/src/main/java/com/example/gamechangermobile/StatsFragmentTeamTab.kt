package com.example.gamechangermobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.models.Team
import com.example.gamechangermobile.views.DynamicTable

class StatsFragmentTeamTab() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats_team_tab, container, false)
        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        val headers = listOf("W", "L", "WIN%", "GB", "HOME", "ROAD", "LAST 10", "STREAK")
        val teams = mutableMapOf<Team, List<String>>()
        for (team in MainActivity.teams) {
            val stats = mutableListOf<String>()
            stats.add(team.totalRecord.wins.toInt().toString())
            stats.add(team.totalRecord.loses.toInt().toString())
//            stats.add((team.totalRecord.wins/(team.totalRecord.wins+team.totalRecord.loses)).toString())
            stats.add("0")
            stats.add("0")
            stats.add(team.homeRecord.getRecord())
            stats.add(team.awayRecord.getRecord())
            stats.add(team.last10.getRecord())
            stats.add(team.streak)
            teams[team] = stats
        }
        dynamicTable.renderStandingsTable(
            headers,
            teams,
            90,
            280,
            "cell_view_header_longer",
            "player_data",
            "cell_view_column",
            "player_name",
            "player_image",
            "cell_view_content_longer",
            "player_data"
        )
        return view
    }


}