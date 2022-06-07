package com.example.gamechangermobile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.MainActivity.Companion.playersMap
import com.example.gamechangermobile.database.GCPlayerID
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.OkHttp
import com.example.gamechangermobile.teampage.TeamPageInfoFragment
import com.example.gamechangermobile.teampage.TeamPageRosterFragment
import com.example.gamechangermobile.teampage.TeamPageScheduleFragment
import com.example.gamechangermobile.models.Team
import com.example.gamechangermobile.models.TeamID
import com.example.gamechangermobile.models.getTeamById
import com.example.gamechangermobile.teampage.*
import com.example.gamechangermobile.user.addToFavTeam
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity() {
    lateinit var teamID: TeamID
    lateinit var teamData: Team
    val model: TeamViewModel by viewModels { TeamViewModelFactory(teamID.ID) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        //TODO dangerous
        teamID = intent.getParcelableExtra<TeamID>("SELECTED_TEAM")!!
        teamData = getTeamById(teamID)!!

        // start rendering ui
        if (teamData != null) {
            team_page_profile_pic.setImageResource(teamData.profilePic)
        }

        model.teamName.observe(this) {
            team_page_team_name.text = it
        }
        model.totalRecord.observe(this) {
            team_page_record.text = it.getRecord()
        }
        model.rank.observe(this) {
            team_page_team_ranking.text = it
        }

        team_page_team_favorite_btn.setOnClickListener { view ->
            teamData?.let { addToFavTeam(view, it.teamId) }
        }

        team_page_tab.addTab(team_page_tab.newTab().setText("INFO"))
        team_page_tab.addTab(team_page_tab.newTab().setText("SCHEDULE"))
        team_page_tab.addTab(team_page_tab.newTab().setText("ROSTER"))

        team_page_viewpager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                team_page_tab
            )
        )
        if (teamData != null)
            team_page_viewpager.adapter = VPagerAdapter(supportFragmentManager, 3, teamData)

        team_page_viewpager.setCurrentItem(0)
        team_page_tab.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                team_page_viewpager
            )
        )
    }

    inner class VPagerAdapter(f: FragmentManager, bh: Int, val team: Team) :
        FragmentPagerAdapter(f, bh) {
        val id = team.teamId
        private val fragments = mutableListOf<Fragment>(
            TeamPageInfoFragment(id),
            TeamPageScheduleFragment(id),
            TeamPageRosterFragment(id)
        )

        override fun getCount(): Int = fragments.size

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }
    }
}



