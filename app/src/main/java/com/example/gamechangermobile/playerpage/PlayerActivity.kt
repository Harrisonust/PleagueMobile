package com.example.gamechangermobile.playerpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.models.Player
import kotlinx.android.synthetic.main.activity_player.*
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Team
import com.example.gamechangermobile.teampage.TeamPageInfoFragment
import com.example.gamechangermobile.teampage.TeamPageRosterFragment
import com.example.gamechangermobile.teampage.TeamPageScheduleFragment

import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_team.*


class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val playerData = intent.getParcelableExtra<Player>("SELECTED_PLAYER")
        Log.d("Debug", playerData?._pts.toString()+"!!!")
        player_page_profile_pic.setImageResource(playerData!!.ProfilePic)
        player_page_player_firstname.text = playerData?.FirstName
        player_page_player_lastname.text = playerData?.LastName
        player_page_player_team.text = playerData?.team.Name
        player_page_player_number.text = playerData?.number.toString()
        player_page_player_position.text = playerData?.position
        player_page_player_pts.text = playerData?._pts.toString()
        player_page_player_reb.text = playerData?._reb.toString()
        player_page_player_ast.text = playerData?._ast.toString()

        player_page_player_favorite_btn.setOnClickListener {view ->
            Snackbar.make(view, "Add to Favorite", Snackbar.LENGTH_SHORT)
                .setAction("Undo") { Log.i("SNACKBAR", "OK") }
                .show()
        }

        player_page_tab.addTab(player_page_tab.newTab().setText("Game Record"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Stats"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Career"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Adv"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Team eff"))

        player_page_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(player_page_tab))
        player_page_viewpager.adapter = VPagerAdapter(supportFragmentManager,3, playerData!!)
        player_page_viewpager.setCurrentItem(0)
        player_page_tab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(player_page_viewpager))

        //TODO: player stats view
    }
}

class VPagerAdapter(f: FragmentManager, bh:Int, val player: Player) : FragmentPagerAdapter(f,bh){
    override fun getCount(): Int = 5

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> PlayerPageGameRecordFragment()
            1 -> PlayerPageStatsFragment()
            2 -> PlayerPageCareerFragment()
            3 -> PlayerPageAdvancedStatsFragment()
            4 -> PlayerPageTeamEffFragment()
            else -> PlayerPageTeamEffFragment()
        }
    }
}