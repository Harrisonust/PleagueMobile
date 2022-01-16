package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PlayerStats(
    val points: Float,
    val rebounds: Float,
    val assists: Float,
    val fieldGoal: Float,
    val fieldGoalAttempt: Float,
    val fieldGoalPercentage: Float,
    val fieldGoal_2pt: Float,
    val fieldGoalAttempt_2pt: Float,
    val fieldGoalPercentage_2pt: Float,
    val fieldGoal_3pt: Float,
    val fieldGoalAttempt_3pt: Float,
    val fieldGoalPercentage_3pt: Float,
    val freeThrow: Float,
    val freeThrowAttempt: Float,
    val freeThrowAttemptPercentage: Float,
    val offensiveRebounds: Float,
    val defensiveRebounds: Float,
    val steals: Float,
    val blocks: Float,
    val turnovers: Float,
    val personalFouls: Float,
    val eff_fieldGoalPercentage: Float
    ): Parcelable {
}