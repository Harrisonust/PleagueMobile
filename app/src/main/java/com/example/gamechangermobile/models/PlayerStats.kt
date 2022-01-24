package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.Constructor

@Parcelize
class PlayerStats(
        val data: MutableMap<String, Float> = mutableMapOf<String, Float>(
                "points" to 0F,
                "rebounds" to 0F,
                "assists" to 0F,
                "fieldGoal" to 0F,
                "fieldGoalAttempt" to 0F,
                "fieldGoalPercentage" to 0F,
                "fieldGoal_2pt" to 0F,
                "fieldGoalAttempt_2pt" to 0F,
                "fieldGoalPercentage_2pt" to 0F,
                "fieldGoal_3pt" to 0F,
                "fieldGoalAttempt_3pt" to 0F,
                "fieldGoalPercentage_3pt" to 0F,
                "freeThrow" to 0F,
                "freeThrowAttempt" to 0F,
                "freeThrowAttemptPercentage" to 0F,
                "offensiveRebounds" to 0F,
                "defensiveRebounds" to 0F,
                "steals" to 0F,
                "blocks" to 0F,
                "turnovers" to 0F,
                "personalFouls" to 0F,
                "eff_fieldGoalPercentage" to 0F,
        )
) : Parcelable {
        constructor(
                points :Float = 0F,
                rebounds :Float = 0F,
                assists :Float = 0F,
                fieldGoal :Float = 0F,
                fieldGoalAttempt :Float = 0F,
                fieldGoalPercentage :Float = 0F,
                fieldGoal_2pt :Float = 0F,
                fieldGoalAttempt_2pt :Float = 0F,
                fieldGoalPercentage_2pt :Float = 0F,
                fieldGoal_3pt :Float = 0F,
                fieldGoalAttempt_3pt :Float = 0F,
                fieldGoalPercentage_3pt :Float = 0F,
                freeThrow :Float = 0F,
                freeThrowAttempt :Float = 0F,
                freeThrowAttemptPercentage :Float = 0F,
                offensiveRebounds :Float = 0F,
                defensiveRebounds :Float = 0F,
                steals :Float = 0F,
                blocks :Float = 0F,
                turnovers :Float = 0F,
                personalFouls :Float = 0F,
                eff_fieldGoalPercentage :Float = 0F,
        ) : this() {
                this.data["points"] = points
                this.data["rebounds"] = rebounds
                this.data["assists"] = assists
                this.data["fieldGoal"] = fieldGoal
                this.data["fieldGoalAttempt"] = fieldGoalAttempt
                this.data["fieldGoalPercentage"] = fieldGoalPercentage
                this.data["fieldGoal_2pt"] = fieldGoal_2pt
                this.data["fieldGoalAttempt_2pt"] = fieldGoalAttempt_2pt
                this.data["fieldGoalPercentage_2pt"] = fieldGoalPercentage_2pt
                this.data["fieldGoal_3pt"] = fieldGoal_3pt
                this.data["fieldGoalAttempt_3pt"] = fieldGoalAttempt_3pt
                this.data["fieldGoalPercentage_3pt"] = fieldGoalPercentage_3pt
                this.data["freeThrow"] = freeThrow
                this.data["freeThrowAttempt"] = freeThrowAttempt
                this.data["freeThrowAttemptPercentage"] = freeThrowAttemptPercentage
                this.data["offensiveRebounds"] = offensiveRebounds
                this.data["defensiveRebounds"] = defensiveRebounds
                this.data["steals"] = steals
                this.data["blocks"] = blocks
                this.data["turnovers"] = turnovers
                this.data["personalFouls"] = personalFouls
                this.data["eff_fieldGoalPercentage"] = eff_fieldGoalPercentage
        }
}
