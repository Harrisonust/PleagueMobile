package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TeamStats : PlayerStats(), Parcelable {
    init {
        data["pointsOffTurnovers"] = 0F
        data["timeoutRemaining"] = 0F
        data["timeRemaining"] = 0F
    }
}