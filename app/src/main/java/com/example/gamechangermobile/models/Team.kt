package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
class Team(val name: String,
           var location: String,
           var profilePic: Int,
           var totalRecord: String = "",
           var homeRecord: String = "",
           var awayRecord: String = "",
           var streak: String = "",
           var last10: String = "",
           var arena: String = "",
           var foundingDate: Date = Date(),
           var ranking: String = "",
           var playerList: ArrayList<Player> = ArrayList<Player>()) : Parcelable {

}