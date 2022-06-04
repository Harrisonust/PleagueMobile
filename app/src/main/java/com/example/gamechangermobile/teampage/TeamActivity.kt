package com.example.gamechangermobile

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.MainActivity.Companion.players
import com.example.gamechangermobile.database.GCPlayerID
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.OkHttp
import com.example.gamechangermobile.teampage.TeamPageInfoFragment
import com.example.gamechangermobile.teampage.TeamPageRosterFragment
import com.example.gamechangermobile.teampage.TeamPageScheduleFragment
import com.example.gamechangermobile.user.addToFavTeam
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_team.*
import org.jsoup.Jsoup

class TeamActivity : AppCompatActivity() {
    lateinit var teamID: TeamID
    lateinit var teamData: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        //TODO dangerous
        teamID = intent.getParcelableExtra<TeamID>("SELECTED_TEAM")!!
        teamData = getTeamById(teamID)!!

        if (teamData != null) {
            FetchTeamRankingTask().execute()
            FetchTeamRosterTask().execute()
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

    inner class FetchTeamRosterTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            val url = "https://pleagueofficial.com/team/${teamID.ID}"
            val doc = Jsoup.connect(url).get()
            doc.select("div.row.player_list")
                .first()
                .children()
                .select("div.col-md-3.col-6.mb-grid-gutter")
                .forEach {
                    var playerID = -1
                    it.children()
                        .select("a")
                        .forEach {
                            val regex = "^<a .*?/player/([0-9]*?)\"><(.*?)></a>\$".toRegex()
                            playerID = regex.find(it.toString())?.groups?.get(1)?.value?.toInt()!!
                        }
                    val regex =
                        "^#([0-9]*?) (.*?) ([a-zA-Z]*)(.*?)([0-9]*.[0-9]*.[0-9]*?) ｜ (.*?cm) ｜ (.*?kg) (?:.*)\$".toRegex()
                    val parsed = regex.find(it.text())
                    val number = parsed?.groups?.get(1)?.value
                    val name = parsed?.groups?.get(2)?.value
                    val position = parsed?.groups?.get(3)?.value
                    val eng_name = parsed?.groups?.get(4)?.value
                    val birthday = parsed?.groups?.get(5)?.value
                    val height = parsed?.groups?.get(6)?.value
                    val weight = parsed?.groups?.get(7)?.value

                    val player = Player(
                        playerID = PlayerID(PLGID = playerID),
                        firstName = name!!,
                        teamId = teamData.teamId,
                        number = number!!,
                        position = position!!,
                    )
                    players.add(player)
                    teamData.playerList.add(player.playerID)
                }
            OkHttp(PlayerIdOnSuccessResponse()).getRequest(
                "player_season_data",
                mapOf(
                    "season_id" to "4",
                    "part" to "info",
                    "team_id" to "19,20,21,22,23,24"
                ),
                "GC"
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun PlayerIdOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object : OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                Log.d("DEBUG", "HIHIHIHI")
                val playerList = result?.let { GCStatsParser().parse<GCPlayerID>(it) }
                if (playerList != null) {
                    for (player in playerList) {
                        getPlayerByName(player.info.name)?.GCID = player.info.id
                    }
                }
            }
        }
    }

    inner class FetchTeamRankingTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            val doc =
                Jsoup.connect("https://pleagueofficial.com/standings/2021-22").get()
            doc.select("tr.bg-gray.text-light.text-center")
                .parallelStream()
                .filter { it != null }
                .forEach {
                    println(it.text())
                    val regex =
                        "^([0-9]) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*)\$".toRegex()
                    val parsed = regex.find(it.text())
                    var ranking = parsed?.groups?.get(1)?.value
                    val teamName = parsed?.groups?.get(2)?.value
                    val total = parsed?.groups?.get(9)?.value
                    val win = parsed?.groups?.get(10)?.value
                    val lose = parsed?.groups?.get(11)?.value
                    val gb = parsed?.groups?.get(13)?.value
                    val streakL = parsed?.groups?.get(14)?.value
                    val streakN = parsed?.groups?.get(15)?.value

                    val teamID = teamName?.let { it1 -> getTeamIdByName(it1) }
                    val team = getTeamById(teamID)

                    ranking += if (ranking == "1") "st" else if (ranking == "2") "nd" else if (ranking == "3") "rd" else "th"
                    team?.ranking = ranking!!

                    team?.totalRecord = Record(win!!.toInt(), lose!!.toInt())
                    team?.streak = streakL!! + streakN!!

                    team?.gamesBack = gb!!
                }
            true
        } catch (e: Exception) {
            false
        }
    }
}



