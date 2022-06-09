package com.example.gamechangermobile.gamepage

import android.os.AsyncTask
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamechangermobile.MainActivity.Companion.playersMap
import com.example.gamechangermobile.database.PlgGame
import com.example.gamechangermobile.database.StatsParser
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.PlayerStats
import com.example.gamechangermobile.network.OkHttp
import org.jsoup.Jsoup


class GameViewModel(gameID: Int, val plgGameID: String) : ViewModel() {
    private val gameID = gameID
    private val apiSource = "PLG"
    val photoList = MutableLiveData<ArrayList<String>>()
    val boxScoreHeaders = listOf(
        "PTS", "REB", "AST",
        "FGM", "FGA",
        "2PM", "2PA",
        "3PM", "3PA",
        "FTM", "FTA",
        "OREB", "DREB",
        "STL", "BLK", "TOV", "PF", "EFF"
    )
    private val guestBoxScore = MutableLiveData<Map<Player, PlayerStats>>()
    fun getGuestBoxScore(): MutableLiveData<Map<Player, PlayerStats>> {
        return guestBoxScore
    }

    private val hostBoxScore = MutableLiveData<Map<Player, PlayerStats>>()
    fun getHostBoxScore(): MutableLiveData<Map<Player, PlayerStats>> {
        return hostBoxScore
    }

    init {
        callBoxScoreAPI()
        FetchAlbumTask().execute()
    }

    inner class FetchAlbumTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            var doc = Jsoup.connect("https://pleagueofficial.com/album").get()
            var albumID = -1
            val _photoList = arrayListOf<String>()
            doc.select("div.col-lg-4.col-md-4.col-6.mb-md-5.mb-3.px-md-2.px-2").forEach {
                var regex =
                    "(?:[0-9]*-[0-9]*-[0-9]*) # (?:\\S+) (?:[0-9]*-[0-9]*) ([a-zA-Z]*[0-9]*)".toRegex()
                val _plgGameID = regex.find(it.text())?.groups?.get(1)?.value!!
                if (_plgGameID == plgGameID) {
                    regex = "<a href=\"/photo/([0-9]*)".toRegex()
                    albumID =
                        regex.find(it.children()[0].toString())?.groups?.get(1)?.value!!.toInt()
                    Log.d("Debug", "Game ID: $gameID Album ID: $albumID")
                    doc = Jsoup.connect("https://pleagueofficial.com/photo/$albumID").get()
                    doc.select("a.gallery-item.waterfall_block").forEach { photo ->
                        regex = "<a href=\"(\\S+)\"".toRegex()
                        val photoURL =
                            "https:${regex.find(photo.toString())?.groups?.get(1)?.value}"
                        if (photoURL != null) {
                            Log.d("Debug", photoURL)
                            _photoList.add(photoURL)
                        }
                    }
                }
            }
            photoList.postValue(_photoList)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun callBoxScoreAPI() {
        OkHttp(boxScoreOnSuccessResponse()).getRequest(
            path = "boxscore.php",
            queryParams = mapOf(
                "id" to gameID.toString(),
                "away_tab" to "total",
                "home_tab" to "total",
            ),
            source = apiSource
        )
    }

    private val game = MutableLiveData<Game>()
    fun getGame(): LiveData<Game> {
        return game
    }

    private val hostLeaders = MutableLiveData<Map<String, Player>>()
    fun getHostLeaders(): LiveData<Map<String, Player>> {
        return hostLeaders
    }

    private val hostLeadersPoints = MutableLiveData<Map<String, Float>>()
    fun getHostLeadersPoints(): LiveData<Map<String, Float>> {
        return hostLeadersPoints
    }

    private val guestLeaders = MutableLiveData<Map<String, Player>>()
    fun getGuestLeaders(): LiveData<Map<String, Player>> {
        return guestLeaders
    }

    private val guestLeadersPoints = MutableLiveData<Map<String, Float>>()
    fun getGuestLeadersPoints(): LiveData<Map<String, Float>> {
        return guestLeadersPoints
    }

    private val hostTotalStats = MutableLiveData<PlayerStats>()
    fun getHostTotalStats(): LiveData<PlayerStats> {
        return hostTotalStats
    }

    private val guestTotalStats = MutableLiveData<PlayerStats>()
    fun getGuestTotalStats(): LiveData<PlayerStats> {
        return guestTotalStats
    }

    private val totalStatsNameList = listOf(
        "fieldGoalMade", "fieldGoalAttempt",
        "threePointMade", "threePointAttempt",
        "freeThrowMade", "freeThrowAttempt",
        "assists",
        "rebounds",
        "offensiveRebounds",
        "defensiveRebounds",
        "steals",
        "blocks",
        "turnovers",
        "personalFouls"
    )

    private fun boxScoreOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object : OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                var g = result?.let { StatsParser().parsePlg<PlgGame>(it) }
                if (g != null) {
                    val gameData = Game()
                    gameData.guestScorePerQuarter = arrayListOf(
                        g.data.q1_away,
                        g.data.q2_away,
                        g.data.q3_away,
                        g.data.q4_away,
                    )
                    gameData.hostScorePerQuarter = arrayListOf(
                        g.data.q1_home,
                        g.data.q2_home,
                        g.data.q3_home,
                        g.data.q4_home,
                    )
                    game.postValue(gameData)

                    val gbs = mutableMapOf<Player, PlayerStats>()
                    val hbs = mutableMapOf<Player, PlayerStats>()
                    val leaderStatsName =
                        listOf("points", "rebounds", "assists", "steals", "blocks")
                    val hl = mutableMapOf(
                        "points" to Player(),
                        "rebounds" to Player(),
                        "assists" to Player(),
                        "steals" to Player(),
                        "blocks" to Player()
                    )
                    val hlStats = mutableMapOf(
                        "points" to -1F,
                        "rebounds" to -1F,
                        "assists" to -1F,
                        "steals" to -1F,
                        "blocks" to -1F
                    )
                    val gl = mutableMapOf(
                        "points" to Player(),
                        "rebounds" to Player(),
                        "assists" to Player(),
                        "steals" to Player(),
                        "blocks" to Player()
                    )
                    val glStats = mutableMapOf(
                        "points" to -1F,
                        "rebounds" to -1F,
                        "assists" to -1F,
                        "steals" to -1F,
                        "blocks" to -1F
                    )
                    val hts = PlayerStats()
                    val gts = PlayerStats()
                    for (plgPlayer in g.data.home + g.data.away) {
                        var player = Player()
                        plgPlayer.player_id?.let {
                            player = playersMap[it.toInt()]!!
//                            player = Player(
//                                playerID = PlayerID(PLGID = it.toInt()),
//                            )
//                            player.playerID = PlayerID(PLGID = it.toInt())
//                            player.firstName = plgPlayer.name_alt.toString()
//                            player.GCID = 378 // TODO soft code
//                            player.number = plgPlayer.jersey.toString()
//                            player.position = plgPlayer.position.toString()
//                            playersMap[player.playerID] = player
                        }
                        val regex = "([0-9]*)-([0-9]*)".toRegex()

                        var two_m = 0F
                        var two_a = 0F
                        var three_m = 0F
                        var three_a = 0F
                        var ft_m = 0F
                        var ft_a = 0F

                        plgPlayer.two_m_two?.let {
                            val group = regex.find(it)
                            two_m = group?.groups?.get(1)?.value?.toFloat() ?: 0F
                            two_a = group?.groups?.get(2)?.value?.toFloat() ?: 0F
                        }

                        plgPlayer.trey_m_trey?.let {
                            val group = regex.find(it)
                            three_m = group?.groups?.get(1)?.value?.toFloat() ?: 0F
                            three_a = group?.groups?.get(2)?.value?.toFloat() ?: 0F
                        }

                        plgPlayer.ft_m_ft?.let {
                            val group = regex.find(it)
                            ft_m = group?.groups?.get(1)?.value?.toFloat() ?: 0F
                            ft_a = group?.groups?.get(2)?.value?.toFloat() ?: 0F
                        }

                        var f_m = two_m + three_m
                        var f_a = two_a + three_a

                        var stat = PlayerStats(
                            points = plgPlayer.points?.toFloatOrNull() ?: 0F,
                            rebounds = plgPlayer.reb?.toFloatOrNull() ?: 0F,
                            assists = plgPlayer.ast?.toFloatOrNull() ?: 0F,

                            fieldGoalMade = f_m,
                            fieldGoalAttempt = f_a,
                            twoPointMade = two_m,
                            twoPointAttempt = two_a,
                            threePointMade = three_m,
                            threePointAttempt = three_a,
                            freeThrowMade = ft_m,
                            freeThrowAttempt = ft_a,

                            offensiveRebounds = plgPlayer.reb_o?.toFloatOrNull() ?: 0F,
                            defensiveRebounds = plgPlayer.reb_d?.toFloatOrNull() ?: 0F,
                            steals = plgPlayer.stl?.toFloatOrNull() ?: 0F,
                            blocks = plgPlayer.blk?.toFloatOrNull() ?: 0F,
                            turnovers = plgPlayer.turnover?.toFloatOrNull() ?: 0F,
                            personalFouls = plgPlayer.pfoul?.toFloatOrNull() ?: 0F,

                            effFieldGoalPercentage = plgPlayer.eff?.toFloatOrNull() ?: 0F,
                        )
//                        stat.field =  stats
//                        stat.twoPointPercentage = plgPlayer.two_m.toFloatOrNull()?: 0F / plgPlayer.two_a.toFloatOrNull(),
//                        stat.threePointPercentage = plgPlayer.trey_m.toFloatOrNull()?: 0F / plgPlayer.trey_a.toFloatOrNull(),
//                        stat.freeThrowPercentage = plgPlayer.ft_m.toFloatOrNull()?: 0F / plgPlayer.ft_a.toFloatOrNull(),
                        if (plgPlayer in g.data.home) {
                            hbs[player] = stat
                            for (statName in leaderStatsName) {
                                if (stat.data[statName]!! > hlStats[statName]!!) { // update leaders
                                    hl[statName] = player
                                    hlStats[statName] = stat.data[statName]!!
                                }
                            }
                            for (statName in totalStatsNameList) {
                                hts.data[statName] = hts.data[statName]!! + stat.data[statName]!!
                            }
//                            gameData.hostPlayerStats[PlayerID(
//                                PLGID = plgPlayer.player_id?.toInt() ?: -1
//                            )] =
//                                stat
                        } else {
                            gbs[player] = stat
                            for (statName in leaderStatsName) {
                                if (stat.data[statName]!! > glStats[statName]!!) { // update leaders
                                    gl[statName] = player
                                    glStats[statName] = stat.data[statName]!!
                                }
                            }
                            for (statName in totalStatsNameList) {
                                gts.data[statName] = gts.data[statName]!! + stat.data[statName]!!
                            }
//                            gameData.guestPlayerStats[PlayerID(
//                                PLGID = plgPlayer.player_id?.toInt() ?: -1
//                            )] = stat
                        }
                    }
                    guestBoxScore.postValue(gbs)
                    hostBoxScore.postValue(hbs)
                    hostLeaders.postValue(hl)
                    hostLeadersPoints.postValue(hlStats)
                    guestLeaders.postValue(gl)
                    guestLeadersPoints.postValue(glStats)
                    hostTotalStats.postValue(hts)
                    guestTotalStats.postValue(gts)
                }
            }
        }
    }
}