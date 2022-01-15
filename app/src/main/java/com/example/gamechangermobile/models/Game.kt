package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game (
    val hostScore: Int,
    val guestScore: Int,
    val remainingTime: String,
    val hostName: String,
    val hostRecord: String,
    val hostImg: String,
    val guestName: String,
    val guestRecord: String,
    val guestImg: String
    ): Parcelable