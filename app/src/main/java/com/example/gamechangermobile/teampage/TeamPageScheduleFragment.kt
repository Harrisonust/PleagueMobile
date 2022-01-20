package com.example.gamechangermobile.teampage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.MainActivity.Companion.Braves
import com.example.gamechangermobile.MainActivity.Companion.Kings
import com.example.gamechangermobile.MainActivity.Companion.Lioneers
import com.example.gamechangermobile.MainActivity.Companion.Steelers
import com.example.gamechangermobile.R
import com.example.gamechangermobile.database.Database
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.GameStats
import com.example.gamechangermobile.models.GameStatus
import com.example.gamechangermobile.models.Team


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
        val game: List<Game> = listOf(
            Game(Braves, Lioneers, GameStats(109F), GameStats(120F), GameStatus.END, day = "Sat", date = "10/20"),
            Game(Steelers, Lioneers, GameStats(99F), GameStats(89F), GameStatus.END, day = "Sun", date = "10/21"),
            Game(Kings, Lioneers, GameStats(0F), GameStats(0F), GameStatus.NOT_YET_START, day = "Sat", date = "10/27")
        )
    }

}
