package com.example.gamechangermobile.database

import kotlin.collections.ArrayList



data class GCPlayerGameStats(
    val info: GCPlayerInfo = GCPlayerInfo(),
    val box: GCPlayerStats = GCPlayerStats()
) {}

data class GCPlayerGameStats2(
    val info: GCPlayerInfo = GCPlayerInfo(),
    val box: ArrayList<GCPlayerStats> = arrayListOf()
) {}

data class GCPlayerInfo(
    val id: Int = 0,

    // personal
    val player_name: String = "",
    val name: String = player_name,
    val player_jersey_number: Int = 0,
    val height: Float? = 0F,
    val weight: Float? = 0F,
    val birthday: String = "",
    val is_foreign_player: Boolean = false,
//    val photo: GCPhoto = GCPhoto(),

    // team
    val team_id: Int = 0,
    val team_name: String = "",
    val team_pts: Int = 0,

    // game
    val game_id: Int = 0,
    val game_date: String = "",
    val game_name: String = "",
    val game_category: GCGameCategory = GCGameCategory(),
    val opponent_team_name: String = "",
    val opponent_team_pts: Int = 0,
    val is_home: Boolean = false,
    val is_win: Boolean = false,
    val is_starter: Boolean = false,
    val matched: Int = 0,
    val record_matches: Int = 0,
    val season_name: String = "",
//
//    // unknown
    val state: String = "",
    val split: String = "",
//    val split_info: String = "",

) {}

data class GCTeam(
    val info: GCTeamInfo = GCTeamInfo(),
    val ranking: GCRankings = GCRankings(),
) {}

data class GCRanking(
    val ranking: Int = 0,
    val value: Float = 0F,
    val avg: Float = 0F
) {}

data class GCTag(
    val name: String = ""
) {}

data class GCPhoto(
    val image: String? = "",
    val tag: GCTag? = GCTag(),
    val file_name: String? = ""
) {}

data class GCPlayerStats(
    val min: Int = 0,

    val pts: Int = 0,
    val ast: Int = 0,
    val reb: Int = 0,
    val off_reb: Int = 0,
    val def_reb: Int = 0,

    val blk: Int = 0,
    val stl: Int = 0,
    val to: Int = 0,
    val pf: Int = 0,

    val eff: Int = 0,

    val fg_pts: Int = 0,
    val fg_m: Int = 0,
    val fg_a: Int = 0,
    val fg_percent: Float = 0.0F,

    val two_pts: Int = 0,
    val two_pts_m: Int = 0,
    val two_pts_a: Int = 0,
    val two_pts_percent: Float = 0.0F,

    val three_pts: Int = 0,
    val three_pts_m: Int = 0,
    val three_pts_a: Int = 0,
    val three_pts_percent: Float = 0.0F,

    val ft_pts: Int = 0,
    val ft_m: Int = 0,
    val ft_a: Int = 0,
    val ft_percent: Float = 0.0F,

    val play: Int = 0,

//// avg

    val avg_pts: Float = 0F,
    val avg_ast: Float = 0F,
    val avg_reb: Float = 0F,
    val avg_off_reb: Float = 0F,
    val avg_def_reb: Float = 0F,

    val avg_blk: Float = 0F,
    val avg_stl: Float = 0F,
    val avg_to: Float = 0F,
    val avg_pf: Float = 0F,

    val avg_fg_pts: Float = 0F,
    val avg_fg_m: Float = 0F,
    val avg_fg_a: Float = 0F,
    val avg_fg_percent: Float = 0.0F,

    val avg_two_pts: Float = 0F,
    val avg_two_pts_m: Float = 0F,
    val avg_two_pts_a: Float = 0F,
    val avg_two_pts_percent: Float = 0.0F,

    val avg_three_pts: Float = 0F,
    val avg_three_pts_m: Float = 0F,
    val avg_three_pts_a: Float = 0F,
    val avg_three_pts_percent: Float = 0.0F,

    val avg_ft_pts: Float = 0F,
    val avg_ft_m: Float = 0F,
    val avg_ft_a: Float = 0F,
    val avg_ft_percent: Float = 0.0F,

    val avg_play: Float = 0F,
) {}

data class GCGameCategory(
    val id: Int? = 0,
    val name: String? = "",
    val description: String? = "",
    val start_date: String? = "",
    val end_date: String? = ""
) {}

data class GCGame(
    val id: Int = 0,
    val quarter_count: Int = 0,
    val date: String = "",
    val category: GCGameCategory = GCGameCategory(),
    val name: String = "",
    val home_team_id: Int = 0,
    val home_team_name: String = "",
    val home_team_logo: GCPhoto = GCPhoto(),
    val home_team_score: Int = 0,

    val away_team_id: Int = 0,
    val away_team_name: String = "",
    val away_team_logo: GCPhoto = GCPhoto(),
    val away_team_score: Int = 0,
) {}

data class GCTeamInfo(
    val id: Int = 0,
    val name: String = "",
    val photo: String = "",
    val split: String? = "",

    val matches: Int = 0,
    val record_matches: Int = 0,
    val win_count: Int = 0,
    val lose_count: Int = 0,
    val win_rate: Float = 0F,
    val home_win_rate: Float = 0F,
    val away_win_rate: Float = 0F,
    val winning_streak: Int = 0,
    val game_behind: Float = 0F,
    val season_name: String = ""
) {}


data class GCTeamStats(
    val info: GCTeamInfo = GCTeamInfo(),
    val ranking: GCRankings = GCRankings()
) {}

data class GCRankings(
    val team: GCRanking = GCRanking(),

    val avg_pts: GCRanking = GCRanking(),
    val avg_fg_m: GCRanking = GCRanking(),
    val avg_fg_a: GCRanking = GCRanking(),
    val avg_fg_pts: GCRanking = GCRanking(),
    val fg_percent: GCRanking = GCRanking(),

    val avg_two_pts: GCRanking = GCRanking(),
    val avg_two_fg_m: GCRanking = GCRanking(),
    val avg_two_fg_a: GCRanking = GCRanking(),
    val avg_two_fg_pts: GCRanking = GCRanking(),
    val two_pts_percent: GCRanking = GCRanking(),

    val avg_three_pts: GCRanking = GCRanking(),
    val avg_three_fg_m: GCRanking = GCRanking(),
    val avg_three_fg_a: GCRanking = GCRanking(),
    val avg_three_fg_pts: GCRanking = GCRanking(),
    val three_pts_percent: GCRanking = GCRanking(),

    val avg_ft_pts: GCRanking = GCRanking(),
    val avg_ft_fg_m: GCRanking = GCRanking(),
    val avg_ft_fg_a: GCRanking = GCRanking(),
    val avg_ft_fg_pts: GCRanking = GCRanking(),
    val ft_percent: GCRanking = GCRanking(),

    val avg_reb: GCRanking = GCRanking(),
    val avg_ast: GCRanking = GCRanking(),
    val avg_stl: GCRanking = GCRanking(),
    val avg_blk: GCRanking = GCRanking(),
    val avg_to: GCRanking = GCRanking(),

    val ppp: GCRanking = GCRanking(),
    val opp_ppp: GCRanking = GCRanking(),
    val to_rate: GCRanking = GCRanking(),
    val opp_to_rate: GCRanking = GCRanking(),
    val off_reb_rate: GCRanking = GCRanking(),
    val def_reb_rate: GCRanking = GCRanking(),
) {}
