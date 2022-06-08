package com.example.gamechangermobile.playerpage

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.MainActivity.Companion.playersMap
import com.example.gamechangermobile.MainActivity.Companion.teamsMap
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.PlayerID
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_player.*


class PlayerActivity : AppCompatActivity() {
    lateinit var player: Player // TODO Delete
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val playerID = intent.getParcelableExtra<PlayerID>("SELECTED_PLAYER")
        player = playersMap[playerID?.PLGID]!!

        player_page_profile_pic.setImageResource(player.profilePic)
        player_page_player_firstname.text = player.firstName
//        player_page_player_lastname.text = player.lastName
        player_page_player_team.text = teamsMap[player.teamId]?.name
        player_page_player_number.text = "#" + player.number.toString()
        player_page_player_position.text = player.position

        val model: PlayerViewModel by viewModels { PlayerViewModelFactory(player.GCID) }
        model.getPlayerBasicInfo().observe(this) { player ->
            player_page_player_pts.text = player.averageStat.data["points"].toString()
            player_page_player_reb.text = player.averageStat.data["rebounds"].toString()
            player_page_player_ast.text = player.averageStat.data["assists"].toString()

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
        player_page_viewpager.adapter = player.let { VPagerAdapter(supportFragmentManager, 5, it) }
        player_page_viewpager.currentItem = 0
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
                4 -> PlayerPageTeamEffFragment(player)
                else -> PlayerPageTeamEffFragment(player)
            }
        }
    }
}


