package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

enum class GameStatus{
    NOT_YET_START,
    INGAME,
    END
}

@Parcelize
data class Game (
    var GuestTeam: Team,
    var HostTeam: Team,
    var GuestStats: GameStats,
    var HostStats: GameStats,
    val status: GameStatus,
    val starting_time: String = "",
    val quarter: String = "",
    val remaining_time: String = "",
    val day: String = "",
    val date: String = ""
    ): Parcelable
