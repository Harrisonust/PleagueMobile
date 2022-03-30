package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.MainActivity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamID(val ID: Int) : Parcelable {

}

enum class TeamName(val id: Int) {
    BRAVES(19),
    KINGS(23),
    PILOTS(20),
    LIONEERS(22),
    DREAMERS(21),
    STEELERS(24)
}

fun getTeamById(id: TeamID?): Team? {
    if (id == null) return null
    for (team in MainActivity.teams) {
        if (team.teamId.ID == id.ID)
            return team
    }
    return null
}

fun getTeamByName(name: String): Team? {
    for (team in MainActivity.teams) {
        if (team.name == name)
            return team
    }
    return null
}

fun getTeamByName(name: TeamName): Team? =
    when (name) {
        TeamName.BRAVES -> getTeamByName("Braves")
        TeamName.KINGS -> getTeamByName("Kings")
        TeamName.PILOTS -> getTeamByName("Pilots")
        TeamName.LIONEERS -> getTeamByName("Lioneers")
        TeamName.DREAMERS -> getTeamByName("Dreamers")
        TeamName.STEELERS -> getTeamByName("Steelers")
        else -> null
    }


fun getTeamIdByName(name: TeamName): TeamID =
    when (name) {
        TeamName.BRAVES -> TeamID(19)
        TeamName.KINGS -> TeamID(23)
        TeamName.PILOTS -> TeamID(20)
        TeamName.LIONEERS -> TeamID(22)
        TeamName.DREAMERS -> TeamID(21)
        TeamName.STEELERS -> TeamID(24)
        else -> TeamID(-1)
    }

fun getTeamIdByName(name: String): TeamID =
    when (name) {
        "台北富邦勇士" -> TeamID(19)
        "新北國王" -> TeamID(23)
        "桃園領航猿" -> TeamID(20)
        "新竹街口攻城獅" -> TeamID(22)
        "福爾摩沙台新夢想家" -> TeamID(21)
        "高雄鋼鐵人" -> TeamID(24)
        else -> TeamID(-1)
    }

fun getAllTeam(): List<TeamID> =
    listOf<TeamID>(
        TeamID(19),
        TeamID(23),
        TeamID(20),
        TeamID(22),
        TeamID(21),
        TeamID(24)
    )
