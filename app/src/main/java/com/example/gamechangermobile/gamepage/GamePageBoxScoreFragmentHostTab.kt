package com.example.gamechangermobile.gamepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.R
import com.example.gamechangermobile.database.GCPlayerInfoWithFullBox
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.example.gamechangermobile.views.DynamicTable
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class GamePageBoxScoreFragmentHostTab(val game: Game) : Fragment() {
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {
                Log.d("Debug","Host Start Parsing ${result}")
                var playerStatsList =
                    result?.let { GCStatsParser().parseList<GCPlayerInfoWithFullBox>(it) }
                Log.d("Debug","Host Finish Parsing")
                if (playerStatsList != null) {
                    for (player in playerStatsList) {
                        val stat = PlayerStats(
                            points = player.box[0].pts.toFloat(),
                            rebounds = player.box[0].reb.toFloat(),
                            assists = player.box[0].ast.toFloat(),

                            fieldGoalMade = player.box[0].fg_m.toFloat(),
                            fieldGoalAttempt = player.box[0].fg_a.toFloat(),
                            fieldGoalPercentage = player.box[0].fg_percent,

                            twoPointMade = player.box[0].two_pts_m.toFloat(),
                            twoPointAttempt = player.box[0].two_pts_a.toFloat(),
                            twoPointPercentage = player.box[0].two_pts_a.toFloat(),

                            threePointMade = player.box[0].three_pts_m.toFloat(),
                            threePointAttempt = player.box[0].three_pts_a.toFloat(),
                            threePointPercentage = player.box[0].three_pts_percent,

                            freeThrowMade = player.box[0].ft_m.toFloat(),
                            freeThrowAttempt = player.box[0].ft_a.toFloat(),
                            freeThrowPercentage = player.box[0].ft_pts.toFloat(),

                            offensiveRebounds = player.box[0].off_reb.toFloat(),
                            defensiveRebounds = player.box[0].def_reb.toFloat(),
                            steals = player.box[0].stl.toFloat(),
                            blocks = player.box[0].blk.toFloat(),
                            turnovers = player.box[0].to.toFloat(),
                            personalFouls = player.box[0].pf.toFloat(),

                            effFieldGoalPercentage = player.box[0].eff.toFloat(),
                        )
                        Log.d(
                            "Debug",
                            "$$ Name: ${player.info.player_name} points: ${player.box[0].pts}"
                        )
                        game.hostPlayerStats[PlayerID(player.info.id)] = stat
                    }
                }

                activity?.runOnUiThread {
//                    updateGameCardView()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_game_page_box_score_host_tab, container, false)
        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        val players: MutableMap<Player, PlayerStats> = mutableMapOf()

        for (playerID in getTeamById(game.hostTeam)!!.playerList) {
            val player = getPlayerById(playerID)
            if (player != null)
                players[player] = game.getPlayerStats(playerID) ?: PlayerStats()
        }

        // Network call section starts
        val myBuilder = CronetEngine.Builder(context)
        val cronetEngine: CronetEngine = myBuilder.build()
        val executor: Executor = Executors.newSingleThreadExecutor()

        // host data
        val req = Api.url(
            "player_game_data", mapOf(
                "part" to "info,box",
                "show_all_quarters" to "true",
                "game_id" to game.gameId.ID.toString(),
                "team_id" to "${game.hostTeam?.ID}"
            )
        )
        val requestBuilder =
            cronetEngine.newUrlRequestBuilder(
                req,
                urlRequestCallback,
                executor
            )
        Log.d("Debug", "req ${req}")
        val request: UrlRequest = requestBuilder.build()
        request.start()

        dynamicTable.renderTable(
            players,
            90,
            280,
            "cell_view_header",
            "player_data",
            "cell_view_column",
            "player_name",
            "player_image",
            "cell_view_content",
            "player_data"
        )
        return view
    }

}