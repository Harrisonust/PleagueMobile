package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Team(val Name:String,
           var Location: String,
           var ProfilePic:Int,
           var record: String="",
           var ranking: String="",
           var playerList: ArrayList<Player> = ArrayList<Player>()): Parcelable {

}