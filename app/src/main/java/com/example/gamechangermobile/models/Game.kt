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
    val guestScore: Int,
    val hostScore: Int,
    val status: GameStatus,
    val quarter: String,
    val remainingTime: String,
    ): Parcelable
