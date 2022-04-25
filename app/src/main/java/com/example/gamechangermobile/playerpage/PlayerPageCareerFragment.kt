package com.example.gamechangermobile.playerpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.views.DynamicTable

class PlayerPageCareerFragment(val player: Player) : Fragment() {
    private var headers: List<String> = listOf()
    private var avgDynamicTable: DynamicTable? = null
    private var accDynamicTable: DynamicTable? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_page_career, container, false)
        avgDynamicTable = view.findViewById(R.id.avg_dynamic_table)
        accDynamicTable = view.findViewById(R.id.acc_dynamic_table)

        val playerViewModel: PlayerViewModel by activityViewModels { PlayerViewModelFactory(player.GCID) }
        headers = playerViewModel.careerHeaders
        playerViewModel.getCareerAcc().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                renderAccTable(it)
            }
        })
        playerViewModel.getCareerAvg().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                renderAvgTable(it)
            }
        })

        return view
    }
    private fun renderAccTable(map: Map<String, List<String>>) {
        val layoutParams = accDynamicTable?.layoutParams
        layoutParams?.height = 90 + 120 * map.size
        accDynamicTable?.layoutParams = layoutParams
        accDynamicTable?.renderPlayerGameTable(
            headers,
            map.toSortedMap(reverseOrder()),
            90,
            300,
            "cell_view_header",
            "player_data",
            "cell_view_column_game_date",
            "column_name",
            "cell_view_content",
            "player_data"
        )
    }
    private fun renderAvgTable(map: Map<String, List<String>>) {
        val layoutParams = avgDynamicTable?.layoutParams
        layoutParams?.height = 90 + 120 * map.size
        avgDynamicTable?.layoutParams = layoutParams
        avgDynamicTable?.renderPlayerGameTable(
            headers,
            map.toSortedMap(reverseOrder()),
            90,
            300,
            "cell_view_header",
            "player_data",
            "cell_view_column_game_date",
            "column_name",
            "cell_view_content",
            "player_data"
        )
    }

}