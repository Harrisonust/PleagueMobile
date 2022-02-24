package com.example.gamechangermobile.playerpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.R
import com.example.gamechangermobile.views.DynamicTable

class PlayerPageTeamEffFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_page_team_eff, container, false)

        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        val headers = listOf("場上","場下","影響","進攻籃板率","盯人進攻效率","區域進攻效率","快攻效率","二波進攻效率","快攻得分","二波得分")
        val mockedData = mapOf(
            "整季" to listOf("80","64.2","15.8","7.5%","0.9","0.8","1.0","1.3","22.7","28.9"),
            "熱身賽" to listOf("80","64.2","15.8","7.5%","0.9","0.8","1.0","1.3","22.7","28.9"),
            "例行賽" to listOf("80","64.2","15.8","7.5%","0.9","0.8","1.0","1.3","22.7","28.9"),
            "第一節" to listOf("80","64.2","15.8","7.5%","0.9","0.8","1.0","1.3","22.7","28.9"),
            "第二節" to listOf("80","64.2","15.8","7.5%","0.9","0.8","1.0","1.3","22.7","28.9"),
            "第三節" to listOf("80","64.2","15.8","7.5%","0.9","0.8","1.0","1.3","22.7","28.9"),
            "第四節" to listOf("80","64.2","15.8","7.5%","0.9","0.8","1.0","1.3","22.7","28.9"),
            "OT" to listOf("80","64.2","15.8","7.5%","0.9","0.8","1.0","1.3","22.7","28.9"),
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