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

class PlayerPageAdvancedStatsFragment(val player: Player) : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_page_advanced_stats, container, false)
        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        val playerViewModel: PlayerViewModel by activityViewModels { PlayerViewModelFactory(player.GCID) }
        val headers = playerViewModel.advHeaders
        playerViewModel.getAdv().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                dynamicTable.renderTable(
                    it,
                    headers,
                    90,
                    200,
                    "cell_view_header_longer",
                    "player_data",
                    "cell_view_column_player",
                    "column_name",
                    "cell_view_content_longer",
                    "player_data"
                )
            }
        })

        return view
    }

}