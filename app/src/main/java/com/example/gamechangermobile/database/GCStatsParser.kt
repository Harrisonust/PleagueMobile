package com.example.gamechangermobile.database

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import java.io.StringReader


//var dataList = StatsParser().parse(test_json)
//Log.d("Debug", "id 0: " + dataList?.get(0)?.info?.opponent_team_name.toString())
//Log.d("Debug", "id 1: " + dataList?.get(1)?.info?.opponent_team_name.toString())


class GCStatsParser() {
    fun parsePlayersInfoWithBox(data: String): ArrayList<GCPlayerInfoWithBox> {
        var playersInfo = arrayListOf<GCPlayerInfoWithBox>()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val d: GCPlayerInfoWithBox? = Klaxon().parse<GCPlayerInfoWithBox>(reader)
                    d?.let { playersInfo.add(it) }
                }
            }
        }
        return playersInfo
    }

    fun parsePlayersInfoWithFullBox(data: String): ArrayList<GCPlayerInfoWithFullBox> {
        var playerStatsList = arrayListOf<GCPlayerInfoWithFullBox>()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val d: GCPlayerInfoWithFullBox? =
                        Klaxon().parse<GCPlayerInfoWithFullBox>(reader)
                    d?.let { playerStatsList.add(it) }
                }
            }
        }
        return playerStatsList
    }

    fun parseTeamsData(data: String): ArrayList<GCTeam> {
        var teams = arrayListOf<GCTeam>()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val d = Klaxon().parse<GCTeam>(reader)
                    d?.let { teams.add(it) }
                }
            }
        }
        return teams
    }

    fun parseTeamData(data: String): GCTeam {
        var team = GCTeam()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    Klaxon().parse<GCTeam>(reader)?.let { team = it }
                }
            }
        }
        return team
    }

    fun parseGameData(data: String): ArrayList<GCGame> {
        val dataList = arrayListOf<GCGame>()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val d: GCGame? = Klaxon().parse<GCGame>(reader)
                    d?.let { dataList.add(it) }
                }
            }
        }
        return dataList
    }

}
