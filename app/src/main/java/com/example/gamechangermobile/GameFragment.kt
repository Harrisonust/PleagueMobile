package com.example.gamechangermobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.games
import com.example.gamechangermobile.database.StatsParser
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

class GameFragment() : Fragment() {
    private var selectedDate: Date = Date()
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {

                var dataList = result?.let { StatsParser().parse_game_data(it) }

                if (dataList != null) {
                    for (data in dataList) {
                        val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(data.date)

                        games.add(
                            Game(
                                gameId = GameID(data.id),
                                guestTeam = getTeamIdByName(data.away_team_name),
                                hostTeam = getTeamIdByName(data.home_team_name),
                                date = date,
                                guestScore = data.away_team_score,
                                hostScore = data.home_team_score,
                            )
                        )
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

        // Network call section starts
        val myBuilder = CronetEngine.Builder(context)
        val cronetEngine: CronetEngine = myBuilder.build()
        val executor: Executor = Executors.newSingleThreadExecutor()

        val requestBuilder =
            cronetEngine.newUrlRequestBuilder(
                Api.url("game_data", mapOf("season_id" to "4")),
                urlRequestCallback,
                executor
            )

        val request: UrlRequest = requestBuilder.build()
        request.start()
        // Network call section ends
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
