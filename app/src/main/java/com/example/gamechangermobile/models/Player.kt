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

                var gamePlayed: Int = 0
                    get(){
                        return stats.size
                    }

                var averageStat: PlayerStats = PlayerStats()
                    get(){
                        var p = 0F
                        var a = 0F
                        var r = 0F
                        stats.values.forEachIndexed{ index, element ->
                            p += element.data["points"]!!
                            a += element.data["assists"]!!
                            r += element.data["rebounds"]!!
                        }

                        return PlayerStats(points = p/gamePlayed, rebounds = r/gamePlayed, assists = a/gamePlayed)
                    }

                fun getStat(date: Date, type: String): Float {
                    return this.stats[date]?.data?.get(type) ?: 0F
                }

                fun getStats(date: Date): PlayerStats{
                    return stats[date] ?: PlayerStats()
                }

                fun setStats(date: Date, stats: PlayerStats){
                    this.stats[date] = stats
                }
             }
