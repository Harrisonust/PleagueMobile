package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class Game(
        var GuestTeam: Team,
        var HostTeam: Team,
        val GuestPlayerStats: MutableMap<Player, PlayerStats> = mutableMapOf(),
        val HostPlayerStats: MutableMap<Player, PlayerStats> = mutableMapOf(),
        val status: GameStatus = GameStatus.NO_STATUS,
        val startingTime: Date = Date(),
        val quarter: String = "",
        val remainingTime: String = "",
        val date: Date = Date(),
        val winner: Team = Team()
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
            for (playerStats in GuestPlayerStats.values) {
                playerStats.data.forEach {
                    gameStats.data[it.key] = gameStats.data[it.key]!! + playerStats.data[it.key]!!
                }
            }
            return gameStats
        }

    var hostStats: TeamStats = TeamStats()
        get() {
            var gameStats = TeamStats()
            for (playerStats in HostPlayerStats.values) {
                playerStats.data.forEach {
                    gameStats.data[it.key] = gameStats.data[it.key]!! + playerStats.data[it.key]!!
                }
            }
            return gameStats
        }

    var hostPointLeader: Player = Player()
        get() {
            return HostPlayerStats.maxByOrNull { it.value.data["points"]!! }?.key ?: Player()
        }
    var hostReboundLeader: Player = Player()
        get() {
            return HostPlayerStats.maxByOrNull { it.value.data["rebounds"]!! }?.key ?: Player()
        }
    var hostAssistLeader: Player = Player()
        get() {
            return HostPlayerStats.maxByOrNull { it.value.data["assists"]!! }?.key ?: Player()
        }
    var hostStealLeader: Player = Player()
        get() {
            return HostPlayerStats.maxByOrNull { it.value.data["steals"]!! }?.key ?: Player()
        }
    var hostBlockLeader: Player = Player()
        get() {
            return HostPlayerStats.maxByOrNull { it.value.data["blocks"]!! }?.key ?: Player()
        }
    var guestPointLeader: Player = Player()
        get() {
            return GuestPlayerStats.maxByOrNull { it.value.data["points"]!! }?.key ?: Player()
        }
    var guestReboundLeader: Player = Player()
        get() {
            return GuestPlayerStats.maxByOrNull { it.value.data["rebounds"]!! }?.key ?: Player()
        }
    var guestAssistLeader: Player = Player()
        get() {
            return GuestPlayerStats.maxByOrNull { it.value.data["assists"]!! }?.key ?: Player()
        }
    var guestStealLeader: Player = Player()
        get() {
            return GuestPlayerStats.maxByOrNull { it.value.data["steals"]!! }?.key ?: Player()
        }
    var guestBlockLeader: Player = Player()
        get() {
            return GuestPlayerStats.maxByOrNull { it.value.data["blocks"]!! }?.key ?: Player()
        }

    var location: String = HostTeam.location

    fun getPlayerStats(player: Player): PlayerStats? {
        return GuestPlayerStats[player] ?: HostPlayerStats[player]
    }


}

