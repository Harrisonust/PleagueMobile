package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.R
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Player(val firstName:String="",
             val lastName:String="",
             var profilePic: Int= R.drawable.ic_baseline_sports_basketball_24,
             var stats: Map<Date, PlayerStats> = mapOf<Date, PlayerStats>(),
             var team: Team = Team("", "", 0),
             var age:Int = 0,
             var number:String = "",
             var position:String = "",

             ): Parcelable {
                var fullName: String = ""
                    get() {
                        return "$firstName $lastName"
                    }
                fun getStats(date: Date, type: String): Float {
                    return this.stats[date]?.data?.get(type) ?: 0F
                }
             }
