package com.example.gamechangermobile.playerpage

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.gamechangermobile.MainActivity.Companion.games
import com.example.gamechangermobile.database.Dictionary
import com.example.gamechangermobile.database.GCGame
import com.example.gamechangermobile.database.GCPlayerInfoWithBox
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class PlayerViewModel(playerGCID: Int) : ViewModel() {
    private val okHttp = OkHttp(OkHttpOnSuccessResponse())

    // network call required parameter
    private val apiSource = "GC"
    private val apiPath = "player_game_data"
    private val gameRecordQueryParams = mapOf(
        "season_id" to "4",
        "part" to "info,box",
        "player_id" to playerGCID.toString()
    )

    private val gameRecords = MutableLiveData<Map<String, List<String>>>()
    val gameRecordsHeaders = listOf(
        "OPP", "MSCR", "H/A", "MIN",
        "PTS", "REB", "AST",
        "FG", "FGM", "FGA", "FG%",
        "2P", "2PM", "2PA", "2P%",
        "3P", "3PM", "3PA", "3P%",
        "FT", "FTM", "FTA", "FT%",
        "OREB", "DREB",
        "STL", "BLK", "TOV", "PF", "EFF")

    init {
        Log.d("VIEWMODEL", "Player ID $playerGCID viewModel is created.")
        okHttp.getRequest(
            apiPath,
            gameRecordQueryParams,
            apiSource
        )
    }

    fun getGameRecords(): LiveData<Map<String, List<String>>> {
        return gameRecords
    }

    private fun OkHttpOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object: OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                Log.d("RESPONSE", result!!)

                val updatedGameRecords = mutableMapOf<String, List<String>>()
                val gameRecordList = result?.let { GCStatsParser().parse<GCPlayerInfoWithBox>(it) }
                if (gameRecordList != null) {
                    for (gameRecord in gameRecordList) {
                        val gameInfo = "${gameRecord.info.game_name}(${gameRecord.info.game_category.name})\n${gameRecord.info.game_date.substring(0, 10)}"

                        // "OPP", "MSCR", "H/A", "MIN", "PTS", "REB", "AST", "FG", "FGM", "FGA", "FG%", "2P", "2PM", "2PA", "2P%", "3P", "3PM", "3PA", "3P%", "FT", "FTM", "FTA", "FT%", "OREB", "DREB", "STL", "BLK", "TOV", "PF", "EFF"
                        val stats = mutableListOf<String>()
                        stats.add(Dictionary.teams[gameRecord.info.opponent_team_name]!!)
                        // match score
                        stats.add(if (gameRecord.info.is_home) "${gameRecord.info.team_pts}:${gameRecord.info.opponent_team_pts}" else "${gameRecord.info.opponent_team_pts}:${gameRecord.info.team_pts}")
                        // home or away
                        stats.add(if (gameRecord.info.is_home) "Home" else "Away")
                        stats.add("${gameRecord.box.min/100}:${gameRecord.box.min%100}")

                        stats.add(gameRecord.box.pts.toString())
                        stats.add(gameRecord.box.reb.toString())
                        stats.add(gameRecord.box.ast.toString())

                        stats.add(gameRecord.box.fg_pts.toString())
                        stats.add(gameRecord.box.fg_m.toString())
                        stats.add(gameRecord.box.fg_a.toString())
                        stats.add(gameRecord.box.fg_percent.toString())

                        stats.add(gameRecord.box.two_pts.toString())
                        stats.add(gameRecord.box.two_pts_m.toString())
                        stats.add(gameRecord.box.two_pts_a.toString())
                        stats.add(gameRecord.box.two_pts_percent.toString())

                        stats.add(gameRecord.box.three_pts.toString())
                        stats.add(gameRecord.box.three_pts_m.toString())
                        stats.add(gameRecord.box.three_pts_a.toString())
                        stats.add(gameRecord.box.three_pts_percent.toString())

                        stats.add(gameRecord.box.ft_pts.toString())
                        stats.add(gameRecord.box.ft_m.toString())
                        stats.add(gameRecord.box.ft_a.toString())
                        stats.add(gameRecord.box.ft_percent.toString())

                        stats.add(gameRecord.box.off_reb.toString())
                        stats.add(gameRecord.box.def_reb.toString())

                        stats.add(gameRecord.box.stl.toString())
                        stats.add(gameRecord.box.blk.toString())
                        stats.add(gameRecord.box.to.toString())
                        stats.add(gameRecord.box.pf.toString())
                        stats.add(gameRecord.box.eff.toString())
//                        val stats = mutableMapOf<String, String>()
//                        stats["OPP"] = gameRecord.info.opponent_team_name
//                        // match score
//                        stats["MSCR"] = if (gameRecord.info.is_home) "${gameRecord.info.team_pts}:${gameRecord.info.opponent_team_pts}" else "${gameRecord.info.opponent_team_pts}:${gameRecord.info.team_pts}"
//                        // home or away
//                        stats["H/A"] = if (gameRecord.info.is_home) "Home" else "Away"
//                        stats["MIN"] = "${gameRecord.box.min/100}:${gameRecord.box.min%100}"
//
//                        stats["PTS"] = gameRecord.box.pts.toString()
//                        stats["REB"] = gameRecord.box.reb.toString()
//                        stats["AST"] = gameRecord.box.ast.toString()
//
//                        stats["FG"] = gameRecord.box.fg_pts.toString()
//                        stats["FGM"] = gameRecord.box.fg_m.toString()
//                        stats["FGA"] = gameRecord.box.fg_a.toString()
//                        stats["FG%"] = gameRecord.box.fg_percent.toString()
//
//                        stats["2P"] = gameRecord.box.two_pts.toString()
//                        stats["2PM"] = gameRecord.box.two_pts_m.toString()
//                        stats["2PA"] = gameRecord.box.two_pts_a.toString()
//                        stats["2P%"] = gameRecord.box.two_pts_percent.toString()
//
//                        stats["3P"] = gameRecord.box.three_pts.toString()
//                        stats["3PM"] = gameRecord.box.three_pts_m.toString()
//                        stats["3PA"] = gameRecord.box.three_pts_a.toString()
//                        stats["3P%"] = gameRecord.box.three_pts_percent.toString()
//
//                        stats["FT"] = gameRecord.box.ft_pts.toString()
//                        stats["FTM"] = gameRecord.box.ft_m.toString()
//                        stats["FTA"] = gameRecord.box.ft_a.toString()
//                        stats["FT%"] = gameRecord.box.ft_percent.toString()
//
//                        stats["OREB"] = gameRecord.box.off_reb.toString()
//                        stats["DREB"] = gameRecord.box.def_reb.toString()
//                        stats["STL"] = gameRecord.box.stl.toString()
//                        stats["BLK"] = gameRecord.box.blk.toString()
//                        stats["TOV"] = gameRecord.box.to.toString()
//                        stats["PF"] = gameRecord.box.pf.toString()
//                        stats["EFF"] = gameRecord.box.eff.toString()

                        updatedGameRecords[gameInfo]= stats
                    }
                }
                gameRecords.postValue(updatedGameRecords)
            }
        }
    }
}