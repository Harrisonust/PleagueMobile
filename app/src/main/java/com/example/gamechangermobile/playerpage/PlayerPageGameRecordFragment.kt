package com.example.gamechangermobile.playerpage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.views.DynamicTable

class PlayerPageGameRecordFragment(val player: Player) : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_page_game_record, container, false)
        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        val gameList = player.stats
//        val headers = listOf("對手","比分","主/客","時間","命中","出手","命中率","進攻","防守","籃板","助攻","失誤","抄截","阻攻","犯規","得分")

        dynamicTable.renderPlayerGameTable(
            gameList,
            90,
            250,
            "cell_view_header",
            "player_data",
            "cell_view_column_game_date",
            "column_name",
            "cell_view_content",
            "player_data"
        )

        return view
    }

}