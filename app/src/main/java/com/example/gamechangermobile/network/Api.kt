package com.example.gamechangermobile.network

object Api {
    private const val API_HEADER = "https://api.gamechanger.tw/api"
    fun url(path: String, queryParams: Map<String, String>):String {
        return "$API_HEADER/$path/${queryParamsString(queryParams)}"
    }
    private fun queryParamsString(queryParams: Map<String, String>): String {
        var queryParamsString = "?"
        for ((key, value) in queryParams) {
            queryParamsString += "$key=$value&"
        }
        return queryParamsString
    }
}