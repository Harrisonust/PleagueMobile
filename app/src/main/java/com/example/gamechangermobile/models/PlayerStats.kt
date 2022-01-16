package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PlayerStats(
        val points: Float = 0F,
        val rebounds: Float = 0F,
        val assists: Float = 0F,
        val fieldGoal: Float = 0F,
        val fieldGoalAttempt: Float = 0F,
        val fieldGoalPercentage: Float = 0F,
        val fieldGoal_2pt: Float = 0F,
        val fieldGoalAttempt_2pt: Float = 0F,
        val fieldGoalPercentage_2pt: Float = 0F,
        val fieldGoal_3pt: Float = 0F,
        val fieldGoalAttempt_3pt: Float = 0F,
        val fieldGoalPercentage_3pt: Float = 0F,
        val freeThrow: Float = 0F,
        val freeThrowAttempt: Float = 0F,
        val freeThrowAttemptPercentage: Float = 0F,
        val offensiveRebounds: Float = 0F,
        val defensiveRebounds: Float = 0F,
        val steals: Float = 0F,
        val blocks: Float = 0F,
        val turnovers: Float = 0F,
        val personalFouls: Float = 0F,
        val eff_fieldGoalPercentage: Float = 0F
) : Parcelable {}
