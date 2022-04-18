package com.example.gamechangermobile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.MainActivity.Companion.teams
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.GCTeam
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.example.gamechangermobile.views.DynamicTable
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class StatsFragmentTeamTab() : Fragment() {
    private val networkRequestCallback: UrlRequestCallback.OnFinishRequest =
        networkRequestCallbackFunc()
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallback)

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {

                var teamList = result?.let { GCStatsParser().parse<GCTeam>(it) }

                if (teamList != null) {
                    for (gcteam in teamList) {
                        val team = getTeamById(TeamID(gcteam.info.id))
                        team?.totalRecord = Record(gcteam.info.win_count, gcteam.info.lose_count)
                        val strk = gcteam.info.winning_streak.toString()
                        team?.streak = if (strk.toInt() > 0) "W$strk" else "L${strk.toInt() * -1}"

                        var rank = gcteam.ranking.team.ranking.toString()
                        rank += if (rank == "1") "st" else if (rank == "2") "nd" else "rd"
                        team?.ranking = rank
                    }
                }

                activity?.runOnUiThread {

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats_team_tab, container, false)

        val myBuilder = CronetEngine.Builder(context)
        val cronetEngine: CronetEngine = myBuilder.build()
        val executor: Executor = Executors.newSingleThreadExecutor()

//        /api/team_season_data/?season_id=4&part=info,ranking
        val requestBuilder =
            cronetEngine.newUrlRequestBuilder(
                Api.url(
                    "team_season_data", mapOf(
                        "season_id" to "4",
                        "part" to "info,ranking"
                    ), source = "GC"
                ),
                urlRequestCallback,
                executor
            )
        val request: UrlRequest = requestBuilder.build()
        request.start()

        val dynamicTable: DynamicTable = view.findViewById(R.id.dynamic_table)
        val headers = listOf("W", "L", "WIN%", "GB", "HOME", "ROAD", "LAST 10", "STREAK")
        val teams = mutableMapOf<Team, List<String>>()
        for (team in MainActivity.teams.sortedBy { it.ranking }) {
            val stats = mutableListOf<String>()
            stats.add(team.totalRecord.wins.toInt().toString())
            stats.add(team.totalRecord.loses.toInt().toString())
            if (team.totalRecord.wins + team.totalRecord.loses != 0)
                stats.add((team.totalRecord.wins / (team.totalRecord.wins + team.totalRecord.loses)).toString())
            stats.add("0")
            stats.add(team.homeRecord.getRecord())
            stats.add(team.awayRecord.getRecord())
            stats.add(team.last10.getRecord())
            stats.add(team.streak)
            teams[team] = stats
        }
        dynamicTable.renderStandingsTable(
            headers,
            teams,
            90,
            280,
            "cell_view_header_longer",
            "player_data",
            "cell_view_column",
            "player_name",
            "player_image",
            "cell_view_content_longer",
            "player_data"
        )
        return view
    }


}