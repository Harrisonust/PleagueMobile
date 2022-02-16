package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Play(
    val teamId: TeamID,
    val timeStamp: String = "",
    val score: String= "",
    val eventDescription: String= "",
) : Parcelable {
}