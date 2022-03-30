package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.MainActivity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamID(val ID: Int) : Parcelable {

}

enum class TeamName {
    BRAVES,
    KINGS,
    PILOTS,
    LIONEERS,
    DREAMERS,
    STEELERS
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
        TeamName.BRAVES -> TeamID(0)
        TeamName.KINGS -> TeamID(1)
        TeamName.PILOTS -> TeamID(2)
        TeamName.LIONEERS -> TeamID(3)
        TeamName.DREAMERS -> TeamID(4)
        TeamName.STEELERS -> TeamID(5)
        else -> TeamID(-1)
    }

fun getTeamIdByName(name: String): TeamID =
    when (name) {
        "台北富邦勇士" -> TeamID(0)
        "新北國王" -> TeamID(1)
        "桃園領航猿" -> TeamID(2)
        "新竹街口攻城獅" -> TeamID(3)
        "福爾摩沙台新夢想家" -> TeamID(4)
        "高雄鋼鐵人" -> TeamID(5)
        else -> TeamID(-1)
    }

fun getAllTeam(): List<TeamID> =
    listOf<TeamID>(
        TeamID(0),
        TeamID(1),
        TeamID(2),
        TeamID(3),
        TeamID(4),
        TeamID(5)
    )
