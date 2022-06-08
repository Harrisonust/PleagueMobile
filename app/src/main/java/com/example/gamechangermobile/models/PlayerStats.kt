package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class PlayerStats(
    val data: MutableMap<String, Float> = mutableMapOf<String, Float>(
        "points" to 0F,
        "rebounds" to 0F,
        "assists" to 0F,

        "fieldGoalMade" to 0F,
        "fieldGoalAttempt" to 0F,
        "fieldGoalPercentage" to 0F,

        "twoPointMade" to 0F,
        "twoPointAttempt" to 0F,
        "twoPointPercentage" to 0F,

        "threePointMade" to 0F,
        "threePointAttempt" to 0F,
        "threePointPercentage" to 0F,

        "freeThrowMade" to 0F,
        "freeThrowAttempt" to 0F,
        "freeThrowPercentage" to 0F,

        "offensiveRebounds" to 0F,
        "defensiveRebounds" to 0F,
        "steals" to 0F,
        "blocks" to 0F,
        "turnovers" to 0F,
        "personalFouls" to 0F,
        "effFieldGoalPercentage" to 0F,
    )
) : Parcelable {
    constructor(
        points: Float = 0F,
        rebounds: Float = 0F,
        assists: Float = 0F,

        fieldGoalMade: Float = 0F,
        fieldGoalAttempt: Float = 0F,
        fieldGoalPercentage: Float = 0F,

        twoPointMade: Float = 0F,
        twoPointAttempt: Float = 0F,
        twoPointPercentage: Float = 0F,

        threePointMade: Float = 0F,
        threePointAttempt: Float = 0F,
        threePointPercentage: Float = 0F,

        freeThrowMade: Float = 0F,
        freeThrowAttempt: Float = 0F,
        freeThrowPercentage: Float = 0F,

        offensiveRebounds: Float = 0F,
        defensiveRebounds: Float = 0F,
        steals: Float = 0F,
        blocks: Float = 0F,
        turnovers: Float = 0F,
        personalFouls: Float = 0F,
        effFieldGoalPercentage: Float = 0F,
    ) : this() {
        this.data["points"] = points
        this.data["rebounds"] = rebounds
        this.data["assists"] = assists

        this.data["fieldGoalMade"] = fieldGoalMade
        this.data["fieldGoalAttempt"] = fieldGoalAttempt
        this.data["fieldGoalPercentage"] = fieldGoalPercentage

        this.data["twoPointMade"] = twoPointMade
        this.data["twoPointAttempt"] = twoPointAttempt
        this.data["twoPointPercentage"] = twoPointPercentage

        this.data["threePointMade"] = threePointMade
        this.data["threePointAttempt"] = threePointAttempt
        this.data["threePointPercentage"] = threePointPercentage

        this.data["freeThrowMade"] = freeThrowMade
        this.data["freeThrowAttempt"] = freeThrowAttempt
        this.data["freeThrowPercentage"] = freeThrowPercentage

        this.data["offensiveRebounds"] = offensiveRebounds
        this.data["defensiveRebounds"] = defensiveRebounds
        this.data["steals"] = steals
        this.data["blocks"] = blocks
        this.data["turnovers"] = turnovers
        this.data["personalFouls"] = personalFouls
        this.data["effFieldGoalPercentage"] = effFieldGoalPercentage
    }
}
