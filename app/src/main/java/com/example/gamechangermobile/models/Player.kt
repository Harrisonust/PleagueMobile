package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.R
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Player(val firstName:String="",
             val lastName:String="",
             var profilePic: Int= R.drawable.ic_baseline_sports_basketball_24,
             var stats: MutableMap<Date, PlayerStats> = mutableMapOf<Date, PlayerStats>(),
             var team: Team = Team("", "", 0),
             var age:Int = 0,
             var number:String = "",
             var position:String = "",

             ): Parcelable {
                var fullName: String = ""
                    get() {
                        return "$firstName $lastName"
                    }
                fun getStat(date: Date, type: String): Float {
                    return this.stats[date]?.data?.get(type) ?: 0F
                }

                fun getStats(date: Date): PlayerStats{
                    return stats[date]!!
                }

                fun setStats(date: Date, stats: PlayerStats){
                    this.stats[date] = stats
                }
             }
