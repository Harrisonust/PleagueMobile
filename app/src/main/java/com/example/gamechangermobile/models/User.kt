package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    var name: String = "",
    var isLogIn: Boolean = false,
    var email: String = "",
    var password: String = "",
    val favTeam: MutableSet<TeamID> = mutableSetOf<TeamID>(),
    val favPlayer: MutableSet<Player> = mutableSetOf<Player>(),
    ) : Parcelable {
}