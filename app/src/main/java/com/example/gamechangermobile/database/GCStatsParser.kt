package com.example.gamechangermobile.database

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import java.io.StringReader


//var dataList = StatsParser().parse(test_json)
//Log.d("Debug", "id 0: " + dataList?.get(0)?.info?.opponent_team_name.toString())
//Log.d("Debug", "id 1: " + dataList?.get(1)?.info?.opponent_team_name.toString())


class GCStatsParser() {
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

    fun parsePlayerGameData(data: String): ArrayList<GCPlayerGameStats> {
        val dataList = arrayListOf<GCPlayerGameStats>()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val d = Klaxon().parse<GCPlayerGameStats>(reader)
                    dataList.add(d!!)
                }
            }
        }
        return dataList
    }

    fun parsePlayerGameData2(data: String): ArrayList<GCPlayerGameStats2> {
        val dataList = arrayListOf<GCPlayerGameStats2>()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val d = Klaxon().parse<GCPlayerGameStats2>(reader)
                    dataList.add(d!!)
                }
            }
        }
        return dataList
    }
}
