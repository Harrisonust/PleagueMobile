package com.example.gamechangermobile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.games
import com.example.gamechangermobile.database.GCGame
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.gametab.GameAdapter
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.collections.ArrayList
import kotlinx.android.synthetic.main.fragment_game.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.EnumSet.of

class GameFragment() : Fragment() {
    private var selectedDate: Date = Date()
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {

                var GCGameList = result?.let { GCStatsParser().parse<GCGame>(it) }

                if (GCGameList != null) {
                    for (gcGameData in GCGameList) {
                        val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(gcGameData.date)
                        val game = Game(
                            gameId = GameID(gcGameData.id),
                            guestTeam = getTeamIdByName(gcGameData.away_team_name),
                            hostTeam = getTeamIdByName(gcGameData.home_team_name),
                            date = date,
                            guestScore = gcGameData.away_team_score,
                            hostScore = gcGameData.home_team_score,
                            status = GameStatus.END
                        )
                        games.add(game)
                        getTeamById(game.hostTeam)?.gamesIdList?.add(game.gameId)
                        getTeamById(game.guestTeam)?.gamesIdList?.add(game.gameId)
                    }
                }

                activity?.runOnUiThread {
                    updateGameCardView()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        val model: GameListViewModel by viewModels()

        model.getGames().observe(viewLifecycleOwner,  {
            updateGameCardView()
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView.setOnDateChangedListener { widget, date, selected ->
            var selectedGames = ArrayList<Game>()
            for (game in games) {
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

    private fun updateGameCardView() {
        var selectedGames = ArrayList<Game>()
        for (game in games) {
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
