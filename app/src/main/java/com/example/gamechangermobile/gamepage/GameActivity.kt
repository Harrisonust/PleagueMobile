package com.example.gamechangermobile.gamepage

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.gamesMap
import com.example.gamechangermobile.R
import com.example.gamechangermobile.TeamActivity
import com.example.gamechangermobile.models.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_game.*
import java.text.SimpleDateFormat

class GameActivity : AppCompatActivity() {
    private lateinit var gameData: Game
    private lateinit var guestTeam: Team
    private lateinit var hostTeam: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val gameID = intent.getParcelableExtra<GameID>("SELECTED_GAME")!!
        val plgGameID = intent.getStringExtra("SELECTED_GAME_PLGID")!!
        gameData = gamesMap[gameID]!!
        guestTeam = getTeamById(gameData.guestTeam)!!
        hostTeam = getTeamById(gameData.hostTeam)!!

        val model: GameViewModel by viewModels {
            GameViewModelFactory(
                gameID.ID.toInt(),
                plgGameID
            )
        }

// rendering UI
        model.photoList.observe(this) {
            game_page_image_recyclerview.adapter = GamePhotoAdapter(it)
        }
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        game_page_image_recyclerview.layoutManager = layoutManager

        game_page_header_guest_icon.setImageResource(
            guestTeam.profilePic
        )
        game_page_header_host_icon.setImageResource(
            hostTeam.profilePic
        )

        model.getGame().observe(this) {
            val guestTtlScore: Int =
                it.guestScorePerQuarter[0].toInt() + it.guestScorePerQuarter[1].toInt() + it.guestScorePerQuarter[2].toInt() + it.guestScorePerQuarter[3].toInt()
//            game_page_header_guest_score.text = gameData.guestScore.toString()
            game_page_header_guest_score.text = guestTtlScore.toString()
            val hostTtlScore: Int =
                it.hostScorePerQuarter[0].toInt() + it.hostScorePerQuarter[1].toInt() + it.hostScorePerQuarter[2].toInt() + it.hostScorePerQuarter[3].toInt()
//            game_page_header_host_score.text = gameData.hostScore.toString()
            game_page_header_host_score.text = hostTtlScore.toString()
        }

        gameData.status.let {
            game_status.text = when (it) {
                GameStatus.NOT_YET_START -> SimpleDateFormat("MM/dd HH:mm").format(gameData.date)
                GameStatus.INGAME -> "LIVE"
                GameStatus.END -> "END"
                else -> "NA"
            }
            if (game_status.text == "LIVE")
                game_status.setBackgroundColor(android.graphics.Color.parseColor("#FF0000"))
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
        game_page_viewpager.adapter = VPagerAdapter(supportFragmentManager, 4, gameData, plgGameID)
        game_page_viewpager.currentItem = 0
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

        refresh_button.setOnClickListener {
            model.refresh()
        }

        // TODO: Collapsing Toolbar Problem
        swipeRefreshLayout.setOnRefreshListener {
            model.refresh()
            swipeRefreshLayout.isRefreshing = false
        }

        nestedScrollView.viewTreeObserver.addOnScrollChangedListener {
            swipeRefreshLayout.isEnabled = nestedScrollView.scrollY == 0
        }
    }

    inner class VPagerAdapter(f: FragmentManager, bh: Int, val game: Game, val plgGameID: String) :
        FragmentPagerAdapter(f, bh) {
        override fun getCount(): Int = 4

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> GamePageSummaryFragment(game, plgGameID)
                1 -> GamePageBoxScoreFragment(game)
                2 -> GamePageHighlightsFragment(game)
                else -> GamePagePlaysFragment(game)
            }
        }
    }
}