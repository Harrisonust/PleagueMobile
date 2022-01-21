package com.example.gamechangermobile.gamepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.R
import com.example.gamechangermobile.TeamActivity
import com.example.gamechangermobile.models.Game
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val game_data = intent.getParcelableExtra<Game>("GAME_DATA")
        if(game_data != null) {
            game_page_header_guest_icon.setImageResource(game_data!!.GuestTeam.ProfilePic)
            game_page_header_host_icon.setImageResource(game_data!!.HostTeam.ProfilePic)
            game_page_header_guest_score.text = game_data!!.GuestStats.points.toInt().toString()
            game_page_header_host_score.text = game_data!!.HostStats.points.toInt().toString()
            game_page_header_time.text = game_data!!.remaining_time
        }

        game_page_tab.addTab(game_page_tab.newTab().setText("Summary"))
        game_page_tab.addTab(game_page_tab.newTab().setText("Box Score"))
        game_page_tab.addTab(game_page_tab.newTab().setText("Highlights"))
        game_page_tab.addTab(game_page_tab.newTab().setText("Plays"))

        game_page_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(game_page_tab))
        game_page_viewpager.adapter = VPagerAdapter(supportFragmentManager,4, game_data!!)
        game_page_viewpager.setCurrentItem(0)
        game_page_tab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(game_page_viewpager))

        game_page_header_host_icon.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            val team = game_data?.HostTeam
            intent.putExtra("SELECTED_TEAM", team)
            startActivity(intent)
        }
        game_page_header_guest_icon.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            val team = game_data?.GuestTeam
            intent.putExtra("SELECTED_TEAM", team)
            startActivity(intent)
        }
    }

    inner class VPagerAdapter(f: FragmentManager, bh:Int, val game: Game) : FragmentPagerAdapter(f,bh){
        override fun getCount(): Int = 4

        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> GamePageSummaryFragment(game)
                1 -> GamePageBoxScoreFragment(game)
                2 -> GamePageHighlightsFragment(game)
                else -> GamePagePlaysFragment(game)
            }
        }
    }
}