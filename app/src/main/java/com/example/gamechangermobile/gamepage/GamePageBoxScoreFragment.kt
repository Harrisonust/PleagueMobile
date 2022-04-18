package com.example.gamechangermobile.gamepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.R
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.PlgGame
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.PlayerID
import com.example.gamechangermobile.models.PlayerStats
import com.example.gamechangermobile.models.getTeamById
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_game_page_box_score.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GamePageBoxScoreFragment(val game: Game) : Fragment() {
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {
                Log.wtf("Debug", "result ${result}")
                var gamedata =
                    result?.let { GCStatsParser().parsePlg<PlgGame>(it) }
                if (gamedata != null) {
                    val pList = gamedata.data.home + gamedata.data.away
                    for (plgPlayer in pList) {
                        Log.d(
                            "Debug",
                            "${plgPlayer.name_alt} pts:${plgPlayer.points.toFloatOrNull()} id:${plgPlayer.player_id}"
                        )
                        var stat = PlayerStats(
                            points = plgPlayer.points.toFloatOrNull() ?: 0F,
                            rebounds = plgPlayer.reb.toFloatOrNull()?: 0F,
                            assists = plgPlayer.ast.toFloatOrNull()?: 0F,

                            fieldGoalMade = plgPlayer.two_m.toFloatOrNull()?: 0F,
                            fieldGoalAttempt = plgPlayer.two_a.toFloatOrNull()?: 0F,

                            twoPointMade = plgPlayer.two_m.toFloatOrNull()?: 0F,
                            twoPointAttempt = plgPlayer.two_a.toFloatOrNull()?: 0F,

                            threePointMade = plgPlayer.trey_m.toFloatOrNull()?: 0F,
                            threePointAttempt = plgPlayer.trey_a.toFloatOrNull()?: 0F,

                            freeThrowMade = plgPlayer.ft_m.toFloatOrNull()?: 0F,
                            freeThrowAttempt = plgPlayer.ft_a.toFloatOrNull()?: 0F,

                            offensiveRebounds = plgPlayer.reb_o.toFloatOrNull()?: 0F,
                            defensiveRebounds = plgPlayer.reb_d.toFloatOrNull()?: 0F,
                            steals = plgPlayer.stl.toFloatOrNull()?: 0F,
                            blocks = plgPlayer.blk.toFloatOrNull()?: 0F,
                            turnovers = plgPlayer.turnover.toFloatOrNull()?: 0F,
                            personalFouls = plgPlayer.pfoul.toFloatOrNull()?: 0F,

                            effFieldGoalPercentage = plgPlayer.eff.toFloatOrNull()?: 0F,
                        )
//                        stat.field =  stats
//                        stat.twoPointPercentage = plgPlayer.two_m.toFloatOrNull()?: 0F / plgPlayer.two_a.toFloatOrNull(),
//                        stat.threePointPercentage = plgPlayer.trey_m.toFloatOrNull()?: 0F / plgPlayer.trey_a.toFloatOrNull(),
//                        stat.freeThrowPercentage = plgPlayer.ft_m.toFloatOrNull()?: 0F / plgPlayer.ft_a.toFloatOrNull(),
                        if (plgPlayer in gamedata.data.home)
                            game.guestPlayerStats[PlayerID(plgPlayer.player_id.toInt())] = stat
                        else
                            game.hostPlayerStats[PlayerID(plgPlayer.player_id.toInt())] = stat
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
        val view = inflater.inflate(R.layout.fragment_game_page_box_score, container, false)
        // Inflate the layout for this fragment
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
            source = "PLG"
        )
        val requestBuilder =
            cronetEngine.newUrlRequestBuilder(
                api,
                urlRequestCallback,
                executor
            )
        val request: UrlRequest = requestBuilder.build()
        request.start()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        game_page_score_tab_team_tab.addTab(
            game_page_score_tab_team_tab.newTab().setText(
                getTeamById(game.guestTeam)?.name
            )
        )
        game_page_score_tab_team_tab.addTab(
            game_page_score_tab_team_tab.newTab().setText(
                getTeamById(game.hostTeam)?.name
            )
        )

        game_page_score_tab_view_pager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                game_page_score_tab_team_tab
            )
        )
        game_page_score_tab_view_pager.adapter = PagerAdapter(childFragmentManager, 2, game)
        game_page_score_tab_view_pager.setCurrentItem(0)
        game_page_score_tab_team_tab.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                game_page_score_tab_view_pager
            )
        )
    }

    inner class PagerAdapter(f: FragmentManager, bh: Int, val game: Game) :
        FragmentPagerAdapter(f, bh) {
        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> GamePageBoxScoreFragmentGuestTab(game)
                else -> GamePageBoxScoreFragmentHostTab(game)
            }
        }
    }

}