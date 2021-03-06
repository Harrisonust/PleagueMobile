package com.example.gamechangermobile.teampage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.views.DynamicTable


class TeamPageRosterFragment(private val teamID: TeamID) : Fragment() {
    private var fakeRosterList = ArrayList<Player>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_team_page_roster, container, false)
        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        val model: TeamViewModel by activityViewModels { TeamViewModelFactory(teamID.ID) }

        model.getRoster().observe(viewLifecycleOwner) {

            dynamicTable.renderRosterTable(
                it,
                model.rosterHeaders,
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

        }
//        val players: MutableMap<Player, PlayerStats> = mutableMapOf()
//        val team = getTeamById(teamID)
//        if (team != null)
//            for (playerID in team.playerList) {
//                val player = getPlayerById(playerID)
//                if (player != null)
//                    players[player] = player.averageStat
//            }

//        view.team_page_roster_recycler.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = RosterAdapter(team.playerList)
//        }

//        val progressBar: CircularProgressIndicator = view.findViewById(R.id.progress_circular)
//        progressBar.visibility = View.VISIBLE

//        Handler(Looper.getMainLooper()).postDelayed({
//            progressBar.visibility = View.VISIBLE
//        }, 3000)
//        progressBar.visibility = View.GONE


        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
//
//        var content: List<List<String>> = listOf()
//        when (team.name) {
//            "Braves" -> content = Database.Braves().roster
//            "Dreamers" -> content = Database.Dreamers().roster
//            "Kings" -> content = Database.Kings().roster
//            "Lioneers" -> content = Database.Lioneers().roster
//            "Pilots" -> content = Database.Pilots().roster
//            "Steelers" -> content = Database.Steelers().roster
//        }
//
//        dynamicTable.renderTable(
//                Database().headers,
//                content,
//                90,
//                280,
//                "cell_view_header",
//                "player_data",
//                "cell_view_column",
//                "player_name",
//                "player_image",
//                "cell_view_content",
//                "player_data"
//        )
//
//        dynamicTable.visibility = View.VISIBLE
////        progress_circular.visibility = View.GONE
//    }
}