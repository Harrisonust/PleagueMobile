package com.example.gamechangermobile.models

import android.os.Parcelable
import android.util.Log
import com.example.gamechangermobile.R
import kotlinx.android.parcel.Parcelize

@Parcelize
class Player(val FirstName:String="",
             val LastName:String="",
             var ProfilePic: Int= R.drawable.ic_baseline_sports_basketball_24,
             var stats: PlayerStats= PlayerStats(),
             var team: Team = Team("", "", 0),
             var age:Int = 0,
             var number:String = "",
             var position:String = "",

             ): Parcelable {
                var FullName: String = ""
                    get() {
                        return "$FirstName $LastName"
                    }
             }