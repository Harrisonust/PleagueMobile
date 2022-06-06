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
    private val game = MutableLiveData<Game>()
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

    private fun boxScoreOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object: OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                var g = result?.let { GCStatsParser().parsePlg<PlgGame>(it) }
                if (g != null) {
//                    lateinit var gameData: Game
//                    gameData.guestScorePerQuarter = arrayListOf(
//                        g.data.q1_away,
//                        g.data.q2_away,
//                        g.data.q3_away,
//                        g.data.q4_away,
//                    )
//                    gameData.hostScorePerQuarter = arrayListOf(
//                        g.data.q1_home,
//                        g.data.q2_home,
//                        g.data.q3_home,
//                        g.data.q4_home,
//                    )

                    val gbs = mutableMapOf<Player, PlayerStats>()
                    val hbs = mutableMapOf<Player, PlayerStats>()
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
//                            gameData.hostPlayerStats[PlayerID(
//                                PLGID = plgPlayer.player_id?.toInt() ?: -1
//                            )] =
//                                stat
                        } else {
                            gbs[player] = stat
//                            gameData.guestPlayerStats[PlayerID(
//                                PLGID = plgPlayer.player_id?.toInt() ?: -1
//                            )] = stat
                        }
                    }
                    guestBoxScore.postValue(gbs)
                    hostBoxScore.postValue(hbs)
                }
            }
        }

    }
}