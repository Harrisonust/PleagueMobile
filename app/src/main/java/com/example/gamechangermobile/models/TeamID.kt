package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.MainActivity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamID(val ID: Int) : Parcelable {

}

val _BRAVES = (1)
val _KINGS = (6)
val _PILOTS = (2)
val _LIONEERS = (3)
val _DREAMERS = (4)
val _STEELERS = (5)

enum class TeamName(val id: Int) {
    BRAVES(_BRAVES),
    KINGS(_KINGS),
    PILOTS(_PILOTS),
    LIONEERS(_LIONEERS),
    DREAMERS(_DREAMERS),
    STEELERS(_STEELERS)
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
        TeamName.BRAVES -> TeamID(_BRAVES)
        TeamName.KINGS -> TeamID(_KINGS)
        TeamName.PILOTS -> TeamID(_PILOTS)
        TeamName.LIONEERS -> TeamID(_LIONEERS)
        TeamName.DREAMERS -> TeamID(_DREAMERS)
        TeamName.STEELERS -> TeamID(_STEELERS)
        else -> TeamID(-1)
    }

fun getTeamIdByName(name: String): TeamID =
    when (name) {
        in "台北富邦勇士" -> TeamID(_BRAVES)
        in "Taipei Fubon Braves" -> TeamID(_BRAVES)
        in "新北國王" -> TeamID(_KINGS)
        in "New Taipei Kings" -> TeamID(_KINGS)
        in "桃園領航猿" -> TeamID(_PILOTS)
        in "Taoyuan Pilots" -> TeamID(_PILOTS)
        in "新竹街口攻城獅" -> TeamID(_LIONEERS)
        in "Hsinchu Jko Lioneers" -> TeamID(_LIONEERS)
        in "福爾摩沙台新夢想家" -> TeamID(_DREAMERS)
        in "Formosa Taishin Dreamers" -> TeamID(_DREAMERS)
        in "高雄鋼鐵人" -> TeamID(_STEELERS)
        in "Kaohsiung Steelers" -> TeamID(_STEELERS)
        else -> TeamID(-1)
    }

fun getAllTeam(): Set<TeamID> =
    setOf<TeamID>(
        TeamID(_BRAVES),
        TeamID(_KINGS),
        TeamID(_PILOTS),
        TeamID(_LIONEERS),
        TeamID(_DREAMERS),
        TeamID(_STEELERS)
    )
