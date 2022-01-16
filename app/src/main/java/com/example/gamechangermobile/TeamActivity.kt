package com.example.gamechangermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.Team
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.activity_team.*
import kotlinx.android.synthetic.main.activity_team.view.*

class TeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val teamData = intent.getParcelableExtra<Team>("SELECTED_TEAM")

        if (teamData != null){
            team_page_profile_pic.setImageResource(teamData!!.ProfilePic)
        }
        team_page_team_location.text = teamData?.Location
        team_page_team_name.text = teamData?.Name
        team_page_record.text = teamData?.record
        team_page_team_ranking.text = teamData?.ranking

        team_page_team_favorite_btn.setOnClickListener {view ->
            Snackbar.make(view, "Add to Favorite", Snackbar.LENGTH_SHORT)
                .setAction("Undo") { Log.i("SNACKBAR", "OK") }
                .show()
        }

        team_page_tab.addTab(team_page_tab.newTab().setText("INFO"))
        team_page_tab.addTab(team_page_tab.newTab().setText("SCHEDULE"))
        team_page_tab.addTab(team_page_tab.newTab().setText("ROSTER"))

        team_page_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(team_page_tab))
        team_page_viewpager.adapter = VPagerAdapter(supportFragmentManager,3, teamData!!)
        team_page_viewpager.setCurrentItem(0)
        team_page_tab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(team_page_viewpager))

        //TODO: player stats view
    }
}

class VPagerAdapter(f: FragmentManager, bh:Int, val team: Team) : FragmentPagerAdapter(f,bh){
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> TeamPageInfoFragment()
            1 -> TeamPageScheduleFragment()
            else -> TeamPageRosterFragment(team)
        }
    }
}



