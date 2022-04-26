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


        val playerViewModel: PlayerViewModel by activityViewModels { PlayerViewModelFactory(player.GCID) }
        val headers = playerViewModel.gameRecordsHeaders
        playerViewModel.getGameRecords().observe(viewLifecycleOwner, {
//            for((key, value) in it.toSortedMap(reverseOrder())) {
//                Log.d("VIEWMODEL", key)
//            }
            if (it.isNotEmpty()) {
                dynamicTable.renderPlayerGameTable(
                    headers,
                    it.toSortedMap(reverseOrder()),
                    90,
                    300,
                    "cell_view_header_longer",
                    "player_data",
                    "cell_view_column_game_date",
                    "column_name",
                    "cell_view_content_longer",
                    "player_data"
                )
            }
        })
        return view
    }

}