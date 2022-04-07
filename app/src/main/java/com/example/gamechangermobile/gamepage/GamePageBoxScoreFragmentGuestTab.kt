package com.example.gamechangermobile.gamepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.R
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.PlgGame
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.example.gamechangermobile.views.DynamicTable
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GamePageBoxScoreFragmentGuestTab(val game: Game) : Fragment() {
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {
                Log.d("Debug", "result: ${result}")
                Log.d("Debug", "Guest Start Parsing")
                var gamedata =
                    result?.let { GCStatsParser().parsePlg<PlgGame>(it) }
                Log.d("Debug", "Guest Finish Parsing")
                if (gamedata != null) {
                    for (player in gamedata.data.home) {
                        Log.d(
                            "Debug",
                            "${player.name_alt} pts:${player.points} id:${player.player_id}"
                        )
                    }
                    for (player in gamedata.data.away) {
                        Log.d(
                            "Debug",
                            "${player.name_alt} pts:${player.points} id:${player.player_id}"
                        )
                    }
//                        game.guestPlayerStats[PlayerID(player.player_id)] = stat

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
        // Inflate the layout for this fragment
        val view =
            inflater.inflate(R.layout.fragment_game_page_box_score_guest_tab, container, false)
        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        val players: MutableMap<Player, PlayerStats> = mutableMapOf()

        for (playerID in getTeamById(game.guestTeam)!!.playerList) {
            val player: Player? = getPlayerById(playerID)
            if (player != null)
                players[player] = game.getPlayerStats(playerID) ?: PlayerStats()
        }

        // Network call section starts
        val myBuilder = CronetEngine.Builder(context)
        val cronetEngine: CronetEngine = myBuilder.build()
        val executor: Executor = Executors.newSingleThreadExecutor()

        // host data
//        https://pleagueofficial.com/api/boxscore.php?id=140&away_tab=total&home_tab=total
        val api = Api.url(
            "boxscore.php", mapOf(
                "id" to "140",
                "away_tab" to "total",
                "home_tab" to "total",
            ),
            testing = true
        )
        val requestBuilder =
            cronetEngine.newUrlRequestBuilder(
                api,
                urlRequestCallback,
                executor
            )
        Log.d("Debug", "result" + api)
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