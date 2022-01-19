package com.example.gamechangermobile.teampage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.R
import com.example.gamechangermobile.database.Database
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.Team
import com.example.gamechangermobile.views.DynamicTable
import kotlinx.android.synthetic.main.fragment_team_page_roster.view.*


class TeamPageRosterFragment(val team: Team) : Fragment() {
    private var fakeRosterList = ArrayList<Player>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_team_page_roster, container, false)

//        view.team_page_roster_recycler.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = RosterAdapter(team.playerList)
//        }

        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        var content: List<List<String>> = listOf()
        when (team.Name) {
            "Braves" -> content = Database.Braves().roster
            "Dreamers" -> content = Database.Dreamers().roster
            "Kings" -> content = Database.Kings().roster
            "Lioneers" -> content = Database.Lioneers().roster
            "Pilots" -> content = Database.Pilots().roster
            "Steelers" -> content = Database.Steelers().roster
        }

        dynamicTable.renderTable(
            Database().headers,
            content,
            90,
            280,
            "cell_view_header",
            "player_data",
            "cell_view_column",
            "player_name",
            "player_image",
            "cell_view_content",
            "player_data"
        )

        return view
    }
}