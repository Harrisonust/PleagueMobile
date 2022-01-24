package com.example.gamechangermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.models.Team
import com.example.gamechangermobile.teampage.TeamPageInfoFragment
import com.example.gamechangermobile.teampage.TeamPageRosterFragment
import com.example.gamechangermobile.teampage.TeamPageScheduleFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val teamData = intent.getParcelableExtra<Team>("SELECTED_TEAM")

        if (teamData != null){
            team_page_profile_pic.setImageResource(teamData!!.profilePic)
        }
        team_page_team_location.text = teamData?.location
        team_page_team_name.text = teamData?.name
        team_page_record.text = teamData?.totalRecord
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

    }
    inner class VPagerAdapter(f: FragmentManager, bh:Int, val team: Team) : FragmentPagerAdapter(f,bh){
        override fun getCount(): Int = 3

        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> TeamPageInfoFragment(team)
                1 -> TeamPageScheduleFragment(team)
                else -> TeamPageRosterFragment(team)
            }
        }
    }
}



