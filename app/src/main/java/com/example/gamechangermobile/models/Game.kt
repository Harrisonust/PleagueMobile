package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class Game (
    var GuestTeam: Team,
    var HostTeam: Team,
    var GuestStats: GameStats,
    var HostStats: GameStats,
    val GuestPlayerStats: MutableMap<Player, PlayerStats> = mutableMapOf(),
    val HostPlayerStats: MutableMap<Player, PlayerStats> = mutableMapOf(),
    val status: GameStatus = GameStatus.NO_STATUS,
    val startingTime: Date = Date(),
    val quarter: String = "",
    val remainingTime: String = "",
    val date: Date = Date()
    ): Parcelable{

    var hostPointLeader: Player = Player()
        get() {
            return Player()
        }
    var hostReboundLeader: Player = Player()
        get() {
            return Player()
        }
    var hostAssistLeader: Player = Player()
        get() {
            return Player()
        }
    var hostStealLeader: Player = Player()
        get() {
            return Player()
        }
    var hostBlockLeader: Player = Player()
        get() {
            return Player()
        }
    var guestPointLeader: Player = Player()
        get() {
            return Player()
        }
    var guestReboundLeader: Player = Player()
        get() {
            return Player()
        }
    var guestAssistLeader: Player = Player()
        get() {
            return Player()
        }
    var guestStealLeader: Player = Player()
        get() {
            return Player()
        }
    var guestBlockLeader: Player = Player()
        get() {
            return Player()
        }

}

