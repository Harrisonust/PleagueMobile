package com.example.gamechangermobile.models

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class Game (
    var GuestTeam: Team,
    var HostTeam: Team,
    val GuestPlayerStats: MutableMap<Player, PlayerStats> = mutableMapOf(),
    val HostPlayerStats: MutableMap<Player, PlayerStats> = mutableMapOf(),
    val status: GameStatus = GameStatus.NO_STATUS,
    val startingTime: Date = Date(),
    val quarter: String = "",
    val remainingTime: String = "",
    val date: Date = Date()
    ): Parcelable{


    fun sumOfStat(PlayerStats: MutableMap<Player, PlayerStats>, type: String): Float{
        var sum = 0F
        for(s in PlayerStats.values){
            sum += s.data[type]!!
        }
        return sum
    }

    var GuestStats: GameStats = GameStats()
        get(){
            val pointsSum = sumOfStat(GuestPlayerStats, "points")
            val reboundsSum = sumOfStat(GuestPlayerStats,"rebounds")
            val assistsSum = sumOfStat(GuestPlayerStats,"assists")
            return GameStats(points = pointsSum, rebounds = reboundsSum, assists = assistsSum)
        }

    var HostStats: GameStats = GameStats()
        get(){
            val pointsSum = sumOfStat(HostPlayerStats,"points")
            val reboundsSum = sumOfStat(HostPlayerStats,"rebounds")
            val assistsSum = sumOfStat(HostPlayerStats,"assists")
            return GameStats(points = pointsSum, rebounds = reboundsSum, assists = assistsSum)
        }

    var hostPointLeader: Player = Player()
        get() {
            return HostPlayerStats.maxByOrNull{ it.value.data["points"]!! }?.key!!
        }
    var hostReboundLeader: Player = Player()
        get() {
            return HostPlayerStats.maxByOrNull{ it.value.data["rebounds"]!! }?.key!!
        }
    var hostAssistLeader: Player = Player()
        get() {
            return HostPlayerStats.maxByOrNull{ it.value.data["assists"]!! }?.key!!
        }
    var hostStealLeader: Player = Player()
        get() {
            return HostPlayerStats.maxByOrNull{ it.value.data["steals"]!! }?.key!!
        }
    var hostBlockLeader: Player = Player()
        get() {
            return HostPlayerStats.maxByOrNull{ it.value.data["blocks"]!! }?.key!!
        }
    var guestPointLeader: Player = Player()
        get() {
            return GuestPlayerStats.maxByOrNull{ it.value.data["points"]!! }?.key!!
        }
    var guestReboundLeader: Player = Player()
        get() {
            return GuestPlayerStats.maxByOrNull{ it.value.data["rebounds"]!! }?.key!!
        }
    var guestAssistLeader: Player = Player()
        get() {
            return GuestPlayerStats.maxByOrNull{ it.value.data["assists"]!! }?.key!!
        }
    var guestStealLeader: Player = Player()
        get() {
            return GuestPlayerStats.maxByOrNull{ it.value.data["steals"]!! }?.key!!
        }
    var guestBlockLeader: Player = Player()
        get() {
            return GuestPlayerStats.maxByOrNull{ it.value.data["blocks"]!! }?.key!!
        }

}

