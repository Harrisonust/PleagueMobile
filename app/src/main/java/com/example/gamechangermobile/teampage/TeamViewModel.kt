package com.example.gamechangermobile.teampage

import android.os.AsyncTask
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamechangermobile.MainActivity.Companion.teamsMap
import com.example.gamechangermobile.database.*
import com.example.gamechangermobile.database.Dictionary
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.OkHttp
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.*

class TeamViewModel(teamID: Int) : ViewModel() {
    private val teamID = teamID
    val gameSchedule = MutableLiveData<ArrayList<Game>>()
    val players = MutableLiveData<ArrayList<Player>>()
    var totalRecord = MutableLiveData<Record>()
    var homeRecord = MutableLiveData<Record>()
    var roadRecord = MutableLiveData<Record>()
    var rank = MutableLiveData<String>()
    var teamName = MutableLiveData<String>()
    var streak = MutableLiveData<String>()
    var arena = MutableLiveData<String>()
    var foundingDate = MutableLiveData<String>()
    var bio = MutableLiveData<String>()
    var last10 = MutableLiveData<String>()

    val rosterHeaders = listOf(
        "POS",
        "MT","MIN",
        "PTS", "REB", "AST",
        "FG", "FGM", "FGA", "FG%",
        "2P", "2PM", "2PA", "2P%",
        "3P", "3PM", "3PA", "3P%",
        "FT", "FTM", "FTA", "FT%",
        "OREB", "DREB",
        "STL", "BLK", "TOV", "PF"
    )
    private val roster = MutableLiveData<Map<Player, List<String>>>()
    fun getRoster(): LiveData<Map<Player, List<String>>> {
        return roster
    }

    private fun callRosterApi() {
        OkHttp(rosterOnSuccessResponse()).getRequest(
            "player_season_data",
            mapOf(
                "season_id" to "4",
                "part" to "info,box",
                "team_id" to teamsMap[TeamID(teamID)]?.GCID.toString()
            ),
            "GC"
        )
    }

    init {
        FetchInfoTask().execute()
        FetchTeamRankTask().execute()
        FetchTeamRosterTask().execute()
        FetchTeamSchedule().execute()
        callRosterApi()
    }

    inner class FetchInfoTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            var doc = Jsoup.connect("https://pleagueofficial.com/team/${teamID}").get()

            val _name =
                doc.select("h1.h3.mb-0.text-black.fs22.mt-4.text_strong.text_scale")[0].text()

            val _arena = doc.select("h1.text-black.fs16.text-left.py-1.mt-4").last().text()

            val _foundingDate = doc.select("table.table.mt-4.mb-5.team_intro")[0]
                .children().select("tbody")[0]
                .children()[3].text()

            val _bio = doc.select("table.table.mt-4.mb-5.team_intro")[0]
                .children().select("tbody")[0]
                .children()[0].text().drop(5)

            val regex = "(?:\\S+) ([0-9]*)\\S+? ([0-9]*)\\S+?".toRegex()
            val parsed = regex.find(_foundingDate)
            val year = parsed?.groups?.get(1)?.value
            val month = parsed?.groups?.get(2)?.value

            teamName.postValue(_name)
            arena.postValue(_arena)
            bio.postValue(_bio)
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
            var _players = arrayListOf<Player>()
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
                    _players.add(player)
                }
            players.postValue(_players)

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

            totalRecord.postValue(Record(win!!.toInt(), lose!!.toInt()))
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
            // fetch totalRecord
            val doc = Jsoup.connect("https://pleagueofficial.com/team/${teamID}").get()
            doc.select("table.table.fs12.col-md-12.bg-light.table-hover")
                .first().children().select("tbody")
                .first().children().select("tr")
                .forEach {
                    var regex =
                        "<a href=\"/game/([0-9]*)".toRegex()
                    val idtext = it.children()[2].children()[0].toString()
                    val id = regex.find(idtext)?.groups?.get(1)?.value!!
                    regex =
                        "([0-9]*)-([0-9]*)-([0-9]*) (\\S+) ([0-9]*)(\\S)? - ([0-9]*)(\\S)?".toRegex()
                    val parsed = regex.find(it.text())
                    val year = parsed?.groups?.get(1)?.value
                    val month = parsed?.groups?.get(2)?.value
                    val date = parsed?.groups?.get(3)?.value
                    val opponent = parsed?.groups?.get(4)?.value
                    val guestScore = parsed?.groups?.get(5)?.value
                    val wl1 = parsed?.groups?.get(6)?.value
                    val hostScore = parsed?.groups?.get(7)?.value
                    val wl2 = parsed?.groups?.get(8)?.value

                    val isHost = wl2 != null
                    val hostTeam: TeamID =
                        if (isHost) TeamID(teamID) else getTeamIdByName(opponent!!)
                    val guestTeam: TeamID =
                        if (isHost) getTeamIdByName(opponent!!) else TeamID(teamID)
//                    val winner = if(hostscore!!.toInt() > guestscore!!.toInt()) hostTeam else guestTeam

                    var gameStatus: GameStatus
                    val today = Date()
                    gameStatus = if (today < Date(
                            year?.toInt()!!,
                            month?.toInt()!!,
                            date?.toInt()!!
                        )
                    ) GameStatus.END else GameStatus.NOT_YET_START

                    val game = Game(
                        GameID(id),
                        gameType = "Regular Game",
                        guestTeam = guestTeam,
                        hostTeam = hostTeam,
                        date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("$year-$month-${date}T00:00:00Z"),
                        guestScore = guestScore!!.toInt(),
                        hostScore = hostScore!!.toInt(),
                        status = gameStatus,
                    )
                    _gameSchedule.add(game)
                    Log.d(
                        "Debug",
                        "$year/$month/$date:\t${getTeamById(TeamID(teamID))?.name}-$hostScore vs $opponent-$guestScore"
                    )
                }
            _gameSchedule.sortBy { it.date }

            var last10Record = Record(0, 0)
            var _homeRecord = Record(0, 0)
            var _roadRecord = Record(0, 0)

            _gameSchedule.forEachIndexed { index, game ->
                if (game.hostTeam.ID == teamID && game.hostScore > game.guestScore) _homeRecord.wins++
                else if (game.hostTeam.ID == teamID && game.hostScore < game.guestScore) _homeRecord.loses++
                else if (game.guestTeam.ID == teamID && game.hostScore > game.guestScore) _roadRecord.loses++
                else if (game.guestTeam.ID == teamID && game.hostScore < game.guestScore) _roadRecord.wins++
                if (index >= _gameSchedule.size - 10) {
                    if (game.hostTeam.ID == teamID && game.hostScore > game.guestScore) last10Record.wins++
                    else if (game.hostTeam.ID == teamID && game.hostScore < game.guestScore) last10Record.loses++
                    else if (game.guestTeam.ID == teamID && game.hostScore > game.guestScore) last10Record.loses++
                    else if (game.guestTeam.ID == teamID && game.hostScore < game.guestScore) last10Record.wins++
                }
            }

            gameSchedule.postValue(_gameSchedule)
            homeRecord.postValue(_homeRecord)
            roadRecord.postValue(_roadRecord)
            last10.postValue("${last10Record.wins} - ${last10Record.loses}")

            true
        } catch (e: Exception) {
            false
        }
    }

    private fun PlayerIdOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object : OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                val playerList = result?.let { StatsParser().parse<GCPlayerID>(it) }
                if (playerList != null) {
                    for (player in playerList) {
                        getPlayerByName(player.info.name)?.GCID = player.info.id
                    }
                }
            }
        }
    }

    private fun rosterOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object: OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                val playerInfoList = result?.let { StatsParser().parse<GCPlayerInfoWithBox>(it) }
                if (playerInfoList != null) {
                    val r = mutableMapOf<Player, List<String>>()
                    for (data in playerInfoList) {
                        val player = getPlayerByGCID(data.info.id)
                        if (player!=null) {
                            val avgStats = mutableListOf<String>()
                            avgStats.add(data.info.record_matches.toString())
                            avgStats.add(Utils.getPlayingTimeInMinutesString(data.box.avg_min))

                            avgStats.add(Utils.formatStat(data.box.avg_pts))
                            avgStats.add(Utils.formatStat(data.box.avg_reb))
                            avgStats.add(Utils.formatStat(data.box.avg_ast))

                            avgStats.add(Utils.formatStat(data.box.avg_fg_pts))
                            avgStats.add(Utils.formatStat(data.box.avg_fg_m))
                            avgStats.add(Utils.formatStat(data.box.avg_fg_a))
                            avgStats.add(Utils.formatStat(data.box.avg_fg_percent))

                            avgStats.add(Utils.formatStat(data.box.avg_two_pts))
                            avgStats.add(Utils.formatStat(data.box.avg_two_pts_m))
                            avgStats.add(Utils.formatStat(data.box.avg_two_pts_a))
                            avgStats.add(Utils.formatStat(data.box.avg_two_pts_percent))

                            avgStats.add(Utils.formatStat(data.box.avg_three_pts))
                            avgStats.add(Utils.formatStat(data.box.avg_three_pts_m))
                            avgStats.add(Utils.formatStat(data.box.avg_three_pts_a))
                            avgStats.add(Utils.formatStat(data.box.avg_three_pts_percent))

                            avgStats.add(Utils.formatStat(data.box.avg_ft_pts))
                            avgStats.add(Utils.formatStat(data.box.avg_ft_m))
                            avgStats.add(Utils.formatStat(data.box.avg_ft_a))
                            avgStats.add(Utils.formatStat(data.box.avg_ft_percent))

                            avgStats.add(Utils.formatStat(data.box.avg_off_reb))
                            avgStats.add(Utils.formatStat(data.box.avg_def_reb))

                            avgStats.add(Utils.formatStat(data.box.avg_stl))
                            avgStats.add(Utils.formatStat(data.box.avg_blk))
                            avgStats.add(Utils.formatStat(data.box.avg_to))
                            avgStats.add(Utils.formatStat(data.box.avg_pf))

                            r[player] = avgStats
                        }
                    }
                    roster.postValue(r)
                }
            }
        }
    }
}