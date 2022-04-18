package com.example.gamechangermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.GCTeam
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.example.gamechangermobile.teampage.TeamPageInfoFragment
import com.example.gamechangermobile.teampage.TeamPageRosterFragment
import com.example.gamechangermobile.teampage.TeamPageScheduleFragment
import com.example.gamechangermobile.user.addToFavTeam
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_team.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class TeamActivity : AppCompatActivity() {
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)
    private var teamID: TeamID? = null

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {

                var data = result?.let { GCStatsParser().parse<GCTeam>(it) }?.get(0)
                var ranking = "na"

                if (data != null) {
                    val team = getTeamById(teamID)
                    team?.totalRecord?.wins = data.info.win_count
                    team?.totalRecord?.loses = data.info.lose_count
                    team?.streak = data.info.winning_streak.toString()
                    team?.ranking = data.ranking.team.ranking.toString()

                    ranking = data.ranking.team.ranking.toString()
                    ranking += if (ranking == "1") "st"
                    else if (ranking == "2") "nd"
                    else "th"
                }
                runOnUiThread {
                    if (data != null) {
                        team_page_record.text =
                            "${data.info.win_count.toInt()} - ${data.info.lose_count.toInt()}"
                        team_page_team_ranking.text = ranking
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        teamID = intent.getParcelableExtra<TeamID>("SELECTED_TEAM")
        val teamData = getTeamById(teamID)
        if (teamData == null) {

        } else {
            // Network call section starts
            val myBuilder = CronetEngine.Builder(this)
            val cronetEngine: CronetEngine = myBuilder.build()
            val executor: Executor = Executors.newSingleThreadExecutor()

            val requestBuilder =
                cronetEngine.newUrlRequestBuilder(
                    Api.url(
                        "team_season_data", mapOf(
                            "season_id" to "4",
                            "part" to "info,ranking",
                            "team_id" to teamData.teamId.ID.toString()
                        ), source = "GC"
                    ),
                    urlRequestCallback,
                    executor
                )

            val request: UrlRequest = requestBuilder.build()
            request.start()
        }

        // start rendering ui
        if (teamData != null) {
            team_page_profile_pic.setImageResource(teamData.profilePic)
        }

        team_page_team_location.text = teamData?.location
        team_page_team_name.text = teamData?.name
        team_page_record.text = teamData?.totalRecord?.getRecord()
        team_page_team_ranking.text = teamData?.ranking

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



