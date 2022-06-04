package com.example.gamechangermobile.gamepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamechangermobile.MainActivity
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.PlgGame
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.OkHttp

class GameViewModel(gameId: Int): ViewModel() {
    // network call required parameter
    private val gameId = gameId
    private val apiSource = "PLG"
    private val gameQueryParams = mapOf(
        "id" to gameId.toString(),
        "away_tab" to "total",
        "home_tab" to "total",
    )
    private fun fetchGame() {
        OkHttp(summaryOnSuccessResponse()).getRequest(
            path = "boxscore.php",
            queryParams = gameQueryParams,
            source = apiSource
        )
    }
    private val game = MutableLiveData<Game>()

    fun getGame(): LiveData<Game> {
        return game
    }

    // summary section
    // box score section
    // highlights section
    // plays section

    init {
        Log.d("VIEWMODEL", "Game $gameId viewModel is created.")
        fetchGame()
    }

    private fun summaryOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object: OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                Log.d("ViewMode", "get game on success")
                val gameData: Game = getGameById(GameID(gameId))!!
                val g = result?.let { GCStatsParser().parsePlg<PlgGame>(it) }
                if (g != null) {
                    for (plgPlayer in g.data.home + g.data.away) {
                        Log.d(
                            "Debug",
                            "#${plgPlayer.player_id} ${plgPlayer.name_alt} "
                        )
                        plgPlayer.player_id?.let {
                            val player = Player(
                                playerID = PlayerID(it.toInt()),
                            )
                            player.firstName = plgPlayer.name_alt.toString()
                            player.number = plgPlayer.jersey.toString()
                            player.position = plgPlayer.position.toString()
                            MainActivity.players.add(player)
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
                            gameData.hostPlayerStats[PlayerID(plgPlayer.player_id?.toInt() ?: -1)] =
                                stat
                        } else {
                            gameData.guestPlayerStats[PlayerID(
                                plgPlayer.player_id?.toInt() ?: -1
                            )] = stat

                        }
                    }
                    game.postValue(gameData)
                }
            }
        }
    }
}