package com.example.gamechangermobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gamechangermobile.models.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()
        val gamesFrag = GameFragment()
        val statsFrag = StatsFragment()
        val userFrag = UserFragment()
        replaceFragment(gamesFrag)

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

        var playersID: MutableMap<PlayerID, Player> = mutableMapOf<PlayerID, Player>()

        var teamsID: MutableMap<TeamID, Team> = mutableMapOf<TeamID, Team>()

        var gamesID: MutableMap<GameID, Game> = mutableMapOf<GameID, Game>()

    }
}



