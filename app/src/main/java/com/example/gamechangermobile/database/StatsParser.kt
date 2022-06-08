package com.example.gamechangermobile.database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StatsParser {

    inline fun <reified T> parse(data: String): List<T> {
        val gson = Gson()
        val typeOf = object : TypeToken<List<T>>() {}.type
        return gson.fromJson(data, typeOf)
    }

    inline fun <reified T> parsePlg(data: String): T {
        val gson = Gson()
        val result = gson.fromJson(data, T::class.java)
        return result
    }
}
