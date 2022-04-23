package com.example.gamechangermobile.playerpage

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.models.Player
import kotlinx.android.synthetic.main.activity_player.*
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.PlayerID
import com.example.gamechangermobile.models.getPlayerById
import com.example.gamechangermobile.models.getTeamById
import com.example.gamechangermobile.user.addToFavPlayer

import com.google.android.material.tabs.TabLayout


class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val playerID = intent.getParcelableExtra<PlayerID>("SELECTED_PLAYER")
        val player = playerID?.let { getPlayerById(it) }

//        val model: PlayerViewModel by viewModels{ PlayerViewModelFactory(application,
//            player?.GCID!!
//        ) }

        val model: PlayerViewModel by viewModels()



        player_page_profile_pic.setImageResource(
            player?.profilePic ?: R.drawable.ic_baseline_sports_basketball_24
        )
        player_page_player_firstname.text = player?.firstName
        player_page_player_lastname.text = player?.lastName
        player_page_player_team.text = getTeamById(player?.teamId)?.name
        player_page_player_number.text = "#" + player?.number.toString()
        player_page_player_position.text = player?.position
        player?.averageStat?.data?.get("points")?.let {
            player_page_player_pts.text = it.toString()
        }
        player?.averageStat?.data?.get("rebounds")?.let {
            player_page_player_reb.text = it.toString()
        }
        player?.averageStat?.data?.get("assists")?.let {
            player_page_player_ast.text = it.toString()
        }

        player_page_player_favorite_btn.setOnClickListener { view ->
            player?.let { addToFavPlayer(view, it.playerID) }
        }

        player_page_tab.addTab(player_page_tab.newTab().setText("Game Record"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Stats"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Career"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Adv"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Team eff"))

        player_page_viewpager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                player_page_tab
            )
        )
        player_page_viewpager.adapter = player?.let { VPagerAdapter(supportFragmentManager, 5, it) }
        player_page_viewpager.setCurrentItem(0)
        player_page_tab.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                player_page_viewpager
            )
        )

    }

    inner class VPagerAdapter(f: FragmentManager, bh: Int, val player: Player) :
        FragmentPagerAdapter(f, bh) {
        override fun getCount(): Int = 5

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> PlayerPageGameRecordFragment(player)
                1 -> PlayerPageStatsFragment(player)
                2 -> PlayerPageCareerFragment(player)
                3 -> PlayerPageAdvancedStatsFragment(player)
                4 -> PlayerPageTeamEffFragment()
                else -> PlayerPageTeamEffFragment()
            }
        }
    }
}


