package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Team(val Name:String,
           var Location: String,
           var ProfilePic:Int,
           var total_record: String="",
           var home_record: String="",
           var away_record: String="",
           var streak: String="",
           var last10: String="",
           var arena: String="",
           var founding_date:String="",
           var ranking: String="",
           var playerList: ArrayList<Player> = ArrayList<Player>()): Parcelable {

}