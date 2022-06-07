package com.example.gamechangermobile.gamepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamechangermobile.MainActivity.Companion.playersMap
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.PlgGame
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.OkHttp


class GameViewModel(gameID: Int) : ViewModel() {
    private val gameID = gameID
    private val apiSource = "PLG"
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

    private fun boxScoreOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object: OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                var g = result?.let { GCStatsParser().parsePlg<PlgGame>(it) }
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
                    val leaderStatsName = listOf("points", "rebounds", "assists", "steals", "blocks")
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
                    for (plgPlayer in g.data.home + g.data.away) {
                        val player = Player()
                        Log.d(
                            "Debug",
                            "#${plgPlayer.player_id} ${plgPlayer.name_alt} "
                        )
                        plgPlayer.player_id?.let {
//                            player = Player(
//                                playerID = PlayerID(PLGID = it.toInt()),
//                            )
                            player.playerID = PlayerID(PLGID = it.toInt())
                            player.firstName = plgPlayer.name_alt.toString()
                            player.GCID = 378 // TODO soft code
//                            player.number = plgPlayer.jersey.toString()
//                            player.position = plgPlayer.position.toString()
//                            playersMap[player.playerID] = player
                        }
                        var stat = PlayerStats(
                            points = plgPlayer.points?.toFloatOrNull() ?: 0F,
                            rebounds = plgPlayer.reb?.toFloatOrNull() ?: 0F,
                            assists = plgPlayer.ast?.toFloatOrNull() ?: 0F,

                            fieldGoalMade = plgPlayer.two_m?.toFloatOrNull() ?: 0F,
                            fieldGoalAttempt = plgPlayer.two_a?.toFloatOrNull() ?: 0F,

                            twoPointMade = plgPlayer.two_m?.toFloatOrNull() ?: 0F,
                            twoPointAttempt = plgPlayer.two_a?.toFloatOrNull() ?: 0F,

                            threePointMade = plgPlayer.trey_m?.toFloatOrNull() ?: 0F,
                            threePointAttempt = plgPlayer.trey_a?.toFloatOrNull() ?: 0F,

                            freeThrowMade = plgPlayer.ft_m?.toFloatOrNull() ?: 0F,
                            freeThrowAttempt = plgPlayer.ft_a?.toFloatOrNull() ?: 0F,

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
                }
            }
        }

    }

    inner class Game() {
        var guestTeam = 0
        var hostTeam = 0
        var guestScorePerQuarter: MutableList<String> = mutableListOf("0", "0", "0", "0")
        var hostScorePerQuarter: MutableList<String> = mutableListOf("0", "0", "0", "0")
    }
}