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

    var hostPointLeader: Player = Player()
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["points"]!! }?.key ?: Player()
        }
    var hostReboundLeader: Player = Player()
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["rebounds"]!! }?.key ?: Player()
        }
    var hostAssistLeader: Player = Player()
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["assists"]!! }?.key ?: Player()
        }
    var hostStealLeader: Player = Player()
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["steals"]!! }?.key ?: Player()
        }
    var hostBlockLeader: Player = Player()
        get() {
            return hostPlayerStats.maxByOrNull { it.value.data["blocks"]!! }?.key ?: Player()
        }
    var guestPointLeader: Player = Player()
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["points"]!! }?.key ?: Player()
        }
    var guestReboundLeader: Player = Player()
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["rebounds"]!! }?.key ?: Player()
        }
    var guestAssistLeader: Player = Player()
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["assists"]!! }?.key ?: Player()
        }
    var guestStealLeader: Player = Player()
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["steals"]!! }?.key ?: Player()
        }
    var guestBlockLeader: Player = Player()
        get() {
            return guestPlayerStats.maxByOrNull { it.value.data["blocks"]!! }?.key ?: Player()
        }

    var location: String = getTeamById(TeamID(hostTeam.ID))!!.location

    fun getPlayerStats(player: Player): PlayerStats? {
        return guestPlayerStats[player] ?: hostPlayerStats[player]
    }

    val winner: TeamID
    get() {
        if (guestStats.data["points"]!! > hostStats.data["points"]!!)
            return guestTeam
        else
            return hostTeam
    }

}

