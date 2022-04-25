package com.example.gamechangermobile.playerpage

import android.util.Log
import androidx.lifecycle.*
import com.example.gamechangermobile.database.*
import com.example.gamechangermobile.network.*

class PlayerViewModel(playerGCID: Int) : ViewModel() {
    // network call required parameter
    private val apiSource = "GC"

    // game records section
    private val gameApiPath = "player_game_data"
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
    fun getGameRecords(): LiveData<Map<String, List<String>>> {
        return gameRecords
    }
    private fun callGameRecordsApi() {
        OkHttp(GameRecordsOnSuccessResponse()).getRequest(
            gameApiPath,
            gameRecordQueryParams,
            apiSource
        )
    }

    // stats section

    // career section
    private val careerApiPath = "player_season_data"
    private val careerQueryParams = mapOf(
        "history" to "true",
        "part" to "info,box",
        "player_id" to playerGCID.toString()
    )
    private val careerAvg = MutableLiveData<Map<String, List<String>>>()
    private val careerAcc = MutableLiveData<Map<String, List<String>>>()
    val careerHeaders = listOf(
        "MT", "MIN",
        "PTS", "REB", "AST",
        "FG", "FGM", "FGA", "FG%",
        "2P", "2PM", "2PA", "2P%",
        "3P", "3PM", "3PA", "3P%",
        "FT", "FTM", "FTA", "FT%",
        "OREB", "DREB",
        "STL", "BLK", "TOV", "PF")
    fun getCareerAvg(): LiveData<Map<String, List<String>>> {
        return careerAvg
    }
    fun getCareerAcc(): LiveData<Map<String, List<String>>> {
        return careerAcc
    }
    private fun callCareerApi() {
        OkHttp(CareerOnSuccessResponse()).getRequest(
            careerApiPath,
            careerQueryParams,
            apiSource
        )
    }

    // adv section
    private val advApiPath = "player_season_data"
    private val advQueryParams = mapOf(
        "season_id" to "4",
        "part" to "info,+advancement,+box",
        "player_id" to playerGCID.toString(),
        "show_all_quarters" to "true",
        "split_type" to "NONE"
    )
    private val adv = MutableLiveData<Map<String, List<String>>>()
    val advHeaders = listOf(
        "MIN", "PER36", "USG%", "ORTG", "TOR")
    fun getAdv(): LiveData<Map<String, List<String>>> {
        return adv
    }
    private fun callAdvApi() {
        OkHttp(AdvOnSuccessResponse()).getRequest(
            advApiPath,
            advQueryParams,
            apiSource
        )
    }


    // team eff section

    init {
        Log.d("VIEWMODEL", "Player ID $playerGCID viewModel is created.")
        callGameRecordsApi()
        callCareerApi()
        callAdvApi()
    }



    private fun GameRecordsOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object: OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                Log.d("RESPONSE", result!!)

                val updatedGameRecords = mutableMapOf<String, List<String>>()
                val gameRecordList = result?.let { GCStatsParser().parse<GCPlayerInfoWithBox>(it) }
                if (gameRecordList != null) {
                    for (gameRecord in gameRecordList) {
                        val gameInfo = "${gameRecord.info.game_date.substring(0, 10)}\n${gameRecord.info.game_name}(${gameRecord.info.game_category.name})"

                        // "OPP", "MSCR", "H/A", "MIN", "PTS", "REB", "AST", "FG", "FGM", "FGA", "FG%", "2P", "2PM", "2PA", "2P%", "3P", "3PM", "3PA", "3P%", "FT", "FTM", "FTA", "FT%", "OREB", "DREB", "STL", "BLK", "TOV", "PF", "EFF"
                        val stats = mutableListOf<String>()
                        stats.add(Dictionary.teams[gameRecord.info.opponent_team_name]!!)
                        // match score
                        stats.add(if (gameRecord.info.is_home) "${gameRecord.info.team_pts}:${gameRecord.info.opponent_team_pts}" else "${gameRecord.info.opponent_team_pts}:${gameRecord.info.team_pts}")
                        // home or away
                        stats.add(if (gameRecord.info.is_home) "Home" else "Away")
                        stats.add(Utils.getPlayingTimeInMinutesString(gameRecord.box.min))

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

    private fun CareerOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object: OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                val updatedCareerAcc = mutableMapOf<String, List<String>>()
                val updatedCareerAvg = mutableMapOf<String, List<String>>()
                val career = result?.let { GCStatsParser().parse<GCPlayerInfoWithBox>(it) }
                if (career != null) {
                    for (data in career) {

                        // "MT", "MIN", "PTS", "REB", "AST", "FG", "FGM", "FGA", "FG%", "2P", "2PM", "2PA", "2P%", "3P", "3PM", "3PA", "3P%", "FT", "FTM", "FTA", "FT%", "OREB", "DREB", "STL", "BLK", "TOV", "PF", "EFF"
                        val stats = mutableListOf<String>()
                        stats.add(data.info.record_matches.toString())
                        stats.add(Utils.getPlayingTimeInMinutesString(data.box.min))

                        stats.add(data.box.pts.toString())
                        stats.add(data.box.reb.toString())
                        stats.add(data.box.ast.toString())

                        stats.add(data.box.fg_pts.toString())
                        stats.add(data.box.fg_m.toString())
                        stats.add(data.box.fg_a.toString())
                        stats.add(data.box.fg_percent.toString())

                        stats.add(data.box.two_pts.toString())
                        stats.add(data.box.two_pts_m.toString())
                        stats.add(data.box.two_pts_a.toString())
                        stats.add(data.box.two_pts_percent.toString())

                        stats.add(data.box.three_pts.toString())
                        stats.add(data.box.three_pts_m.toString())
                        stats.add(data.box.three_pts_a.toString())
                        stats.add(data.box.three_pts_percent.toString())

                        stats.add(data.box.ft_pts.toString())
                        stats.add(data.box.ft_m.toString())
                        stats.add(data.box.ft_a.toString())
                        stats.add(data.box.ft_percent.toString())

                        stats.add(data.box.off_reb.toString())
                        stats.add(data.box.def_reb.toString())

                        stats.add(data.box.stl.toString())
                        stats.add(data.box.blk.toString())
                        stats.add(data.box.to.toString())
                        stats.add(data.box.pf.toString())

                        updatedCareerAcc[data.info.season_name]= stats

                        val avgStats = mutableListOf<String>()
                        avgStats.add(data.info.record_matches.toString())
                        avgStats.add(Utils.getPlayingTimeInMinutesString(data.box.avg_min))

                        avgStats.add(data.box.avg_pts.toString())
                        avgStats.add(data.box.avg_reb.toString())
                        avgStats.add(data.box.avg_ast.toString())

                        avgStats.add(data.box.avg_fg_pts.toString())
                        avgStats.add(data.box.avg_fg_m.toString())
                        avgStats.add(data.box.avg_fg_a.toString())
                        avgStats.add(data.box.avg_fg_percent.toString())

                        avgStats.add(data.box.avg_two_pts.toString())
                        avgStats.add(data.box.avg_two_pts_m.toString())
                        avgStats.add(data.box.avg_two_pts_a.toString())
                        avgStats.add(data.box.avg_two_pts_percent.toString())

                        avgStats.add(data.box.avg_three_pts.toString())
                        avgStats.add(data.box.avg_three_pts_m.toString())
                        avgStats.add(data.box.avg_three_pts_a.toString())
                        avgStats.add(data.box.avg_three_pts_percent.toString())

                        avgStats.add(data.box.avg_ft_pts.toString())
                        avgStats.add(data.box.avg_ft_m.toString())
                        avgStats.add(data.box.avg_ft_a.toString())
                        avgStats.add(data.box.avg_ft_percent.toString())

                        avgStats.add(data.box.avg_off_reb.toString())
                        avgStats.add(data.box.avg_def_reb.toString())

                        avgStats.add(data.box.avg_stl.toString())
                        avgStats.add(data.box.avg_blk.toString())
                        avgStats.add(data.box.avg_to.toString())
                        avgStats.add(data.box.avg_pf.toString())

                        updatedCareerAvg[data.info.season_name] = avgStats

                    }
                }
                careerAcc.postValue(updatedCareerAcc)
                careerAvg.postValue(updatedCareerAvg)
            }
        }

    }

    private fun AdvOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object: OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                val updatedAdv = mutableMapOf<String, List<String>>()
                val advList = result?.let { GCStatsParser().parse<GCPlayerInfoWithBoxAndAdv>(it) }
                if (advList != null) {
                    val columns = listOf("整季", "第一節", "第二節", "第三節", "第四節", "OT")
                    for (i in advList[0].advancement.indices) {
                        // "MIN", "PER36", "USG%", "ORTG", "TOR"
                        val stats = mutableListOf<String>()
                        stats.add(Utils.getPlayingTimeInMinutesString(advList[0].box[i].avg_min))
                        stats.add(advList[0].advancement[i].plus_minus_per_36.toString())
                        stats.add(advList[0].advancement[i].usg.toString())
                        stats.add(advList[0].advancement[i].ppp.toString())
                        stats.add(advList[0].advancement[i].to_rate.toString())
                        updatedAdv[columns[i]] = stats
                    }
                    Log.d("VIEWMODEL", "Map size: ${updatedAdv.size}")
                    adv.postValue(updatedAdv)
                }
            }
        }
    }
}