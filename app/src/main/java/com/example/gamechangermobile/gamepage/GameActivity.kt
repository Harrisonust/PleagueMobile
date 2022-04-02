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
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_game.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GameActivity : AppCompatActivity() {
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)
    private lateinit var game_data: Game
    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {
                var playerStatsList = result?.let { GCStatsParser().parsePlayersInfoWithFullBox(it) }
                if (playerStatsList != null) {
                    for (player in playerStatsList) {
                        game_data.hostPlayerStats[PlayerID(player.info.id)] = PlayerStats(
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
                    }
                }

                runOnUiThread {
//                    updateGameCardView()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        game_data = intent.getParcelableExtra<Game>("SELECTED_GAME")!!
        val guestTeam = getTeamById(game_data?.guestTeam)
        val hostTeam = getTeamById(game_data?.hostTeam)

        Log.d("Debug", "gameid:" + game_data?.gameId?.ID.toString())

        // Network call section starts
        val myBuilder = CronetEngine.Builder(this)
        val cronetEngine: CronetEngine = myBuilder.build()
        val executor: Executor = Executors.newSingleThreadExecutor()

        val req = Api.url(
            "player_game_data", mapOf(
                "part" to "info,box",
                "show_all_quarters" to "true",
                "game_id" to game_data?.gameId?.ID.toString(),
                "team_id" to hostTeam?.teamId?.ID.toString()
            )
        )
        val requestBuilder =
            cronetEngine.newUrlRequestBuilder(
                req,
                urlRequestCallback,
                executor
            )
        val request: UrlRequest = requestBuilder.build()
        request.start()

// rendering UI


        game_page_header_guest_icon.setImageResource(
            guestTeam?.profilePic ?: R.drawable.ic_baseline_bar_chart_24
        )
        game_page_header_host_icon.setImageResource(
            hostTeam?.profilePic ?: R.drawable.ic_baseline_bar_chart_24
        )

        game_data?.guestStats?.data?.get("points")?.let {
            game_page_header_guest_score.text = it.toInt().toString()
        }

        game_data?.hostStats?.data?.get("points")?.let {
            game_page_header_host_score.text = it.toInt().toString()
        }

        game_data?.remainingTime?.let {
            game_page_header_time.text = it
        }

        game_data?.highlightPhoto?.let {
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
        game_page_viewpager.adapter = VPagerAdapter(supportFragmentManager, 4, game_data!!)
        game_page_viewpager.setCurrentItem(0)
        game_page_tab.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                game_page_viewpager
            )
        )

        game_page_header_host_icon.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            val team = hostTeam
            intent.putExtra("SELECTED_TEAM", team)
            startActivity(intent)
        }
        game_page_header_guest_icon.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            val team = guestTeam
            intent.putExtra("SELECTED_TEAM", team)
            startActivity(intent)
        }
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