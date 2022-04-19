package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.MainActivity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerID(
    val ID: Int = 0
) : Parcelable {

}

fun getPlayerById(playerID: PlayerID?): Player? {
    if (playerID == null) return null
    for (player in MainActivity.players) {
        if (player.playerID == playerID)
            return player
    }
    return null
}