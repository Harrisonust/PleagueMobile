package com.example.gamechangermobile.gamepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.*
import kotlinx.android.synthetic.main.fragment_game_page_plays.*

class GamePagePlaysFragment(val game: Game) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_page_plays, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val plays = listOf<Play>(
            Play(getTeamIdByName(TeamName.BRAVES), "12:00", "0 - 0", "Start Period"),
            Play(getTeamIdByName(TeamName.BRAVES), "11:57", "0 - 0", "Jump Ball A vs B"),
            Play(
                getTeamIdByName(TeamName.BRAVES),
                "11:49",
                "0 - 2",
                "Singletary Cutting Layout Shot, Made(2 PTS) Assist: Z(1 AST)"
            ),
            Play(
                getTeamIdByName(TeamName.PILOTS),
                "11:27",
                "3 - 2",
                "Robinson #pt Shot: Made(3 PTS)"
            ),
            Play(getTeamIdByName(TeamName.BRAVES), "11:18", "3 - 2", "Jet Driving Layup: Miss"),
            Play(
                getTeamIdByName(TeamName.PILOTS),
                "11:09",
                "5 - 2",
                "Tolbert Cutting Dunk Made(2 PTS)"
            ),
        )
        plays_recyclerview.adapter = PlaysAdapter(plays)
        plays_recyclerview.layoutManager = LinearLayoutManager(context)
    }
}