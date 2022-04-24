package com.example.gamechangermobile.gamepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.R
import com.example.gamechangermobile.TeamActivity
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.PlgGame
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game_page_summary.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GameActivity : AppCompatActivity() {
    private lateinit var gameData: Game
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {
//                Log.wtf("Debug", "result ${result}")
                var g = result?.let { GCStatsParser().parsePlg<PlgGame>(it) }
                if (g != null) {
                    for (plgPlayer in g.data.home + g.data.away) {
//                        Log.d(
//                            "Debug",
//                            "${plgPlayer.name_alt} pts:${plgPlayer.points.toFloatOrNull()} id:${plgPlayer.player_id}"
//                        )
                        var stat = PlayerStats(
                            points = plgPlayer.points.toFloatOrNull() ?: 0F,
                            rebounds = plgPlayer.reb.toFloatOrNull() ?: 0F,
                            assists = plgPlayer.ast.toFloatOrNull() ?: 0F,

                            fieldGoalMade = plgPlayer.two_m.toFloatOrNull() ?: 0F,
                            fieldGoalAttempt = plgPlayer.two_a.toFloatOrNull() ?: 0F,

                            twoPointMade = plgPlayer.two_m.toFloatOrNull() ?: 0F,
                            twoPointAttempt = plgPlayer.two_a.toFloatOrNull() ?: 0F,

                            threePointMade = plgPlayer.trey_m.toFloatOrNull() ?: 0F,
                            threePointAttempt = plgPlayer.trey_a.toFloatOrNull() ?: 0F,

                            freeThrowMade = plgPlayer.ft_m.toFloatOrNull() ?: 0F,
                            freeThrowAttempt = plgPlayer.ft_a.toFloatOrNull() ?: 0F,

                            offensiveRebounds = plgPlayer.reb_o.toFloatOrNull() ?: 0F,
                            defensiveRebounds = plgPlayer.reb_d.toFloatOrNull() ?: 0F,
                            steals = plgPlayer.stl.toFloatOrNull() ?: 0F,
                            blocks = plgPlayer.blk.toFloatOrNull() ?: 0F,
                            turnovers = plgPlayer.turnover.toFloatOrNull() ?: 0F,
                            personalFouls = plgPlayer.pfoul.toFloatOrNull() ?: 0F,

                            effFieldGoalPercentage = plgPlayer.eff.toFloatOrNull() ?: 0F,
                        )
//                        stat.field =  stats
//                        stat.twoPointPercentage = plgPlayer.two_m.toFloatOrNull()?: 0F / plgPlayer.two_a.toFloatOrNull(),
//                        stat.threePointPercentage = plgPlayer.trey_m.toFloatOrNull()?: 0F / plgPlayer.trey_a.toFloatOrNull(),
//                        stat.freeThrowPercentage = plgPlayer.ft_m.toFloatOrNull()?: 0F / plgPlayer.ft_a.toFloatOrNull(),
                        if (plgPlayer in g.data.home) {
                            gameData.hostPlayerStats[PlayerID(plgPlayer.player_id.toInt())] = stat
                        } else {
                            gameData.guestPlayerStats[PlayerID(plgPlayer.player_id.toInt())] = stat
                        }
//                        getPlayerById(PlayerID(plgPlayer.player_id.toInt()))?.stats?.set(
//                            gameData.gameId,
//                            stat
//                        )
//                        Log.d(
//                            "Debug",
//                            "s: ${plgPlayer.name_alt} ${stat.data["points"]} ${stat.data["rebounds"]} ${stat.data["assists"]}"
//                        )
                    }
                }
                runOnUiThread {
//                    updateGameCardView()
                }
            }
        }
    }

    private lateinit var guestTeam: Team
    private lateinit var hostTeam: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val gameID = intent.getParcelableExtra<GameID>("SELECTED_GAME")!!
        gameData = getGameById(gameID)!!
        guestTeam = getTeamById(gameData?.guestTeam)!!
        hostTeam = getTeamById(gameData?.hostTeam)!!


// rendering UI

        game_page_header_guest_icon.setImageResource(
            guestTeam?.profilePic
        )
        game_page_header_host_icon.setImageResource(
            hostTeam?.profilePic
        )

        gameData?.guestStats?.data?.get("points")?.let {
            game_page_header_guest_score.text = it.toInt().toString()
        }

        gameData?.hostStats?.data?.get("points")?.let {
            game_page_header_host_score.text = it.toInt().toString()
        }

        gameData?.remainingTime?.let {
            game_page_header_time.text = it
        }

        gameData?.highlightPhoto?.let {
            game_page_image_view.setImageResource(it)
        }

        game_page_tab.addTab(game_page_tab.newTab().setText("Summary"))
        game_page_tab.addTab(game_page_tab.newTab().setText("Box Score"))
        game_page_tab.addTab(game_page_tab.newTab().setText("Highlights"))
        game_page_tab.addTab(game_page_tab.newTab().setText("Plays"))

        game_page_viewpager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                game_page_tab
            )
        )
        game_page_viewpager.adapter = VPagerAdapter(supportFragmentManager, 4, gameData!!)
        game_page_viewpager.setCurrentItem(0)
        game_page_tab.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                game_page_viewpager
            )
        )

        game_page_header_host_icon.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            val team = hostTeam
            intent.putExtra("SELECTED_TEAM", team.teamId)
            startActivity(intent)
        }
        game_page_header_guest_icon.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            val team = guestTeam
            intent.putExtra("SELECTED_TEAM", team.teamId)
            startActivity(intent)
        }

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

    inner class VPagerAdapter(f: FragmentManager, bh: Int, val game: Game) :
        FragmentPagerAdapter(f, bh) {
        override fun getCount(): Int = 4

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> GamePageSummaryFragment(game)
                1 -> GamePageBoxScoreFragment(game)
                2 -> GamePageHighlightsFragment(game)
                else -> GamePagePlaysFragment(game)
            }
        }
    }
}