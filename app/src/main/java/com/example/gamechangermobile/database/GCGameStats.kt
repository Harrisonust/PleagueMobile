package com.example.gamechangermobile.database

data class GCPlayerID(
    val info: GCPlayerInfo = GCPlayerInfo()
)

data class GCPlayerInfoWithBox(
    val info: GCPlayerInfo = GCPlayerInfo(),
    val box: GCPlayerStats = GCPlayerStats()
)

data class GCPlayerInfoWithBoxAndAdv(
    val info: GCPlayerInfo = GCPlayerInfo(),
    val box: ArrayList<GCPlayerStats> = arrayListOf<GCPlayerStats>(),
    val advancement: ArrayList<GCPlayerAdv> = arrayListOf<GCPlayerAdv>()
)

data class GCPlayer(
    val info: GCPlayerInfo = GCPlayerInfo(),
    val box: ArrayList<GCPlayerStats> = arrayListOf(),
    val on_off_court: GCPlayerOnOffCourt = GCPlayerOnOffCourt(),
    val eff: ArrayList<GCPlayerEff> = arrayListOf(),
    val advancement: ArrayList<GCPlayerAdv> = arrayListOf(),
    val vs_defense: GCPlayerVsDefense = GCPlayerVsDefense(),
    val opp_vs_defense: GCPlayerOppVsDefense = GCPlayerOppVsDefense()
)

data class GCPlayerInfoWithFullBox(
    val info: GCPlayerInfo = GCPlayerInfo(),
    val box: ArrayList<GCPlayerStats> = arrayListOf<GCPlayerStats>()
)

//data class GCPlayerBasicInfo(
//    val id: Int = 0,
//    val name: String = "",
//) {}
//
//data class GCPlayerID(
//    val info: GCPlayerBasicInfo = GCPlayerBasicInfo()
//) {}

data class GCPlayerInfo(
    val id: Int = 0,

    // personal
    val player_name: String = "",
    val name: String = "",
    val player_jersey_number: Int = 0,
    val height: Int? = 0,
    val weight: Int? = 0,
    val birthday: String? = "",
    val is_foreign_player: Boolean = false,
//    val photo: GCPhoto = GCPhoto(),

    // team
    val team_id: Int = 0,
    val team_name: String = "",
//    val team_pts: Int = 0,

    // game
    val game_id: Int = 0,
    val game_date: String = "",
    val game_name: String = "",
    val game_category: GCGameCategory = GCGameCategory(),
    val opponent_team_name: String = "",
    val opponent_team_pts: Int = 0,
    val team_pts: Int = 0,
    val is_home: Boolean = false,
//    val is_win: Boolean = false,
//    val is_starter: Boolean = false,
    val matched: Int = 0,
    val record_matches: Int = 0,
    val season_name: String = "",
//
//    // unknown
//    val state: String? = "",
//    val split: String? = "",
//    val split_info: String = "",

)

data class GCPlayerOnOffCourt(
    val on_court: ArrayList<GCPlayerAdv> = arrayListOf(),
    val off_court: ArrayList<GCPlayerAdv> = arrayListOf()
)

data class GCPlayerEff(
    val ppp_rounds_100: Float = 0F,
    val opp_ppp_rounds_100: Float = 0F,
)

data class GCPlayerVsDefense(
    val vs_man: ArrayList<VsDefense> = arrayListOf(),
    val vs_zone: ArrayList<VsDefense> = arrayListOf(),
    val vs_transition: ArrayList<VsDefense> = arrayListOf(),
    val vs_second_chance: ArrayList<VsDefense> = arrayListOf(),
)

data class GCPlayerOppVsDefense(
    val opp_vs_man: ArrayList<VsDefense> = arrayListOf(),
    val opp_vs_zone: ArrayList<VsDefense> = arrayListOf(),
    val opp_vs_transition: ArrayList<VsDefense> = arrayListOf(),
    val opp_vs_second_chance: ArrayList<VsDefense> = arrayListOf(),
)

data class VsDefense(
    val ppp: Float = 0F,
    val pts_per_36: Float = 0F
)

data class GCTeam(
    val info: GCTeamInfo = GCTeamInfo(),
    val ranking: GCRankings = GCRankings(),
)

data class GCRanking(
    val ranking: Int? = 0,
    val value: Float? = 0F,
    val avg: Float? = 0F
)

data class GCTag(
    val name: String = ""
)

data class GCPhoto(
    val image: String? = "",
    val tag: GCTag? = GCTag(),
    val file_name: String? = ""
)

data class GCPlayerAdv(
    val def_reb: Int = 0,
    val to: Float = 0F,
    val ppp: Float = 0F,
    val play: Int = 0,
    val available_def_reb: Int = 0,
    val avg_to: Float = 0F,
    val avg_pass_pts: Float = 0F,
    val avg_def_reb: Float = 0F,
    val avg_available_def_reb: Float = 0F,
    val avg_available_off_reb: Float = 0F,
    val pts: Int = 0,
    val off_reb_rate: Float = 0F,
    val inc_pass_play: Int = 0,
    val plus_minus: Int = 0,
    val pass_pts: Int = 0,
    val available_off_reb: Int = 0,
    val non_pass_to: Int = 0,
    val avg_pass_to: Float = 0F,
    val off_reb: Int = 0,
    val pass: Int = 0,
    val avg_play: Float = 0F,
    val usg: Float = 0F,
    val inc_pass_ppp: Float = 0F,
    val plus_minus_per_36: Float = 0F,
    val potential_ast: Int = 0,
    val avg_non_pass_to: Float = 0F,
    val avg_potential_ast: Float = 0F,
    val to_rate: Float = 0F,
    val def_reb_rate: Float = 0F,
    val avg_off_reb: Float = 0F,
    val inc_pass_pts: Int = 0,
    val usg_inc_pass: Float = 0F,
    val avg_pass: Float = 0F,
    val pass_to: Int = 0,
    val avg_pts: Float = 0F,

    val opp_ppp_rounds_100: Float = 0F,
    val ppp_rounds_100: Float = 0F,
)

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
    val avg_min: Float = 0F,
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
)

data class GCGameCategory(
    val id: Int? = 0,
    val name: String? = "",
    val description: String? = "",
    val start_date: String? = "",
    val end_date: String? = ""
)

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
)

data class GCTeamInfo(
    val id: Int = 0,
    val name: String = "",
//    val photo: String = "",
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
)

data class GCRankings(
    val team: GCRanking? = GCRanking(),

    val avg_pts: GCRanking? = GCRanking(),
    val avg_fg_m: GCRanking? = GCRanking(),
    val avg_fg_a: GCRanking? = GCRanking(),
    val avg_fg_pts: GCRanking? = GCRanking(),
    val fg_percent: GCRanking? = GCRanking(),

    val avg_two_pts: GCRanking? = GCRanking(),
    val avg_two_fg_m: GCRanking? = GCRanking(),
    val avg_two_fg_a: GCRanking? = GCRanking(),
    val avg_two_fg_pts: GCRanking? = GCRanking(),
    val two_pts_percent: GCRanking? = GCRanking(),

    val avg_three_pts: GCRanking? = GCRanking(),
    val avg_three_fg_m: GCRanking? = GCRanking(),
    val avg_three_fg_a: GCRanking? = GCRanking(),
    val avg_three_fg_pts: GCRanking? = GCRanking(),
    val three_pts_percent: GCRanking? = GCRanking(),

    val avg_ft_pts: GCRanking? = GCRanking(),
    val avg_ft_fg_m: GCRanking? = GCRanking(),
    val avg_ft_fg_a: GCRanking? = GCRanking(),
    val avg_ft_fg_pts: GCRanking? = GCRanking(),
    val ft_percent: GCRanking? = GCRanking(),

    val avg_reb: GCRanking? = GCRanking(),
    val avg_ast: GCRanking? = GCRanking(),
    val avg_stl: GCRanking? = GCRanking(),
    val avg_blk: GCRanking? = GCRanking(),
    val avg_to: GCRanking? = GCRanking(),

    val ppp: GCRanking? = GCRanking(),
    val opp_ppp: GCRanking? = GCRanking(),
    val to_rate: GCRanking? = GCRanking(),
    val opp_to_rate: GCRanking? = GCRanking(),
    val off_reb_rate: GCRanking? = GCRanking(),
    val def_reb_rate: GCRanking? = GCRanking(),
)

data class PlgTeamBox(
    val mins: String = "",
    val points: String = "",

    val two: String = "",
    val two_m: String = "",
    val twop: String = "",

    val trey: String = "",
    val trey_m: String = "",
    val treyp: String = "",

    val ft: String = "",
    val ft_m: String = "",
    val ftp: String = "",

    val reb: String = "",
    val reb_o: String = "",
    val reb_d: String = "",

    val ast: String = "",

    val stl: String = "",
    val blk: String = "",
    val turnover: String = "",
    val pfoul: String = "",

    val positive: String = "",
    val seconds: String = "",

    )

data class PlgPlayerBox(
    val name_alt: String? = "",
    val player_id: String? = "",
    val name: String? = "",
    val jersey: String? = "",
    val position: String? = "",

    val points: String? = "",
    val positive: String? = "",
    val seconds: String? = "",
    val starter: String? = "",
    val turnover: String? = "",
    val ast: String? = "",
    val blk: String? = "",
    val reb_d: String? = "",
    val eff: String? = "",
    val efgp: String? = "",
    val ft_m: String? = "",
    val ft_a: String? = "",
    val reb_o: String? = "",
    val pfoul: String? = "",
    val profile_picture: String? = "",
    val stl: String? = "",
    val trey_m: String? = "",
    val trey_a: String? = "",
    val reb: String? = "",
    val tsp: String? = "",
    val two_m: String? = "",
    val two_a: String? = "",
    val ugp: String? = "",
    val mins: String? = "",
    val two: String? = "",
    val two_m_two: String? = "",
    val twop: String? = "",
    val trey: String? = "",
    val trey_m_trey: String? = "",
    val treyp: String? = "",
    val ft: String? = "",
    val ft_m_ft: String? = "",
    val ftp: String? = ""
)

data class PlgData(
    val q1_home: String = "",
    val q2_home: String = "",
    val q3_home: String = "",
    val q4_home: String = "",
    val q1_away: String = "",
    val q2_away: String = "",
    val q3_away: String = "",
    val q4_away: String = "",
    val score_home: String = "",
    val score_away: String = "",
    val home: List<PlgPlayerBox> = arrayListOf<PlgPlayerBox>(),
    val home_total: PlgTeamBox = PlgTeamBox(),
    val away: List<PlgPlayerBox> = arrayListOf<PlgPlayerBox>(),
    val away_total: PlgTeamBox = PlgTeamBox(),
)

data class PlgGame(
    val error: String = "",
    val data: PlgData = PlgData(),
)

