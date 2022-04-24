package com.example.gamechangermobile.playerpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.views.DynamicTable

class PlayerPageCareerFragment(val player: Player) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_page_career, container, false)
        val avgDynamicTable: DynamicTable = view.findViewById(R.id.avg_dynamic_table)
        val accDynamicTable: DynamicTable = view.findViewById(R.id.acc_dynamic_table)

        avgDynamicTable.renderStatsTable(
            "Total",
            player.averageStat.data,
            90,
            200,
            "cell_view_header",
            "player_data",
            "cell_view_column_player",
            "column_name",
            "cell_view_content",
            "player_data"
        )

        //TODO this broke need to be fixed
//        accDynamicTable.renderStatsTable(
//                "Total",
//                player.accumulatedStats.data,
//                90,
//                200,
//                "cell_view_header",
//                "player_data",
//                "cell_view_column_player",
//                "column_name",
//                "cell_view_content",
//                "player_data"
//        )
        return view
    }

}