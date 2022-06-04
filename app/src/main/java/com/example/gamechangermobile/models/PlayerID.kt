package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.MainActivity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerID(
    var Name: String = "",
    var PLGID: Int = 0,
    var GCID: Int = 0
) : Parcelable {

}

fun getPlayerById(playerID: PlayerID?): Player? {
    if (playerID == null) return null
    for (player in MainActivity.playersMap.values) {
        if (player.playerID == playerID)
            return player
    }
    return null
}