package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.R
import com.example.gamechangermobile.Utils.sha256
import kotlinx.android.parcel.Parcelize

@Parcelize
class Player(
    val firstName: String = "",
    val lastName: String = "",
    var profilePic: Int = R.drawable.ic_baseline_sports_basketball_24,
    var stats: MutableMap<GameID, PlayerStats> = mutableMapOf<GameID, PlayerStats>(),
    var teamId: TeamID = TeamID(-1),
    var age: Int = 0,
    var number: String = "",
    var position: String = "",

    ) : Parcelable {
    val playerID: PlayerID
        get() {
            return PlayerID(fullName.sha256())
        }

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

            accumulatedStats.data.forEach {
                averagePlayerStats.data[it.key] = accumulatedStats.data[it.key]!! / gamePlayed
            }

            return averagePlayerStats
        }

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

fun getAllPlayer(): Set<Player> {
    val playerList = mutableSetOf<Player>()
    val allTeamIDList = getAllTeam()
    for (teamId in allTeamIDList) {
        val team = getTeamById(teamId)
        if (team != null) {
            for (player in team.playerList)
                playerList.add(player)
        }
    }
    return playerList
}
