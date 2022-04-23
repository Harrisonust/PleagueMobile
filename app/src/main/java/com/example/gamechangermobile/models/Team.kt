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
    var playerList: ArrayList<PlayerID> = ArrayList<PlayerID>(),
    var gamesIdList: MutableList<GameID> = mutableListOf(),
    var totalRecord: Record = Record(0, 0),
    var homeRecord: Record = Record(0, 0),
    var awayRecord: Record = Record(0, 0),
    var last10: Record = Record(0, 0),
    var streak: String = "",
    var gamesBack: String = "",

) : Parcelable {

    fun getGameList(): ArrayList<Game> {
        val games = ArrayList<Game>()
        for (gameId in gamesIdList) {
            val game = getGameById(gameId) ?: continue
            games.add(game)
        }
        return games
    }

    private fun getGameResult(gameID: GameID): String {
        return if (getGameById(gameID)?.winner == this.teamId) "W"
        else "L"
    }

}