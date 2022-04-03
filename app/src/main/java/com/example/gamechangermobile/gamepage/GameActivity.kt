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
import kotlinx.android.synthetic.main.fragment_game.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GameActivity : AppCompatActivity() {

    private lateinit var gameData: Game
    private lateinit var guestTeam: Team
    private lateinit var hostTeam: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameData = intent.getParcelableExtra<Game>("SELECTED_GAME")!!
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