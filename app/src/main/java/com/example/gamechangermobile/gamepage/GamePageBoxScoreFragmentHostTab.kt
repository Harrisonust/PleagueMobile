package com.example.gamechangermobile.gamepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.views.DynamicTable


class GamePageBoxScoreFragmentHostTab(val game: Game) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_game_page_box_score_host_tab, container, false)
        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        val model: GameViewModel by activityViewModels { GameViewModelFactory(game.gameId.ID.toInt()) }
        model.getHostBoxScore().observe(viewLifecycleOwner, {
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
        return view
    }

}