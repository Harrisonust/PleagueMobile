package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game (
    val guestScore: Int,
    val hostScore: Int,
    val remainingTime: String,
    val hostName: String,
    val hostRecord: String,
    val hostImg: Int,
    val guestName: String,
    val guestRecord: String,
    val guestImg: Int
    ): Parcelable{}