package com.example.gamechangermobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.gamesMap
import com.example.gamechangermobile.gametab.GameAdapter
import com.example.gamechangermobile.models.*
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment() : Fragment() {
    private var selectedDate: Date = Date()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView.setOnDateChangedListener { widget, date, selected ->
            var selectedGames = ArrayList<Game>()
            for (game in gamesMap.values) {
                if (game.date.date == selectedDate.date &&
                    game.date.year == selectedDate.year &&
                    game.date.month == selectedDate.month
                ) {
                    selectedGames.add(game)
                }
            }
            selectedDate = Date(date.year - 1900, date.month - 1, date.day)
            updateGameCardView()
        }

        calendarView.selectedDate = (CalendarDay.today())
        game_recyclerview?.apply { layoutManager = LinearLayoutManager(activity) }
    }

    fun updateGameCardView() {
        var selectedGames = ArrayList<Game>()
        for (game in gamesMap.values) {
            if (game.date.date == selectedDate.date &&
                game.date.year == selectedDate.year &&
                game.date.month == selectedDate.month
            ) {
                selectedGames.add(game)
            }
        }
        game_recyclerview.adapter = GameAdapter(selectedGames)
        game_recyclerview.adapter?.notifyDataSetChanged()
    }
}
