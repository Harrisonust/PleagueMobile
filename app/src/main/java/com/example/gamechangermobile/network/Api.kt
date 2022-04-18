package com.example.gamechangermobile.network

object Api {
    private const val API_HEADER = "https://api.gamechanger.tw/api"
    private const val P_API_HEADER = "https://pleagueofficial.com/api"

    fun url(path: String, queryParams: Map<String, String>, source: String): String =
        when(source){
            "PLG" -> "$P_API_HEADER/$path/${queryParamsString(queryParams)}"
            "GC" -> "$API_HEADER/$path${queryParamsString(queryParams)}"
            else -> "null"
        }


    private fun queryParamsString(queryParams: Map<String, String>): String {
        var queryParamsString = "?"
        for ((key, value) in queryParams) {
            queryParamsString += "$key=$value&"
        }
        return queryParamsString
    }
}