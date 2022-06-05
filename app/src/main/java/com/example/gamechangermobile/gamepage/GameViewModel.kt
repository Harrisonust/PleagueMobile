package com.example.gamechangermobile.gamepage

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gamechangermobile.MainActivity.Companion.playersMap
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.PlgGame
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.PlayerID
import com.example.gamechangermobile.models.PlayerStats
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GameViewModel : ViewModel() {
    init{
        // Network call section starts
        val myBuilder = CronetEngine.Builder(this)
        val cronetEngine: CronetEngine = myBuilder.build()
        val executor: Executor = Executors.newSingleThreadExecutor()

//        https://pleagueofficial.com/api/boxscore.php?id=140&away_tab=total&home_tab=total
        val api = Api.url(
            "boxscore.php", mapOf(
                "id" to gameData.gameId.ID.toString(),
                "away_tab" to "total",
                "home_tab" to "total",
            ),
            source = "PLG"
        )
//        Log.wtf("Debug", "s: $api")
        val requestBuilder =
            cronetEngine.newUrlRequestBuilder(
                api,
                urlRequestCallback,
                executor
            )
        val request: UrlRequest = requestBuilder.build()
        request.start()
    }

    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {
//                Log.wtf("Debug", "result ${result}")
                var g = result?.let { GCStatsParser().parsePlg<PlgGame>(it) }
                if (g != null) {
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

                    for (plgPlayer in g.data.home + g.data.away) {
                        Log.d(
                            "Debug",
                            "#${plgPlayer.player_id} ${plgPlayer.name_alt} "
                        )
                        plgPlayer.player_id?.let {
                            val player = Player(
                                playerID = PlayerID(PLGID = it.toInt()),
                            )
                            player.firstName = plgPlayer.name_alt.toString()
                            player.number = plgPlayer.jersey.toString()
                            player.position = plgPlayer.position.toString()
                            playersMap[player.playerID] = player
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
                            gameData.hostPlayerStats[PlayerID(
                                PLGID = plgPlayer.player_id?.toInt() ?: -1
                            )] =
                                stat
                        } else {
                            gameData.guestPlayerStats[PlayerID(
                                PLGID = plgPlayer.player_id?.toInt() ?: -1
                            )] = stat
                        }
                    }
                }
            }
        }
    }
}