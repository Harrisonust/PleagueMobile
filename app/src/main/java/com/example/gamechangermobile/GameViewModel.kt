package com.example.gamechangermobile

import android.os.AsyncTask
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamechangermobile.models.*
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.*

class GameViewModel : ViewModel() {

    var games = MutableLiveData<ArrayList<Game>>()

    init {
        Log.d("VIEWMODEL", "")
        FetchGamesTask().execute()
        FetchTeamRankTask().execute()
    }

    inner class FetchGamesTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            val allGamesURL: java.util.ArrayList<String> = arrayListOf(
                "https://pleagueofficial.com/schedule-pre-season/2021-22",
                "https://pleagueofficial.com/schedule-regular-season/2021-22",
                "https://pleagueofficial.com/schedule-playoffs/2021-22",
                "https://pleagueofficial.com/schedule-finals/2021-22"
            )

            val gameType =
                arrayListOf("Pre Season", "Regular Season", "Playoffs 1st Round", "Playoffs Finals")
            val tempGameList = ArrayList<Game>()
            allGamesURL.forEachIndexed { index, url ->
                val doc =
                    Jsoup.connect(url).get()
                doc.select("div.col-lg-12.col-12")
                    .parallelStream()
                    .filter { it != null }
                    .forEach {
                        val regex =
                            "([0-9][0-9])/([0-9][0-9]) \\(.*?\\) ([0-9][0-9]:[0-9][0-9]) 客隊 (?:\\S+) (.*?) ([0-9]*?) [0-9]*? (\\S+[0-9]*) (.*?) 追蹤賽事 (.*? / .*?) ([0-9]*?) [0-9]*? 主隊 (?:\\S+) (.*?) 數據 售票 (?:.*)".toRegex()

                        val parsed = regex.find(it.text())
                        val month = parsed?.groups?.get(1)?.value
                        val date = parsed?.groups?.get(2)?.value
                        val year = if (month!!.toInt() > 8) "2021" else "2022"
                        val time = parsed?.groups?.get(3)?.value
                        val guest = parsed?.groups?.get(4)?.value
                        val guestScore = parsed?.groups?.get(5)?.value
                        val id = parsed?.groups?.get(6)?.value
                        val location = parsed?.groups?.get(7)?.value
                        val audience = parsed?.groups?.get(8)?.value
                        val hostScore = parsed?.groups?.get(9)?.value
                        val host = parsed?.groups?.get(10)?.value
                        var game = Game(
                            gameId = GameID(id!!),
                            gameType = gameType[index],
                            date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("$year-$month-${date}T${time}:00Z"),
                            guestTeam = getTeamIdByName(guest!!),
                            hostTeam = getTeamIdByName(host!!),
                            guestScore = guestScore!!.toInt(),
                            hostScore = hostScore!!.toInt(),
                        )

                        val today = Date()
                        if (today.compareTo(game.date) > 0)
                            game.status = GameStatus.END
                        else
                            game.status = GameStatus.NOT_YET_START
                        tempGameList.add(game)
//                        getTeamById(game.hostTeam)?.gamesIdList?.add(game.gameId)
//                        getTeamById(game.guestTeam)?.gamesIdList?.add(game.gameId)
                    }
            }
////                Only the original thread that created a view hierarchy can touch its views.
//                gamesFrag.updateGameCardView()
            games.postValue(tempGameList)
            true
        } catch (e: Exception) {
            false
        }
    }

    inner class FetchTeamRankTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            val doc =
                Jsoup.connect("https://pleagueofficial.com/standings/2021-22").get()
            doc.select("tr.bg-gray.text-light.text-center")
                .parallelStream()
                .filter { it != null }
                .forEach {
                    val regex =
                        "^([0-9]) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (?:.*)\$".toRegex()
                    val parsed = regex.find(it.text())
                    var ranking = parsed?.groups?.get(1)?.value
                    val teamName = parsed?.groups?.get(2)?.value
                    val total = parsed?.groups?.get(3)?.value
                    val win = parsed?.groups?.get(4)?.value
                    val lose = parsed?.groups?.get(5)?.value
                    val gb = parsed?.groups?.get(7)?.value
                    val streakL = parsed?.groups?.get(8)?.value
                    val streakN = parsed?.groups?.get(9)?.value

                    val teamID = teamName?.let { it1 -> getTeamIdByName(it1) }
                    val team = getTeamById(teamID)
                    ranking += if (ranking == "1") "st" else if (ranking == "2") "nd" else if (ranking == "3") "rd" else "th"
                    team?.ranking = ranking!!

                    team?.totalRecord = Record(win!!.toInt(), lose!!.toInt())
                    team?.streak = streakL!! + streakN!!

                    team?.gamesBack = gb!!
                }
            true
        } catch (e: Exception) {
            false
        }
    }
}