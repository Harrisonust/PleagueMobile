package com.example.gamechangermobile.playerpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val headers = listOf("時間","正負值per 36","USG%","進攻效率","失誤率")
        val mockedData = mapOf(
            "整季" to listOf("38:09","11.0","24.4%","1.0","12.8%"),
            "熱身賽" to listOf("38:09","11.0","24.4%","1.0","12.8%"),
            "例行賽" to listOf("38:09","11.0","24.4%","1.0","12.8%"),
            "第一節" to listOf("38:09","11.0","24.4%","1.0","12.8%"),
            "第二節" to listOf("38:09","11.0","24.4%","1.0","12.8%"),
            "第三節" to listOf("38:09","11.0","24.4%","1.0","12.8%"),
            "第四節" to listOf("38:09","11.0","24.4%","1.0","12.8%"),
            "OT" to listOf("38:09","11.0","24.4%","1.0","12.8%"),
        )

        dynamicTable.renderTable(
            mockedData,
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

        return view
    }

}