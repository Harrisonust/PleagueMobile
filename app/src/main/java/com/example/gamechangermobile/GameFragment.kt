package com.example.gamechangermobile

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.games
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
import org.jsoup.Jsoup

class GameFragment() : Fragment() {
    private var selectedDate: Date = Date()
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onFinishRequest(result: String?) {
                val doc = Jsoup.connect("https://pleagueofficial.com/schedule-regular-season/2021-22").get()
                doc.select("div.col-lg-12.col-12")
                    .parallelStream()
                    .filter { it != null }
                    .forEach {
                        val regex =
                            "^([0-9][0-9])/([0-9][0-9]) \\(.*?\\) ([0-9][0-9]:[0-9][0-9]) 客隊 (\\S+) (.*?) ([0-9]*?) [0-9]*? G([0-9][0-9]) (.*?) 追蹤賽事 (.*? / .*?) ([0-9]*?) [0-9]*? 主隊 (\\S+) (.*?) 數據 售票 (.*? / .*?)\$".toRegex()
                        val parsed = regex.find(it.text())
                        val month = parsed?.groups?.get(1)?.value
                        val date = parsed?.groups?.get(2)?.value
                        val time = parsed?.groups?.get(3)?.value
                        val guest = parsed?.groups?.get(5)?.value
                        val guestScore = parsed?.groups?.get(6)?.value
                        val id = parsed?.groups?.get(7)?.value
                        val location = parsed?.groups?.get(8)?.value
                        val audience = parsed?.groups?.get(9)?.value
                        val hostScore = parsed?.groups?.get(10)?.value
                        val host = parsed?.groups?.get(12)?.value
                        var game = Game(
                            gameId = GameID(id!!.toInt()),
                            date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-$month-${date}T${time}:00Z"),
                            guestTeam = getTeamIdByName(guest!!),
                            hostTeam = getTeamIdByName(host!!),
                            guestScore = guestScore!!.toInt(),
                            hostScore = hostScore!!.toInt(),
                        )
                        val today = Date()
                        if(today.compareTo(game.date) > 0)
                            game.status = GameStatus.END
                        else
                            game.status = GameStatus.NOT_YET_START
                        games.add(game)
                        getTeamById(game.hostTeam)?.gamesIdList?.add(game.gameId)
                        getTeamById(game.guestTeam)?.gamesIdList?.add(game.gameId)
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

        val api = Api.url(
            "", mapOf(),
            source = "PLG_WEB"
        )
        Log.d("Debug", api.toString())
        val requestBuilder =
            cronetEngine.newUrlRequestBuilder(
                api,
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
