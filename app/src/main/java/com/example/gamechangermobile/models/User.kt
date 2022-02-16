package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val favTeam: MutableList<TeamID> = mutableListOf<TeamID>(),
    val otherTeam: MutableList<TeamID> = mutableListOf<TeamID>(
        getTeamIdByName(TeamName.BRAVES),
        getTeamIdByName(TeamName.KINGS),
        getTeamIdByName(TeamName.PILOTS),
        getTeamIdByName(TeamName.STEELERS),
        getTeamIdByName(TeamName.DREAMERS),
        getTeamIdByName(TeamName.LIONEERS),
    ),
    ) : Parcelable {
}