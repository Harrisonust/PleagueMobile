package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.R
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Game(
    val gameId: GameID,
    val gameType: String,
    var guestTeam: TeamID,
    var hostTeam: TeamID,
    val date: Date = Date(),
    val guestPlayerStats: MutableMap<PlayerID, PlayerStats> = mutableMapOf(),
    val hostPlayerStats: MutableMap<PlayerID, PlayerStats> = mutableMapOf(),
    var status: GameStatus = GameStatus.NO_STATUS,
    val quarter: String = "",
    val remainingTime: String = "",
    val highlightPhoto: Int = R.drawable.ic_baseline_sports_basketball_24,
    val guestScore: Int = 0,
    val hostScore: Int = 0,
    var guestScorePerQuarter: MutableList<String> = mutableListOf("0", "0", "0", "0"),
    var hostScorePerQuarter: MutableList<String> = mutableListOf("0", "0", "0", "0"),
    var description: String = "",
) : Parcelable {


    fun sumOfStat(PlayerStats: MutableMap<Player, PlayerStats>, type: String): Float {
        var sum = 0F
        for (s in PlayerStats.values) {
            sum += s.data[type]!!
        }
        return sum
    }

    var guestStats: TeamStats = TeamStats()
        get() {
            var gameStats = TeamStats()
            if (guestPlayerStats.isNotEmpty()) {
                for (playerStats in guestPlayerStats.values) {
                    playerStats.data.forEach {
                        gameStats.data[it.key] =
                            gameStats.data[it.key]!! + playerStats.data[it.key]!!
                    }
                }
            } else {
                gameStats.data["points"] = guestScore.toFloat()
            }

            return gameStats
        }

    var hostStats: TeamStats = TeamStats()
        get() {
            var gameStats = TeamStats()
            if (hostPlayerStats.isNotEmpty()) {
                for (playerStats in hostPlayerStats.values) {
                    playerStats.data.forEach {
                        gameStats.data[it.key] =
                            gameStats.data[it.key]!! + playerStats.data[it.key]!!
                    }
                }
            } else {
                gameStats.data["points"] = hostScore.toFloat()
            }
            return gameStats
        }

    var hostPointLeader: PlayerID? = null
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["points"]!! }?.key
        }

    var hostReboundLeader: PlayerID? = null
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["rebounds"]!! }?.key
        }

    var hostAssistLeader: PlayerID? = null
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["assists"]!! }?.key
        }

    var hostStealLeader: PlayerID? = null
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["steals"]!! }?.key
        }

    var hostBlockLeader: PlayerID? = null
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["blocks"]!! }?.key
        }

    var guestPointLeader: PlayerID? = null
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["points"]!! }?.key
        }

    var guestReboundLeader: PlayerID? = null
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["rebounds"]!! }?.key
        }

    var guestAssistLeader: PlayerID? = null
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["assists"]!! }?.key
        }

    var guestStealLeader: PlayerID? = null
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["steals"]!! }?.key
        }

    var guestBlockLeader: PlayerID? = null
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["blocks"]!! }?.key
        }

    var location: String = getTeamById(TeamID(hostTeam.ID))!!.location

    fun getPlayerStats(playerID: PlayerID): PlayerStats? {
        return guestPlayerStats[playerID] ?: hostPlayerStats[playerID]
    }

    val winner: TeamID
        get() = if (guestStats.data["points"]!! > hostStats.data["points"]!!)
            guestTeam
        else
            hostTeam


}

