package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Team(val Name:String, var ProfilePic:Int): Parcelable {
    var playerList = listOf<Player>()
}