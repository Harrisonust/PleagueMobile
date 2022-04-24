package com.example.gamechangermobile

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gamechangermobile.database.GCPlayerInfoWithBox
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import kotlinx.android.synthetic.main.activity_main.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {
                var GCPlayerList = result?.let { GCStatsParser().parse<GCPlayerInfoWithBox>(it) }

                if (GCPlayerList != null) {
                    for (gcplayer in GCPlayerList) {
                        var stat = mutableMapOf<String, Float>()
                        stat["points"] = gcplayer.box.avg_pts
                        stat["rebounds"] = gcplayer.box.avg_reb
                        stat["assists"] = gcplayer.box.avg_ast

                        stat["fieldGoalMade"] = gcplayer.box.avg_fg_m
                        stat["fieldGoalAttempt"] = gcplayer.box.avg_fg_a
                        stat["fieldGoalPercentage"] = gcplayer.box.avg_fg_percent

                        stat["twoPointMade"] = gcplayer.box.avg_two_pts_m
                        stat["twoPointAttempt"] = gcplayer.box.avg_two_pts_a
                        stat["twoPointPercentage"] = gcplayer.box.avg_two_pts_percent

                        stat["threePointMade"] = gcplayer.box.avg_three_pts_m
                        stat["threePointAttempt"] = gcplayer.box.avg_three_pts_a
                        stat["threePointPercentage"] = gcplayer.box.avg_three_pts_percent

                        stat["freeThrow"] = gcplayer.box.avg_ft_m
                        stat["freeThrowAttempt"] = gcplayer.box.avg_ft_a
                        stat["freeThrowPercentage"] = gcplayer.box.avg_ft_percent

                        stat["offensiveRebounds"] = gcplayer.box.avg_off_reb
                        stat["defensiveRebounds"] = gcplayer.box.avg_def_reb
                        stat["steals"] = gcplayer.box.avg_stl
                        stat["blocks"] = gcplayer.box.avg_blk
                        stat["turnovers"] = gcplayer.box.avg_to
                        stat["personalFouls"] = gcplayer.box.avg_pf
                        stat["effFieldGoalPercentage"] = gcplayer.box.eff.toFloat()

                        var player = Player(
                            playerID = PlayerID(gcplayer.info.id),
                            firstName = gcplayer.info.name[0].toString(),
                            lastName = gcplayer.info.name.substring(1),
                            profilePic = R.drawable.ic_baseline_sports_basketball_24,
                            averageStat = PlayerStats(data = stat),
                            teamId = TeamID(gcplayer.info.team_id),
                            age = 20,
                            number = gcplayer.info.player_jersey_number.toString(),
                            position = "N",
                            isForeignPlayer = gcplayer.info.is_foreign_player
                        )
                        players.add(player)
                        if (getTeamById(player.teamId) != null)
                            getTeamById(player.teamId)?.playerList?.add(player.playerID)
                        else {
                            Log.d("Debug", "Fail to find team by id. ${player.fullName}")
                        }
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
        setContentView(R.layout.activity_main)
        actionBar?.hide()
        val gamesFrag = GameFragment()
        val statsFrag = StatsFragment()
        val userFrag = UserFragment()
        replaceFragment(gamesFrag)

        // network request
        val myBuilder = CronetEngine.Builder(this)
        val cronetEngine: CronetEngine = myBuilder.build()
        val executor: Executor = Executors.newSingleThreadExecutor()

        val requestBuilder =
            cronetEngine.newUrlRequestBuilder(
                Api.url(
                    "player_season_data", mapOf(
                        "season_id" to "4",
                        "part" to "info,box",
                        "team_id" to "19,20,21,22,23,24"
                    )
                ),
                urlRequestCallback,
                executor
            )
        val request: UrlRequest = requestBuilder.build()
        request.start()

        // ui binding and rendering
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.games_page -> {
                    replaceFragment(gamesFrag)
                    true
                }
                R.id.stats_page -> {
                    replaceFragment(statsFrag)
                    true
                }
                R.id.user_page -> {
                    replaceFragment(userFrag)
                    true
                }
                else -> false
            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragView, fragment)
            commit()
        }
    }

    companion object {
        var currentUser = User()

        var players: MutableSet<Player> = mutableSetOf<Player>()

        var teams: MutableSet<Team> = mutableSetOf<Team>(
            Team(
                teamId = getTeamIdByName(TeamName.BRAVES),
                name = "Braves",
                location = "Taipei",
                profilePic = R.drawable.braves,
                foundingDate = Date(2001 - 1900, 1 - 1, 1),
                arena = "Hoping",
                color = R.color.braves_color,
            ),
            Team(
                teamId = getTeamIdByName(TeamName.KINGS),
                name = "Kings",
                location = "New Taipei",
                profilePic = R.drawable.kings,
                foundingDate = Date(2021 - 1900, 1 - 1, 1),
                arena = "HsinChung",
                color = R.color.kings_color,
            ),
            Team(
                teamId = getTeamIdByName(TeamName.PILOTS),
                name = "Pilots",
                location = "Taoyuan",
                profilePic = R.drawable.pilots,
                foundingDate = Date(1999 - 1900, 1 - 1, 1),
                arena = "Taoyuan",
                color = R.color.pilots_color,
            ),
            Team(
                teamId = getTeamIdByName(TeamName.LIONEERS),
                name = "Lioneers",
                location = "Hsinchu",
                profilePic = R.drawable.lioneers,
                foundingDate = Date(2020 - 1900, 1 - 1, 1),
                arena = "ChuPei",
                color = R.color.lioneers_color,
            ),
            Team(
                teamId = getTeamIdByName(TeamName.DREAMERS),
                name = "Dreamers",
                location = "Changhua",
                profilePic = R.drawable.dreamers,
                foundingDate = Date(2005 - 1900, 1 - 1, 1),
                arena = "ChungHau",
                color = R.color.dreamers_color,
            ),
            Team(
                teamId = getTeamIdByName(TeamName.STEELERS),
                name = "Steelers",
                location = "Kaoshung",
                profilePic = R.drawable.steelers,
                foundingDate = Date(2021 - 1900, 1 - 1, 1),
                arena = "KH",
                color = R.color.steelers_color,
            )
        )

        val games: MutableSet<Game> = mutableSetOf<Game>()

    }
}
