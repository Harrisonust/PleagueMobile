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
) : Parcelable {

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

    private fun getGameResult(game: Game): String {
        return if (game.winner == this.teamId) "W"
        else "L"
    }

    var streak: String = ""
        get() {
            if (gamesIdList.isEmpty()) return "0"
            var acc: Int = 1
            var first: String = ""

            for (i in gamesIdList.size - 1 downTo 0) {
                val game = getGameById(gamesIdList[i])!!
                if (game.status != GameStatus.END) continue
                first = getGameResult(game)
            }

            if (gamesIdList.size == 1) return first + "1"

            for (i in 1 until gamesIdList.size - 1) {
                val game: Game = getGameById(gamesIdList[i])!!
                val prevGame: Game = getGameById(gamesIdList[i - 1])!!
                if (game.status != GameStatus.END) continue
                if (prevGame.status != GameStatus.END) continue

                if (getGameResult(game) == getGameResult(prevGame)) acc += 1
                else break
            }
            return first + acc.toString()
        }

    var last10: Record = Record(0F, 0F)
        get() {
            var wins = 0
            var loses = 0
            var cnt = 1

            for (gameId in gamesIdList) {
                if (cnt > 10) break
                val game = getGameById(gameId)!!
                if (game.status != GameStatus.END) continue
                if (game.winner == this.teamId) wins++
                else loses++
                cnt++
            }
            return Record(wins.toFloat(), loses.toFloat())
        }
}