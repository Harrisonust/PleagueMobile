package com.example.gamechangermobile.models

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize

@Parcelize
class Player(val FirstName:String, val LastName:String, var ProfilePic: Int,
             var _pts:Float = 0F,
             var _ast:Float = 0F,
             var _reb:Float = 0F,
             var team: Team = Team("", "", 0),
             var age:Int = 0,
             var number:String = "",
             var position:String = ""
             ): Parcelable {

    fun setStats(pts:Float = 0F, reb:Float = 0F, ast:Float = 0F){
        _pts = pts
        _ast = ast
        _reb = reb
        Log.d("Debug", _pts.toString())
    }
}