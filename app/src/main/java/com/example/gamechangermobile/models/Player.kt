package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.MainActivity.Companion.playersMap
import com.example.gamechangermobile.R
import kotlinx.android.parcel.Parcelize

@Parcelize
class Player(
    var playerID: PlayerID = PlayerID(),
    var GCID: Int = 0,
    var firstName: String = "",
    val lastName: String = "",
    var profilePic: Int = R.drawable.ic_user_foreground,
    var stats: MutableMap<GameID, PlayerStats> = mutableMapOf<GameID, PlayerStats>(),
    var averageStat: PlayerStats = PlayerStats(),
    var teamId: TeamID = TeamID(-1),
    var team: String = "",
    var age: Int = 0,
    var number: String = "",
    var position: String = "",
    var isForeignPlayer: Boolean = false,
    var isStarter: Boolean = false, // for game box score
    var mins: String = ""

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

//    var averageStat: PlayerStats = PlayerStats()
//        get() {
//            var averagePlayerStats = PlayerStats()
//
//            accumulatedStats.data.forEach {
//                averagePlayerStats.data[it.key] = accumulatedStats.data[it.key]!! / gamePlayed
//            }
//
//            return averagePlayerStats
//        }

    var accumulatedStats: PlayerStats = PlayerStats()
        get() {
            var sumPlayerStats = PlayerStats()
            stats.values.forEachIndexed { _, eachGameStats ->
                eachGameStats.data.forEach {
                    sumPlayerStats.data[it.key] =
                        sumPlayerStats.data[it.key]!! + eachGameStats.data[it.key]!!
                }
            }

            return sumPlayerStats
        }

    fun getStat(gameId: GameID): PlayerStats {
        return this.stats[gameId] ?: PlayerStats()
    }

    fun setStat(gameId: GameID, stats: PlayerStats) {
        this.stats[gameId] = stats
    }
}

fun getAllPlayer(): MutableSet<PlayerID> {
    return playersMap.toList().map { it.second.playerID }.toMutableSet()
}

fun getPlayerByName(name: String): Player? {
    for (player in playersMap.values) {
        if (player.firstName == name)
            return player
    }
    return null
}

fun getPlayerByGCID(GCID: Int): Player? {
    for (player in playersMap.values) {
        if (player.GCID == GCID)
            return player
    }
    return null
}
