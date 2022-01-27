package com.example.gamechangermobile.teampage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.game1
import com.example.gamechangermobile.MainActivity.Companion.game2
import com.example.gamechangermobile.MainActivity.Companion.game3
import com.example.gamechangermobile.MainActivity.Companion.game4
import com.example.gamechangermobile.MainActivity.Companion.game5
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.Team
import kotlinx.android.synthetic.main.fragment_team_page_schedule.*


class TeamPageScheduleFragment(team: Team) : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_page_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val games: List<Game> = listOf(
                game1, game2, game3, game4, game5,
                game1, game2, game3, game4, game5,
                game1, game2, game3, game4, game5,
                game1, game2, game3, game4, game5,
        )


        schedule_recycler.adapter = ScheduleAdapter(games)
        schedule_recycler.layoutManager = LinearLayoutManager(context)

    }

}
