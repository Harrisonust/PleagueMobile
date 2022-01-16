package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game (
    val hostScore: Int,
    val guestScore: Int,
    val remainingTime: String,
    var GuestTeam: Team,
    var HostTeam: Team
    ): Parcelable
