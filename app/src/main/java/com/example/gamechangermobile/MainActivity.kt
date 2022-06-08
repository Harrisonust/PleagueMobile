package com.example.gamechangermobile

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gamechangermobile.database.GCPlayerID
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.OkHttp
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.*

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
        FetchTeamRankTask().execute()
        FetchPlayerTask().execute()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragView, fragment)
            commit()
        }
    }

    companion object {
        var currentUser = User()

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

        var playersMap: MutableMap<PlayerID, Player> = mutableMapOf<PlayerID, Player>()

        var teamsMap: MutableMap<TeamID, Team> = mutableMapOf<TeamID, Team>()

        var gamesMap: MutableMap<GameID, Game> = mutableMapOf<GameID, Game>()

    }

    init {
        teams.forEach {
            teamsMap[it.teamId] = it
        }
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
                        if (today > game.date)
                            game.status = GameStatus.END
                        else
                            game.status = GameStatus.NOT_YET_START
                        gamesMap[game.gameId] = game
                    }
            }
////                Only the original thread that created a view hierarchy can touch its views.
//                gamesFrag.updateGameCardView()
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

    inner class FetchPlayerTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            val fetchList = listOf<String>(
                "https://pleagueofficial.com/team/1",
                "https://pleagueofficial.com/team/2",
                "https://pleagueofficial.com/team/3",
                "https://pleagueofficial.com/team/4",
                "https://pleagueofficial.com/team/5",
                "https://pleagueofficial.com/team/6"
            )
            fetchList.forEachIndexed { index, url ->
                var playerID = -1
                var player = Player()
                val doc = Jsoup.connect(url).get()
                doc.select("div.row.player_list").first().children()
                    .select("div.col-md-3.col-6.mb-grid-gutter")
                    .forEach {
                        it.children().select("a")
                            .forEach {
                                val regex = "^<a .*?/player/([0-9]*?)\"><(.*?)></a>\$".toRegex()
                                playerID =
                                    regex.find(it.toString())?.groups?.get(1)?.value?.toInt()!!
                            }
                        val regex =
                            "^#([0-9]*?) (.*?) ([a-zA-Z]*)(.*?)([0-9]*.[0-9]*.[0-9]*?) ｜ (.*?cm) ｜ (.*?kg) (?:.*)\$".toRegex()
                        val parsed = regex.find(it.text())
                        val number = parsed?.groups?.get(1)?.value
                        val name = parsed?.groups?.get(2)?.value
                        val position = parsed?.groups?.get(3)?.value
                        val engName = parsed?.groups?.get(4)?.value
                        val birthday = parsed?.groups?.get(5)?.value
                        val height = parsed?.groups?.get(6)?.value
                        val weight = parsed?.groups?.get(7)?.value

                        player = Player(
                            playerID = PlayerID(PLGID = playerID),
                            firstName = name!!,
                            teamId = TeamID(index),
                            number = number!!,
                            position = position!!,
                        )
                        playersMap[PlayerID(Name = name, PLGID = playerID)] = player
                    }
            }

//            val api = "https://api.gamechanger.tw/api/player_season_data/?season_id=4&part=info"

            OkHttp(boxScoreOnSuccessResponse()).getRequest(
                path = "player_season_data",
                queryParams = mapOf(
                    "season_id" to "4",
                    "part" to "info",
                ),
                source = "GC"
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun boxScoreOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object : OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                if (result != null) {
                    val playerList = GCStatsParser().parse<GCPlayerID>(result)
                    playerList.forEach { player ->
                        playersMap.forEach {
                            if (it.key.Name == player.info.name) it.key.GCID = player.info.id
                        }
                    }
                }
            }
        }
    }
}




