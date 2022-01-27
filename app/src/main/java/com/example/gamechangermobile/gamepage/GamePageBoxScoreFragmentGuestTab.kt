package com.example.gamechangermobile.gamepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.R
import com.example.gamechangermobile.database.Database
import kotlinx.android.synthetic.main.fragment_game_page_box_score_guest_tab.*

class GamePageBoxScoreFragmentGuestTab : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_page_box_score_guest_tab, container, false)
        dynamic_table.renderTable(
                Database().headers,
                content,
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