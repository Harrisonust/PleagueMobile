package com.example.gamechangermobile.playerpage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamechangermobile.database.*
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.network.OkHttp
import kotlin.collections.set

class PlayerViewModel(playerGCID: Int) : ViewModel() {
    // network call required parameter
    private val apiSource = "GC"

    private val player = MutableLiveData<Player>()
    fun getPlayerBasicInfo(): LiveData<Player> {
        return player
    }

    private val playerParams = mapOf(
        "season_id" to "4",
        "part" to "info,box",
        "player_id" to playerGCID.toString()
    )

    private fun callPlayerBasicInfoApi() {
        OkHttp(PlayerBasicInfoOnSuccessResponse()).getRequest(
            "player_season_data",
            playerParams,
            apiSource
        )
    }

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
        "STL", "BLK", "TOV", "PF", "EFF"
    )

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
    private val chartStats = MutableLiveData<Map<String, Float>>()
    fun getChartStats(): LiveData<Map<String, Float>> {
        return chartStats
    }

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
        "STL", "BLK", "TOV", "PF"
    )

    fun getCareerAvg(): LiveData<Map<String, List<String>>> {
        return careerAvg
    }

    fun getCareerAcc(): LiveData<Map<String, List<String>>> {
        return careerAcc
    }

    private fun callCareerAndStatsApi() {
        OkHttp(CareerAndStatsOnSuccessResponse()).getRequest(
            careerApiPath,
            careerQueryParams,
            apiSource
        )
    }

    // adv section
    private val adv = MutableLiveData<Map<String, List<String>>>()
    val advHeaders = listOf(
        "MIN", "PER36", "USG%", "ORTG", "TOR"
    )

    fun getAdv(): LiveData<Map<String, List<String>>> {
        return adv
    }

    // team eff section
    private val effApiPath = "player_season_data"
    private val effQueryParams = mapOf(
        "season_id" to "4",
        "part" to "info,+box,+on_off_court,+eff,+advancement,+vs_defense,+opp_vs_defense",
        "player_id" to playerGCID.toString(),
        "show_all_quarters" to "true",
        "split_type" to "NONE"
    )
    private val eff = MutableLiveData<Map<String, List<String>>>()
    val effHeaders = listOf(
        "ON (100RD)", "OFF (100RD)", "EFF (100RD)",
        "ORB", "MAN", "ZONE", "TRANS", "2CH",
        "TRANS PTS", "2CH PTS"
    )

    fun getEff(): LiveData<Map<String, List<String>>> {
        return eff
    }

    private fun callAdvAndEffApi() {
        OkHttp(AdvAndEffOnSuccessResponse()).getRequest(
            effApiPath,
            effQueryParams,
            apiSource
        )
    }

    init {
        Log.d("VIEWMODEL", "Player ID $playerGCID viewModel is created.")
        callPlayerBasicInfoApi()
        callGameRecordsApi()
        callCareerAndStatsApi()
        callAdvAndEffApi()
    }

    private fun PlayerBasicInfoOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object : OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                val playerInfoList = result?.let { StatsParser().parse<GCPlayerInfoWithBox>(it) }
                if (playerInfoList != null) {
                    // update player data
                    val p = Player()
                    p.averageStat.data["points"] = playerInfoList[0].box.avg_pts
                    p.averageStat.data["rebounds"] = playerInfoList[0].box.avg_reb
                    p.averageStat.data["assists"] = playerInfoList[0].box.avg_ast
                    p.firstName = playerInfoList[0].info.name
                    p.number = playerInfoList[0].info.player_jersey_number.toString()
                    p.team = Dictionary.teams[playerInfoList[0].info.team_name].toString()
                    player.postValue(p)
                }
            }
        }
    }

    private fun GameRecordsOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object : OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                val updatedGameRecords = mutableMapOf<String, List<String>>()
                val gameRecordList = result?.let { StatsParser().parse<GCPlayerInfoWithBox>(it) }
                if (gameRecordList != null) {
                    for (gameRecord in gameRecordList) {
                        val gameInfo = "${
                            gameRecord.info.game_date.substring(
                                0,
                                10
                            )
                        }\n${gameRecord.info.game_name}(${gameRecord.info.game_category.name})"

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
                        stats.add(Utils.formatStat((gameRecord.box.fg_percent)*100)+"%")

                        stats.add(gameRecord.box.two_pts.toString())
                        stats.add(gameRecord.box.two_pts_m.toString())
                        stats.add(gameRecord.box.two_pts_a.toString())
                        stats.add(Utils.formatStat((gameRecord.box.two_pts_percent)*100)+"%")

                        stats.add(gameRecord.box.three_pts.toString())
                        stats.add(gameRecord.box.three_pts_m.toString())
                        stats.add(gameRecord.box.three_pts_a.toString())
                        stats.add(Utils.formatStat((gameRecord.box.three_pts_percent)*100)+"%")

                        stats.add(gameRecord.box.ft_pts.toString())
                        stats.add(gameRecord.box.ft_m.toString())
                        stats.add(gameRecord.box.ft_a.toString())
                        stats.add(Utils.formatStat((gameRecord.box.ft_percent)*100)+"%")

                        stats.add(gameRecord.box.off_reb.toString())
                        stats.add(gameRecord.box.def_reb.toString())

                        stats.add(gameRecord.box.stl.toString())
                        stats.add(gameRecord.box.blk.toString())
                        stats.add(gameRecord.box.to.toString())
                        stats.add(gameRecord.box.pf.toString())
                        stats.add(gameRecord.box.eff.toString())

                        updatedGameRecords[gameInfo] = stats
                    }
                }
                gameRecords.postValue(updatedGameRecords)
            }
        }
    }

    private fun CareerAndStatsOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object : OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                val updatedCareerAcc = mutableMapOf<String, List<String>>()
                val updatedCareerAvg = mutableMapOf<String, List<String>>()
                val updatedChartStats = mutableMapOf<String, Float>()
                val career = result?.let { StatsParser().parse<GCPlayerInfoWithBox>(it) }
                career?.forEachIndexed { i, data ->
                    // Career Section
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
                    stats.add(Utils.formatStat((data.box.fg_percent)*100)+"%")

                    stats.add(data.box.two_pts.toString())
                    stats.add(data.box.two_pts_m.toString())
                    stats.add(data.box.two_pts_a.toString())
                    stats.add(Utils.formatStat((data.box.two_pts_percent)*100)+"%")

                    stats.add(data.box.three_pts.toString())
                    stats.add(data.box.three_pts_m.toString())
                    stats.add(data.box.three_pts_a.toString())
                    stats.add(Utils.formatStat((data.box.three_pts_percent)*100)+"%")

                    stats.add(data.box.ft_pts.toString())
                    stats.add(data.box.ft_m.toString())
                    stats.add(data.box.ft_a.toString())
                    stats.add(Utils.formatStat((data.box.ft_percent)*100)+"%")

                    stats.add(data.box.off_reb.toString())
                    stats.add(data.box.def_reb.toString())

                    stats.add(data.box.stl.toString())
                    stats.add(data.box.blk.toString())
                    stats.add(data.box.to.toString())
                    stats.add(data.box.pf.toString())

                    updatedCareerAcc[data.info.season_name] = stats

                    val avgStats = mutableListOf<String>()
                    avgStats.add(data.info.record_matches.toString())
                    avgStats.add(Utils.getPlayingTimeInMinutesString(data.box.avg_min))

                    avgStats.add(Utils.formatStat(data.box.avg_pts))
                    avgStats.add(Utils.formatStat(data.box.avg_reb))
                    avgStats.add(Utils.formatStat(data.box.avg_ast))

                    avgStats.add(Utils.formatStat(data.box.avg_fg_pts))
                    avgStats.add(Utils.formatStat(data.box.avg_fg_m))
                    avgStats.add(Utils.formatStat(data.box.avg_fg_a))
                    avgStats.add(Utils.formatStat(data.box.avg_fg_percent))

                    avgStats.add(Utils.formatStat(data.box.avg_two_pts))
                    avgStats.add(Utils.formatStat(data.box.avg_two_pts_m))
                    avgStats.add(Utils.formatStat(data.box.avg_two_pts_a))
                    avgStats.add(Utils.formatStat(data.box.avg_two_pts_percent))

                    avgStats.add(Utils.formatStat(data.box.avg_three_pts))
                    avgStats.add(Utils.formatStat(data.box.avg_three_pts_m))
                    avgStats.add(Utils.formatStat(data.box.avg_three_pts_a))
                    avgStats.add(Utils.formatStat(data.box.avg_three_pts_percent))

                    avgStats.add(Utils.formatStat(data.box.avg_ft_pts))
                    avgStats.add(Utils.formatStat(data.box.avg_ft_m))
                    avgStats.add(Utils.formatStat(data.box.avg_ft_a))
                    avgStats.add(Utils.formatStat(data.box.avg_ft_percent))

                    avgStats.add(Utils.formatStat(data.box.avg_off_reb))
                    avgStats.add(Utils.formatStat(data.box.avg_def_reb))

                    avgStats.add(Utils.formatStat(data.box.avg_stl))
                    avgStats.add(Utils.formatStat(data.box.avg_blk))
                    avgStats.add(Utils.formatStat(data.box.avg_to))
                    avgStats.add(Utils.formatStat(data.box.avg_pf))

                    updatedCareerAvg[data.info.season_name] = avgStats

                    // Stats Section
                    // update chart stats for the latest season
                    if (i == career.size - 1) { // the latest season
                        updatedChartStats["PTS"] = data.box.avg_pts
                        updatedChartStats["REB"] = data.box.avg_reb
                        updatedChartStats["AST"] = data.box.avg_ast
                        updatedChartStats["STL"] = data.box.avg_stl
                        updatedChartStats["BLK"] = data.box.avg_blk
                        updatedChartStats["TOV"] = data.box.avg_to
                    }

                }
                careerAcc.postValue(updatedCareerAcc)
                careerAvg.postValue(updatedCareerAvg)
                chartStats.postValue(updatedChartStats)
            }
        }

    }

    private fun AdvAndEffOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object : OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                val updatedAdv = mutableMapOf<String, List<String>>()
                val updatedEff = mutableMapOf<String, List<String>>()
                val fullStatsList = result?.let { StatsParser().parse<GCPlayer>(it) }
                if (fullStatsList != null) {
                    val advColumns = listOf("整季", "第一節", "第二節", "第三節", "第四節", "OT")
                    val columns = listOf(
                        "整季\n(團隊)",
                        "整季\n(對手)",
                        "第一節\n(團隊)",
                        "第一節\n(對手)",
                        "第二節\n(團隊)",
                        "第二節\n(對手)",
                        "第三節\n(團隊)",
                        "第三節\n(對手)",
                        "第四節\n(團隊)",
                        "第四節\n(對手)",
                        "OT\n(團隊)",
                        "OT\n(對手)"
                    )
                    for (i in fullStatsList[0].advancement.indices) {
                        // ADV Section
                        // "MIN", "PER36", "USG%", "ORTG", "TOR"
                        val advStats = mutableListOf<String>()
                        advStats.add(Utils.getPlayingTimeInMinutesString(fullStatsList[0].box[i].avg_min))
                        advStats.add(Utils.formatStat(fullStatsList[0].advancement[i].plus_minus_per_36))
                        advStats.add(Utils.formatStat(fullStatsList[0].advancement[i].usg))
                        advStats.add(Utils.formatStat(fullStatsList[0].advancement[i].ppp))
                        advStats.add(Utils.formatStat(fullStatsList[0].advancement[i].to_rate))
                        updatedAdv[advColumns[i]] = advStats

                        // Eff Section
                        // "ON (100RD)", "OFF (100RD)", "EFF (100RD)",
                        // "ORB", "MAN", "ZONE", "TRANS", "2CH",
                        // "TRANS PTS", "2CH PTS"
                        val stats = mutableListOf<String>()
                        stats.add(Utils.formatStat(fullStatsList[0].on_off_court.on_court[i].ppp_rounds_100))
                        stats.add(Utils.formatStat(fullStatsList[0].on_off_court.off_court[i].ppp_rounds_100))
                        stats.add(Utils.formatStat(fullStatsList[0].eff[i].ppp_rounds_100))
                        stats.add(Utils.formatStat((fullStatsList[0].advancement[i].off_reb_rate)*100)+"%")
                        stats.add(Utils.formatStat(fullStatsList[0].vs_defense.vs_man[i].ppp))
                        stats.add(Utils.formatStat(fullStatsList[0].vs_defense.vs_zone[i].ppp))
                        stats.add(Utils.formatStat(fullStatsList[0].vs_defense.vs_transition[i].ppp))
                        stats.add(Utils.formatStat(fullStatsList[0].vs_defense.vs_second_chance[i].ppp))
                        stats.add(Utils.formatStat(fullStatsList[0].vs_defense.vs_transition[i].pts_per_36))
                        stats.add(Utils.formatStat(fullStatsList[0].vs_defense.vs_second_chance[i].pts_per_36))
                        updatedEff[columns[2 * i]] = stats

                        val oppStats = mutableListOf<String>()
                        oppStats.add(Utils.formatStat(fullStatsList[0].on_off_court.on_court[i].opp_ppp_rounds_100))
                        oppStats.add(Utils.formatStat(fullStatsList[0].on_off_court.off_court[i].opp_ppp_rounds_100))
                        oppStats.add(Utils.formatStat(fullStatsList[0].eff[i].opp_ppp_rounds_100))
                        oppStats.add(Utils.formatStat((fullStatsList[0].advancement[i].def_reb_rate)*100)+"%")
                        oppStats.add(Utils.formatStat(fullStatsList[0].opp_vs_defense.opp_vs_man[i].ppp))
                        oppStats.add(Utils.formatStat(fullStatsList[0].opp_vs_defense.opp_vs_zone[i].ppp))
                        oppStats.add(Utils.formatStat(fullStatsList[0].opp_vs_defense.opp_vs_transition[i].ppp))
                        oppStats.add(Utils.formatStat(fullStatsList[0].opp_vs_defense.opp_vs_second_chance[i].ppp))
                        oppStats.add(Utils.formatStat(fullStatsList[0].opp_vs_defense.opp_vs_transition[i].pts_per_36))
                        oppStats.add(Utils.formatStat(fullStatsList[0].opp_vs_defense.opp_vs_second_chance[i].pts_per_36))
                        updatedEff[columns[2 * i + 1]] = oppStats
                    }
                    eff.postValue(updatedEff)
                    adv.postValue(updatedAdv)
                }
            }
        }

    }
}