package com.example.gamechangermobile.gamepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.gamechangermobile.R
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.PlgGame
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.example.gamechangermobile.views.DynamicTable
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GamePageBoxScoreFragmentGuestTab(val game: Game) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =
            inflater.inflate(R.layout.fragment_game_page_box_score_guest_tab, container, false)
        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)

        val model: GameViewModel by activityViewModels { GameViewModelFactory(175) }
        model.getGuestBoxScore().observe(viewLifecycleOwner, {
            Log.d("VIEWMODEL", "GamePAGEBOXSCORE")

            dynamicTable.renderBoxScoreTable(
                it,
                model.boxScoreHeaders,
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
        })

//        val players: MutableMap<Player, PlayerStats> = mutableMapOf()
//
//        for (playerID in getTeamById(game.guestTeam)!!.playerList) {
//            val player: Player? = getPlayerById(playerID)
//            if (player != null)
//                players[player] = game.getPlayerStats(playerID) ?: PlayerStats()
//        }
//
//        dynamicTable.renderTable(
//            players,
//            90,
//            280,
//            "cell_view_header",
//            "player_data",
//            "cell_view_column",
//            "player_name",
//            "player_image",
//            "cell_view_content",
//            "player_data"
//        )
        return view
    }

}