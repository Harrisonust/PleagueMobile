package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.R
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class Team(
        var teamId: TeamID,
        var name: String = "",
        var location: String = "",
        var profilePic: Int = 0,
        var arena: String = "",
        var foundingDate: Date = Date(),
        var ranking: String = "",
        var color: Int = R.color.bg_color,
        var playerList: ArrayList<Player> = ArrayList<Player>(),
        var gamesIdList: ArrayList<GameID> = ArrayList<GameID>(),
        var record: Record = Record(),
) : Parcelable {

    fun getGame(gameId: GameID): Game? {
        for (gameId in gamesIdList) {
            return getGameById(gameId)
        }
        return null
    }

    fun getGame(date: Date): Game? {
        for (gameId in gamesIdList) {
            val game = getGameById(gameId) ?: return null
            if (game.date == date) return game
        }
        return null
    }

    fun getGameList(): ArrayList<Game> {
        val games = ArrayList<Game>()
        for (gameId in gamesIdList) {
            val game = getGameById(gameId) ?: continue
            games.add(game)
        }
        return games
    }

    private fun gamesPlayed(): Int {
        var gameplayed = 0
        for (gameId in gamesIdList) {
            val game = getGameById(gameId) ?: continue
            if (game.status != GameStatus.END) continue
            gameplayed += 1
        }
        return gameplayed
    }

    var totalRecord: Record = Record(0F, 0F)
        get() {
            var wins = 0
            for (gameId in gamesIdList) {
                val game = getGameById(gameId) ?: return Record()
                if (game.status != GameStatus.END) continue
                val winnerTeam = getTeamById(game.winner) ?: continue
                if (winnerTeam!!.teamId == this.teamId) wins += 1
            }
            val loses = gamesPlayed() - wins
            return Record(wins.toFloat(), loses.toFloat())
        }

    var homeRecord: Record = Record(0F, 0F)
        get() {
            var wins = 0
            var loses = 0

            for (gameId in gamesIdList) {
                val game = getGameById(gameId) ?: return Record()
                if (game.location == this.location) {
                    val winnerTeam = getTeamById(game.winner) ?: continue
                    if (game.status != GameStatus.END) continue
                    if (winnerTeam!!.teamId == this.teamId) wins += 1
                    else loses += 1
                }
            }
            return Record(wins.toFloat(), loses.toFloat())
        }

    var awayRecord: Record = Record(0F, 0F)
        get() {
            return Record(totalRecord.wins - homeRecord.wins, totalRecord.loses - homeRecord.loses)
        }

    var streak: String = ""
        get() {
            return this.record.getStreak()
        }

    var last10: Record = Record(0F, 0F)
        get() {
            return this.record.getLast10()
        }

//    override fun equals(other: Any?): Boolean {
//        if(other == null) return false
//        return this.teamId == other.teamId
//    }
}