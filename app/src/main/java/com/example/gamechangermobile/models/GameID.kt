package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.MainActivity.Companion.games
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameID(val ID: String) : Parcelable {

}

fun getGameById(id: GameID): Game? {
    for (game in games) {
        if (game.gameId == id)
            return game
    }
    return null
}