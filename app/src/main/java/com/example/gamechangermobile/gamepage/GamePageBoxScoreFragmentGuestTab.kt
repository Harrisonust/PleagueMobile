package com.example.gamechangermobile.gamepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.R
import com.example.gamechangermobile.database.Database
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.PlayerStats
import com.example.gamechangermobile.views.DynamicTable
import kotlinx.android.synthetic.main.fragment_game_page_box_score_guest_tab.*

class GamePageBoxScoreFragmentGuestTab(val game: Game) : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_page_box_score_guest_tab, container, false)
        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        val players: MutableMap<Player, PlayerStats> = mutableMapOf()

        for (player in game.GuestTeam.playerList) {
            players[player] = game.getPlayerStats(player) ?: PlayerStats()
        }
        dynamicTable.renderTable(
                players,
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