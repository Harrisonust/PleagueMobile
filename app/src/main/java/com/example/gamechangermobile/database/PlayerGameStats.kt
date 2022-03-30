package com.example.gamechangermobile.database

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import java.io.StringReader
import kotlin.collections.ArrayList


//var dataList = StatsParser().parse(test_json)
//Log.d("Debug", "id 0: " + dataList?.get(0)?.info?.opponent_team_name.toString())
//Log.d("Debug", "id 1: " + dataList?.get(1)?.info?.opponent_team_name.toString())

class StatsParser() {
    fun parse_team_data(data: String): Team{
        var team = Team()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    Klaxon().parse<Team>(reader)?.let { team = it }
                }
            }
        }
        return team
    }

    fun parse_game_data(data: String): ArrayList<Game> {
        val dataList = arrayListOf<Game>()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val d: Game? = Klaxon().parse<Game>(reader)
                    d?.let { dataList.add(it) }
                }
            }
        }
        return dataList
    }

    fun parse_player_game_data(data: String): ArrayList<PlayerGameStats> {
        val dataList = arrayListOf<PlayerGameStats>()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val d = Klaxon().parse<PlayerGameStats>(reader)
                    dataList.add(d!!)
                }
            }
        }
        return dataList
    }

    fun parse_player_game_data2(data: String): ArrayList<PlayerGameStats2> {
        val dataList = arrayListOf<PlayerGameStats2>()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val d = Klaxon().parse<PlayerGameStats2>(reader)
                    dataList.add(d!!)
                }
            }
        }
        return dataList
    }
}

class PlayerGameStats(val info: PlayerInfo = PlayerInfo(), val box: PlayerStats = PlayerStats()) {

}

class PlayerGameStats2(
    val info: PlayerInfo = PlayerInfo(),
    val box: ArrayList<PlayerStats> = arrayListOf()
) {

}

class PlayerInfo(
    val id: Int = 0,

    // personal
    val player_name: String = "",
    val name: String = player_name,
    val player_jersey_number: Int = 0,
    val height: Float? = 0F,
    val weight: Float? = 0F,
    val birthday: String = "",
    val is_foreign_player: Boolean = false,
//    val photo: Photo = Photo(),

    // team
    val team_id: Int = 0,
    val team_name: String = "",
    val team_pts: Int = 0,

    // game
    val game_id: Int = 0,
    val game_date: String = "",
    val game_name: String = "",
    val game_category: GameCategory = GameCategory(),
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

) {

}

class Team(
    val info: TeamInfo = TeamInfo(),
    val ranking: Ranking = Ranking(),
) {

}

class Ranking(
    val ranking: Int = 0,
    val value: Float = 0F,
    val avg: Float = 0F
) {}

class Tag(
    val name: String = ""
) {

}

class Photo(
    val image: String? = "",
    val tag: Tag? = Tag(),
    val file_name: String? = ""
) {}

class PlayerStats(
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
) {

}

class GameCategory(
    val id: Int? = 0,
    val name: String? = "",
    val description: String? = "",
    val start_date: String? = "",
    val end_date: String? = ""
) {

}

class Game(
    val id: Int = 0,
    val quarter_count: Int = 0,
    val date: String = "",
    val category: GameCategory = GameCategory(),
    val name: String = "",
    val home_team_id: Int = 0,
    val home_team_name: String = "",
    val home_team_logo: Photo = Photo(),
    val home_team_score: Int = 0,

    val away_team_id: Int = 0,
    val away_team_name: String = "",
    val away_team_logo: Photo = Photo(),
    val away_team_score: Int = 0,
) {


}

class TeamInfo(
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


class TeamStats(
    val info: TeamInfo = TeamInfo(),
    val ranking: Rankings = Rankings()
) {}

class Rankings(
    val team: Ranking = Ranking(),

    val avg_pts: Ranking = Ranking(),
    val avg_fg_m: Ranking = Ranking(),
    val avg_fg_a: Ranking = Ranking(),
    val avg_fg_pts: Ranking = Ranking(),
    val fg_percent: Ranking = Ranking(),

    val avg_two_pts: Ranking = Ranking(),
    val avg_two_fg_m: Ranking = Ranking(),
    val avg_two_fg_a: Ranking = Ranking(),
    val avg_two_fg_pts: Ranking = Ranking(),
    val two_pts_percent: Ranking = Ranking(),

    val avg_three_pts: Ranking = Ranking(),
    val avg_three_fg_m: Ranking = Ranking(),
    val avg_three_fg_a: Ranking = Ranking(),
    val avg_three_fg_pts: Ranking = Ranking(),
    val three_pts_percent: Ranking = Ranking(),

    val avg_ft_pts: Ranking = Ranking(),
    val avg_ft_fg_m: Ranking = Ranking(),
    val avg_ft_fg_a: Ranking = Ranking(),
    val avg_ft_fg_pts: Ranking = Ranking(),
    val ft_percent: Ranking = Ranking(),

    val avg_reb: Ranking = Ranking(),
    val avg_ast: Ranking = Ranking(),
    val avg_stl: Ranking = Ranking(),
    val avg_blk: Ranking = Ranking(),
    val avg_to: Ranking = Ranking(),

    val ppp: Ranking = Ranking(),
    val opp_ppp: Ranking = Ranking(),
    val to_rate: Ranking = Ranking(),
    val opp_to_rate: Ranking = Ranking(),
    val off_reb_rate: Ranking = Ranking(),
    val def_reb_rate: Ranking = Ranking(),
    ) {

}

val test_team_json = """
    
    [
        {
            "info": {
                "id": 23,
                "name": "新北國王",
                "photo": "",
                "split": "NONE",
                "split_info": {},
                "matches": 31,
                "record_matches": 18,
                "win_count": 12,
                "lose_count": 6,
                "win_rate": 0.667,
                "home_win_rate": 0.714,
                "away_win_rate": 0.636,
                "winning_streak": 4,
                "game_behind": 0.0,
                "season_name": "2021-2022賽季"
            },
            "ranking": {
                "team": {
                    "ranking": 1,
                    "value": 0.667,
                    "avg": 0.502
                },
                "avg_pts": {
                    "ranking": 1,
                    "value": 99.444,
                    "avg": 93.024
                },
                "avg_fg_m": {
                    "ranking": 3,
                    "value": 34.222,
                    "avg": 34.541
                },
                "avg_fg_a": {
                    "ranking": 5,
                    "value": 84.722,
                    "avg": 85.338
                },
                "avg_fg_pts": {
                    "ranking": 2,
                    "value": 81.333,
                    "avg": 79.107
                },
                "avg_two_pts_m": {
                    "ranking": 6,
                    "value": 21.333,
                    "avg": 24.517
                },
                "avg_two_pts_a": {
                    "ranking": 6,
                    "value": 44.556,
                    "avg": 51.107
                },
                "avg_two_pts": {
                    "ranking": 6,
                    "value": 42.667,
                    "avg": 49.034
                },
                "avg_three_pts_m": {
                    "ranking": 1,
                    "value": 12.889,
                    "avg": 10.024
                },
                "avg_three_pts_a": {
                    "ranking": 1,
                    "value": 40.167,
                    "avg": 34.23
                },
                "avg_three_pts": {
                    "ranking": 1,
                    "value": 38.667,
                    "avg": 30.073
                },
                "avg_ft_m": {
                    "ranking": 1,
                    "value": 18.111,
                    "avg": 13.916
                },
                "avg_ft_a": {
                    "ranking": 2,
                    "value": 24.444,
                    "avg": 20.778
                },
                "avg_ft_pts": {
                    "ranking": 1,
                    "value": 18.111,
                    "avg": 13.916
                },
                "avg_reb": {
                    "ranking": 3,
                    "value": 49.444,
                    "avg": 48.378
                },
                "avg_ast": {
                    "ranking": 2,
                    "value": 18.333,
                    "avg": 17.961
                },
                "avg_stl": {
                    "ranking": 3,
                    "value": 8.889,
                    "avg": 9.34
                },
                "avg_blk": {
                    "ranking": 4,
                    "value": 3.389,
                    "avg": 3.829
                },
                "avg_to": {
                    "ranking": 2,
                    "value": 15.833,
                    "avg": 15.501
                },
                "fg_percent": {
                    "ranking": 3,
                    "value": 0.404,
                    "avg": 0.405
                },
                "ft_percent": {
                    "ranking": 1,
                    "value": 0.741,
                    "avg": 0.669
                },
                "two_pts_percent": {
                    "ranking": 3,
                    "value": 0.479,
                    "avg": 0.48
                },
                "three_pts_percent": {
                    "ranking": 1,
                    "value": 0.321,
                    "avg": 0.291
                },
                "ppp": {
                    "ranking": 2,
                    "value": 0.828,
                    "avg": 0.787
                },
                "opp_ppp": {
                    "ranking": 3,
                    "value": 0.804,
                    "avg": 0.787
                },
                "to_rate": {
                    "ranking": 2,
                    "value": 0.132,
                    "avg": 0.131
                },
                "opp_to_rate": {
                    "ranking": 5,
                    "value": 0.122,
                    "avg": 0.131
                },
                "off_reb_rate": {
                    "ranking": 1,
                    "value": 0.192,
                    "avg": 0.173
                },
                "def_reb_rate": {
                    "ranking": 6,
                    "value": 0.152,
                    "avg": 0.18
                }
            }
        }
    ]
"""

