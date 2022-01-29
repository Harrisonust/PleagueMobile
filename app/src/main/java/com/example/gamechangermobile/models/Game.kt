package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class Game(
    val gameId: GameID,
    var guestTeam: TeamID,
    var hostTeam: TeamID,
    val date: Date = Date(),
    val guestPlayerStats: MutableMap<Player, PlayerStats> = mutableMapOf(),
    val hostPlayerStats: MutableMap<Player, PlayerStats> = mutableMapOf(),
    val status: GameStatus = GameStatus.NO_STATUS,
    val quarter: String = "",
    val remainingTime: String = "",
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
            for (playerStats in guestPlayerStats.values) {
                playerStats.data.forEach {
                    gameStats.data[it.key] = gameStats.data[it.key]!! + playerStats.data[it.key]!!
                }
            }
            return gameStats
        }

    var hostStats: TeamStats = TeamStats()
        get() {
            var gameStats = TeamStats()
            for (playerStats in hostPlayerStats.values) {
                playerStats.data.forEach {
                    gameStats.data[it.key] = gameStats.data[it.key]!! + playerStats.data[it.key]!!
                }
            }
            return gameStats
        }

    var hostPointLeader: Player? = null
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["points"]!! }?.key
        }

    var hostReboundLeader: Player? = null
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["rebounds"]!! }?.key
        }

    var hostAssistLeader: Player? = null
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["assists"]!! }?.key
        }

    var hostStealLeader: Player? = null
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["steals"]!! }?.key
        }

    var hostBlockLeader: Player? = null
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["blocks"]!! }?.key
        }

    var guestPointLeader: Player? = null
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["points"]!! }?.key
        }

    var guestReboundLeader: Player? = null
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["rebounds"]!! }?.key
        }

    var guestAssistLeader: Player? = null
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["assists"]!! }?.key
        }

    var guestStealLeader: Player? = null
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["steals"]!! }?.key
        }

    var guestBlockLeader: Player? = null
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["blocks"]!! }?.key
        }

    var location: String = getTeamById(TeamID(hostTeam.ID))!!.location

    fun getPlayerStats(player: Player): PlayerStats? {
        return guestPlayerStats[player] ?: hostPlayerStats[player]
    }

    val winner: TeamID
        get() = if (guestStats.data["points"]!! > hostStats.data["points"]!!)
            guestTeam
        else
            hostTeam


}

