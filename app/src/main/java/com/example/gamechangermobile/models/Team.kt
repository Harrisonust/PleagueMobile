package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.R
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
class Team(val name: String,
           var location: String,
           var profilePic: Int,
           var totalRecord: Record = Record(0F, 0F),
           var homeRecord: Record = Record(0F, 0F),
           var awayRecord: Record = Record(0F, 0F),
           var streak: String = "",
           var last10: Record = Record(0F, 0F),
           var arena: String = "",
           var foundingDate: Date = Date(),
           var ranking: String = "",
           var games: ArrayList<Game> = ArrayList<Game>(),
           var color: Int = R.color.bg_color,
           var playerList: ArrayList<Player> = ArrayList<Player>()) : Parcelable {

    fun getGame(date: Date): Game? {
        for (game in games)
            if (game.date == date) return game
        return null
    }
}