package com.example.gamechangermobile.models

import android.os.Parcelable
import android.util.Log
import com.example.gamechangermobile.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_player.*
import java.util.*

@Parcelize
class Player(val FirstName:String="",
             val LastName:String="",
             var ProfilePic: Int= R.drawable.ic_baseline_sports_basketball_24,
             var stats: Map<Date, PlayerStats> = mapOf<Date, PlayerStats>(),
             var team: Team = Team("", "", 0),
             var age:Int = 0,
             var number:String = "",
             var position:String = "",

             ): Parcelable {
                var FullName: String = ""
                    get() {
                        return "$FirstName $LastName"
                    }
                fun getStats(date: Date, type: String): Float {
                    return this.stats[date]?.data?.get(type) ?: 0F
                }
             }
