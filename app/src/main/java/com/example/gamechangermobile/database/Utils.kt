package com.example.gamechangermobile.database

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class Utils {
    companion object {
        fun getPlayingTimeInMinutesString(sec: Int): String {
            val minutes = sec / 60
            val seconds = sec % 60
            val secondString = if (seconds < 10) "0$seconds" else "$seconds"
            return "$minutes:$secondString"
        }

        fun getPlayingTimeInMinutesString(sec: Float): String {
            val minutes = sec.toInt() / 60
            val seconds = (sec % 60).toDouble().roundToInt()
            val secondString = if (seconds < 10) "0$seconds" else "$seconds"
            return "$minutes:$secondString"
        }

        fun formatStat(stat: Float): String {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.HALF_UP
            return df.format(stat).toString()
        }
    }
}