package com.example.gamechangermobile.teampage

import android.os.AsyncTask
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamechangermobile.MainActivity.Companion.players
import com.example.gamechangermobile.database.GCPlayerID
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.OkHttp
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.*

class TeamViewModel(teamID: Int) : ViewModel() {
    private val teamID = teamID
    val gameSchedule = MutableLiveData<ArrayList<Game>>()
    var record = MutableLiveData<Record>()
    var rank = MutableLiveData<String>()
    var teamName = MutableLiveData<String>()
    var streak = MutableLiveData<String>()
    var arena = MutableLiveData<String>()
    var foundingDate = MutableLiveData<String>()
    var bio = MutableLiveData<String>()

    init {
        FetchInfoTask().execute()
        FetchTeamRankTask().execute()
        FetchTeamRosterTask().execute()
        FetchTeamSchedule().execute()
    }

    inner class FetchInfoTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            var doc = Jsoup.connect("https://pleagueofficial.com/team/${teamID}").get()

            val _name =
                doc.select("h1.h3.mb-0.text-black.fs22.mt-4.text_strong.text_scale")[0].text()
            teamName.postValue(_name)

            val _arena = doc.select("h1.text-black.fs16.text-left.py-1.mt-4").last().text()
            arena.postValue(_arena)

            val _foundingDate = doc.select("table.table.mt-4.mb-5.team_intro")[0]
                .children().select("tbody")[0]
                .children()[3].text()

            val _bio = doc.select("table.table.mt-4.mb-5.team_intro")[0]
                .children().select("tbody")[0]
                .children()[0].text().drop(5)
            bio.postValue(_bio)

            val regex = "(?:\\S+) ([0-9]*)\\S+? ([0-9]*)\\S+?".toRegex()
            val parsed = regex.find(_foundingDate)
            val year = parsed?.groups?.get(1)?.value
            val month = parsed?.groups?.get(2)?.value
            foundingDate.postValue("$year/$month")

            true
        } catch (e: Exception) {
            false
        }
    }

    inner class FetchTeamRosterTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            val url = "https://pleagueofficial.com/team/${teamID}"
            val doc = Jsoup.connect(url).get()
            doc.select("div.row.player_list")
                .first()
                .children()
                .select("div.col-md-3.col-6.mb-grid-gutter")
                .forEach {
                    var playerID = -1
                    it.children()
                        .select("a")
                        .forEach {
                            val regex = "^<a .*?/player/([0-9]*?)\"><(.*?)></a>\$".toRegex()
                            playerID = regex.find(it.toString())?.groups?.get(1)?.value?.toInt()!!
                        }
                    val regex =
                        "^#([0-9]*?) (.*?) ([a-zA-Z]*)(.*?)([0-9]*.[0-9]*.[0-9]*?) ｜ (.*?cm) ｜ (.*?kg) (?:.*)\$".toRegex()
                    val parsed = regex.find(it.text())
                    val number = parsed?.groups?.get(1)?.value
                    val name = parsed?.groups?.get(2)?.value
                    val position = parsed?.groups?.get(3)?.value
                    val eng_name = parsed?.groups?.get(4)?.value
                    val birthday = parsed?.groups?.get(5)?.value
                    val height = parsed?.groups?.get(6)?.value
                    val weight = parsed?.groups?.get(7)?.value

                    val player = Player(
                        playerID = PlayerID(PLGID = playerID),
                        firstName = name!!,
                        teamId = TeamID(teamID),
                        number = number!!,
                        position = position!!,
                    )
                    players.add(player)
                }
            OkHttp(PlayerIdOnSuccessResponse()).getRequest(
                "player_season_data",
                mapOf(
                    "season_id" to "4",
                    "part" to "info",
                    "team_id" to "19,20,21,22,23,24"
                ),
                "GC"
            )
            true
        } catch (e: Exception) {
            false
        }
    }


    inner class FetchTeamRankTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            var ranking: String? = ""
            var teamName: String? = ""
            var total: String? = ""
            var win: String? = ""
            var lose: String? = ""
            var gb: String? = ""
            var streakL: String? = ""
            var streakN: String? = ""

            val doc =
                Jsoup.connect("https://pleagueofficial.com/standings/2021-22").get()
            doc.select("tr.bg-gray.text-light.text-center")
                .parallelStream()
                .filter { it != null }
                .forEach {
                    val regex =
                        "^([0-9]) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*)\$".toRegex()
                    val parsed = regex.find(it.text())

                    teamName = parsed?.groups?.get(2)?.value
                    if (teamID == getTeamIdByName(teamName!!).ID) {
                        ranking = parsed?.groups?.get(1)?.value
                        total = parsed?.groups?.get(3)?.value
                        win = parsed?.groups?.get(4)?.value
                        lose = parsed?.groups?.get(5)?.value
                        gb = parsed?.groups?.get(7)?.value
                        streakL = parsed?.groups?.get(8)?.value
                        streakN = parsed?.groups?.get(9)?.value
                        ranking += if (ranking == "1") "st" else if (ranking == "2") "nd" else if (ranking == "3") "rd" else "th"
                    }
                }
            record.postValue(Record(win!!.toInt(), lose!!.toInt()))
            rank.postValue(ranking!!)
            streak.postValue(streakL!! + streakN!!)
            true
        } catch (e: Exception) {
            false
        }
    }

    inner class FetchTeamSchedule : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            var _gameSchedule = arrayListOf<Game>()

            // fetch record
            val doc = Jsoup.connect("https://pleagueofficial.com/team/${teamID}").get()
            doc.select("table.table.fs12.col-md-12.bg-light.table-hover")
                .first().children().select("tbody")
                .first().children().select("tr")
                .forEach {
                    val regex =
                        "([0-9]*)-([0-9]*)-([0-9]*) (\\S+) ([0-9]*)(\\S)? - ([0-9]*)(\\S)?".toRegex()
                    val parsed = regex.find(it.text())
                    val year = parsed?.groups?.get(1)?.value
                    val month = parsed?.groups?.get(2)?.value
                    val date = parsed?.groups?.get(3)?.value
                    val opponent = parsed?.groups?.get(4)?.value
                    val guestscore = parsed?.groups?.get(5)?.value
                    val wl1 = parsed?.groups?.get(6)?.value
                    val hostscore = parsed?.groups?.get(7)?.value
                    val wl2 = parsed?.groups?.get(8)?.value

                    val isHost = wl2 != null
                    val hostTeam: TeamID =
                        if (isHost) TeamID(teamID) else getTeamIdByName(opponent!!)
                    val guestTeam: TeamID =
                        if (isHost) getTeamIdByName(opponent!!) else TeamID(teamID)

                    var gameStatus: GameStatus
                    val today = Date()
                    gameStatus = if (today.compareTo(
                            Date(
                                year?.toInt()!!,
                                month?.toInt()!!,
                                date?.toInt()!!
                            )
                        ) < 0
                    ) GameStatus.END else GameStatus.NOT_YET_START

                    val game = Game(
                        GameID("0"),
                        gameType = "Regular Game",
                        guestTeam = guestTeam,
                        hostTeam = hostTeam,
                        date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("$year-$month-${date}T00:00:00Z"),
                        guestScore = guestscore!!.toInt(),
                        hostScore = hostscore!!.toInt(),
                        status = gameStatus
                    )
                    _gameSchedule.add(game)
                    Log.d(
                        "Debug",
                        "$year/$month/$date:\t${getTeamById(TeamID(teamID))?.name}-$hostscore vs $opponent-$guestscore"
                    )
                }
            gameSchedule.postValue(_gameSchedule)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun PlayerIdOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object : OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                val playerList = result?.let { GCStatsParser().parse<GCPlayerID>(it) }
                if (playerList != null) {
                    for (player in playerList) {
                        getPlayerByName(player.info.name)?.GCID = player.info.id
                    }
                }
            }
        }
    }
}