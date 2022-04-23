package com.example.gamechangermobile

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.GCTeam
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import kotlinx.android.synthetic.main.activity_main.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import org.jsoup.Jsoup
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()
        val gamesFrag = GameFragment()
        val statsFrag = StatsFragment()
        val userFrag = UserFragment()
        replaceFragment(gamesFrag)

        // ui binding and rendering
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.games_page -> {
                    replaceFragment(gamesFrag)
                    true
                }
                R.id.stats_page -> {
                    replaceFragment(statsFrag)
                    true
                }
                R.id.user_page -> {
                    replaceFragment(userFrag)
                    true
                }
                else -> false
            }
        }

        FetchGamesTask().execute()
        FetchTeamRankingTask().execute()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragView, fragment)
            commit()
        }
    }

    companion object {
        var currentUser = User()

        var players: MutableSet<Player> = mutableSetOf<Player>()

        var teams: MutableSet<Team> = mutableSetOf<Team>(
            Team(
                teamId = getTeamIdByName(TeamName.BRAVES),
                name = "Braves",
                location = "Taipei",
                profilePic = R.drawable.braves,
                foundingDate = Date(2001 - 1900, 1 - 1, 1),
                arena = "Hoping",
                color = R.color.braves_color,
            ),
            Team(
                teamId = getTeamIdByName(TeamName.KINGS),
                name = "Kings",
                location = "New Taipei",
                profilePic = R.drawable.kings,
                foundingDate = Date(2021 - 1900, 1 - 1, 1),
                arena = "HsinChung",
                color = R.color.kings_color,
            ),
            Team(
                teamId = getTeamIdByName(TeamName.PILOTS),
                name = "Pilots",
                location = "Taoyuan",
                profilePic = R.drawable.pilots,
                foundingDate = Date(1999 - 1900, 1 - 1, 1),
                arena = "Taoyuan",
                color = R.color.pilots_color,
            ),
            Team(
                teamId = getTeamIdByName(TeamName.LIONEERS),
                name = "Lioneers",
                location = "Hsinchu",
                profilePic = R.drawable.lioneers,
                foundingDate = Date(2020 - 1900, 1 - 1, 1),
                arena = "ChuPei",
                color = R.color.lioneers_color,
            ),
            Team(
                teamId = getTeamIdByName(TeamName.DREAMERS),
                name = "Dreamers",
                location = "Changhua",
                profilePic = R.drawable.dreamers,
                foundingDate = Date(2005 - 1900, 1 - 1, 1),
                arena = "ChungHau",
                color = R.color.dreamers_color,
            ),
            Team(
                teamId = getTeamIdByName(TeamName.STEELERS),
                name = "Steelers",
                location = "Kaoshung",
                profilePic = R.drawable.steelers,
                foundingDate = Date(2021 - 1900, 1 - 1, 1),
                arena = "KH",
                color = R.color.steelers_color,
            )
        )

        val games: MutableSet<Game> = mutableSetOf<Game>()

    }


    inner class FetchGamesTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            val doc =
                Jsoup.connect("https://pleagueofficial.com/schedule-regular-season/2021-22").get()
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
                        gameId = GameID(id!!.toInt() + 72),
                        date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-$month-${date}T${time}:00Z"),
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
                    MainActivity.games.add(game)
                    getTeamById(game.hostTeam)?.gamesIdList?.add(game.gameId)
                    getTeamById(game.guestTeam)?.gamesIdList?.add(game.gameId)

                }
////                Only the original thread that created a view hierarchy can touch its views.
//                gamesFrag.updateGameCardView()

            true
        } catch (e: Exception) {
            false
        }
    }

    inner class FetchTeamRankingTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            val doc =
                Jsoup.connect("https://pleagueofficial.com/standings/2021-22").get()
            doc.select("tr.bg-gray.text-light.text-center")
                .parallelStream()
                .filter { it != null }
                .forEach {
                    println(it.text())
                    val regex =
                        "^([0-9]) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*)\$".toRegex()
                    val parsed = regex.find(it.text())
                    var ranking= parsed?.groups?.get(1)?.value
                    val teamName= parsed?.groups?.get(2)?.value
                    val total= parsed?.groups?.get(9)?.value
                    val win= parsed?.groups?.get(10)?.value
                    val lose= parsed?.groups?.get(11)?.value
                    val gb= parsed?.groups?.get(13)?.value
                    val streakL= parsed?.groups?.get(14)?.value
                    val streakN= parsed?.groups?.get(15)?.value

                    val teamID = teamName?.let { it1 -> getTeamIdByName(it1) }
                    val team = getTeamById(teamID)

                    ranking += if (ranking == "1") "st" else if (ranking == "2") "nd" else if (ranking == "3") "rd" else "th"
                    team?.ranking = ranking!!

                    team?.totalRecord = Record(win!!.toInt(), lose!!.toInt())
                    team?.streak = streakL!!+streakN!!

                    team?.gamesBack = gb!!
                }
            true
        } catch (e: Exception) {
            false
        }
    }
}



