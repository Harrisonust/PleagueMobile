package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
class Team(val name: String = "",
           var location: String = "",
           var profilePic: Int = 0,
           var arena: String = "",
           var foundingDate: Date = Date(),
           var ranking: String = "",
           var playerList: ArrayList<Player> = ArrayList<Player>(),
           var gamesList: ArrayList<Game> = ArrayList<Game>(),
           var record: Record = Record(),
) : Parcelable {

    fun getGame(date: Date): Game? {
        for (game in gamesList)
            if (game.date == date) return game
        return null
    }

    fun gamesPlayed(): Int {
        return gamesList.size
    }

    var totalRecord: Record = Record(0F, 0F)
        get() {
            return Record()

            var wins = 0
            for (game in gamesList) {
                if (game.winner == this) wins += 1
            }
            val loses = gamesPlayed() - wins
            return Record(wins.toFloat(), loses.toFloat())
        }

    var homeRecord: Record = Record(0F, 0F)
        get() {
            return Record()
            var wins = 0
            var loses = 0

            for (game in gamesList) {
                if (game.location == this.location) {
                    if (game.winner == this) wins += 1
                    else loses += 1
                }
            }

            return Record(wins.toFloat(), loses.toFloat())
        }

    var awayRecord: Record = Record(0F, 0F)
        get() {
            return Record()
            return Record(totalRecord.wins - homeRecord.wins, totalRecord.loses - homeRecord.loses)
        }

    var streak: String = ""
        get() {
            return ""
            return this.record.getStreak()
        }

    var last10: Record = Record(0F, 0F)
        get() {
            return Record()
            return this.record.getLast10()
        }
}