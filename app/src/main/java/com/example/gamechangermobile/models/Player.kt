package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.R
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Player(
        val firstName: String = "",
        val lastName: String = "",
        var profilePic: Int = R.drawable.ic_baseline_sports_basketball_24,
        var stats: MutableMap<Date, PlayerStats> = mutableMapOf<Date, PlayerStats>(),
        var teamId: TeamID = TeamID(-1),
        var age: Int = 0,
        var number: String = "",
        var position: String = "",

        ) : Parcelable {
    var fullName: String = ""
        get() {
            return "$firstName $lastName"
        }

    var abbrName: String = ""
        get() {
            val abbrFirstName = firstName[0]
            return "$abbrFirstName. $lastName"
        }

    var gamePlayed: Int = 0
        get() {
            return stats.size
        }

    var averageStat: PlayerStats = PlayerStats()
        get() {
            var averagePlayerStats = PlayerStats()
            stats.values.forEachIndexed { _, eachGameStats ->
                eachGameStats.data.forEach {
                    averagePlayerStats.data[it.key] = averagePlayerStats.data[it.key]!! + eachGameStats.data[it.key]!!
                }
            }

            averagePlayerStats.data.forEach {
                averagePlayerStats.data[it.key] = averagePlayerStats.data[it.key]!! / gamePlayed
            }

            return averagePlayerStats
        }

    var accumulatedStats: PlayerStats = PlayerStats()
        get() {
            var averagePlayerStats = PlayerStats()
            stats.values.forEachIndexed { _, eachGameStats ->
                eachGameStats.data.forEach {
                    averagePlayerStats.data[it.key] = averagePlayerStats.data[it.key]!! + eachGameStats.data[it.key]!!
                }
            }

            return averagePlayerStats
        }

    fun getStat(date: Date, type: String): Float {
        return this.stats[date]?.data?.get(type) ?: 0F
    }

    fun getStats(date: Date): PlayerStats {
        return stats[date] ?: PlayerStats()
    }

    fun setStats(date: Date, type: String, value: Float) {
        this.stats[date]?.data?.set(type, value)
    }

    fun setStats(date: Date, stats: PlayerStats) {
        this.stats[date] = stats
    }
}
