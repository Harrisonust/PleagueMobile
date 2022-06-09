package com.example.gamechangermobile.gamepage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.MainActivity.Companion.gamesMap
import com.example.gamechangermobile.R
import com.example.gamechangermobile.TeamActivity
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.GameID
import com.example.gamechangermobile.models.Team
import com.example.gamechangermobile.models.getTeamById
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_game.*
import java.util.concurrent.Executors

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
            val executor = Executors.newSingleThreadExecutor()
            val handler = Handler(Looper.getMainLooper())
            var image: Bitmap? = null
            executor.execute {
                val imageURL = it[0]
                try {
                    val _in = java.net.URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(_in)
                    handler.post {
                        game_page_image_view.setImageBitmap(image)
                    }
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        game_page_header_guest_icon.setImageResource(
            guestTeam.profilePic
        )
        game_page_header_host_icon.setImageResource(
            hostTeam.profilePic
        )

        gameData.guestStats.data.get("points")?.let {
            game_page_header_guest_score.text = it.toInt().toString()
        }

        gameData.hostStats.data.get("points")?.let {
            game_page_header_host_score.text = it.toInt().toString()
        }

        gameData.remainingTime.let {
            game_page_header_time.text = it
        }

        gameData.highlightPhoto.let {
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