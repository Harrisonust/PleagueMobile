package com.example.gamechangermobile

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.fetchStatus
import com.example.gamechangermobile.MainActivity.Companion.gamesMap
import com.example.gamechangermobile.gametab.GameAdapter
import com.example.gamechangermobile.models.Game
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*
import java.util.*

class GameFragment : Fragment() {
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
        game_recyclerview?.apply { layoutManager = LinearLayoutManager(activity) }

        fetchStatus.observe(viewLifecycleOwner) {
            Log.d("TEST", "${it[0]} ${it[1]} ${it[2]}")
            if (it[0] && it[1] && it[2]) {
                updateGameCardView()
                progress_circular.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
            }
        }

        calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDate = Date(date.year - 1900, date.month - 1, date.day)
            updateGameCardView()
        }
        calendarView.selectedDate = (CalendarDay.today())
        main_refresh_button.setOnClickListener {
            (activity as MainActivity?)?.refresh()
        }
        swipeRefreshLayout.setOnRefreshListener {
            Log.d("TEST", "Refresh!")
            (activity as MainActivity?)?.refresh()
        }
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
            game_recyclerview.adapter = GameAdapter(selectedGames)
            game_recyclerview.adapter?.notifyDataSetChanged()
        }
    }
}
