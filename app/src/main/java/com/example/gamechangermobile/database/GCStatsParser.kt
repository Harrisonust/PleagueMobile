package com.example.gamechangermobile.database

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import java.io.StringReader


//var dataList = StatsParser().parse(test_json)
//Log.d("Debug", "id 0: " + dataList?.get(0)?.info?.opponent_team_name.toString())
//Log.d("Debug", "id 1: " + dataList?.get(1)?.info?.opponent_team_name.toString())


class GCStatsParser() {
    inline fun <reified T> parseItem(data: String): T{
        var item = T::class.java.newInstance()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    Klaxon().parse<T>(reader)?.let { item = it }
                }
            }
        }
        return item
    }

    inline fun <reified T> parseList(data: String): ArrayList<T> {
        var list = arrayListOf<T>()
        JsonReader(StringReader(data)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val d: T? = Klaxon().parse<T>(reader)
                    d?.let { list.add(it) }
                }
            }
        }
        return list
    }
}
