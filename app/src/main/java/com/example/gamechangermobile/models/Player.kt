package com.example.gamechangermobile.models

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize

@Parcelize
class Player(val FirstName:String, val LastName:String, var ProfilePic: Int,
             var stats: PlayerStats,
             var team: Team = Team("", "", 0),
             var age:Int = 0,
             var number:String = "",
             var position:String = ""
             ): Parcelable {

}