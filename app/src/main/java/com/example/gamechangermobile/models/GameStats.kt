package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GameStats(
    val points: Float = 0F,
    val rebounds: Float = 0F,
    val assists: Float = 0F,
    val fieldGoal: Float = 0F,
    val fieldGoalAttempt: Float = 0F,
    val fieldGoalPercentage: Float = 0F,
    val fieldGoal2pt: Float = 0F,
    val fieldGoalAttempt2pt: Float = 0F,
    val fieldGoalPercentage2pt: Float = 0F,
    val fieldGoal3pt: Float = 0F,
    val fieldGoalAttempt3pt: Float = 0F,
    val fieldGoalPercentage3pt: Float = 0F,
    val freeThrow: Float = 0F,
    val freeThrowAttempt: Float = 0F,
    val freeThrowAttemptPercentage: Float = 0F,
    val offensiveRebounds: Float = 0F,
    val defensiveRebounds: Float = 0F,
    val steals: Float = 0F,
    val blocks: Float = 0F,
    val turnovers: Float = 0F,
    val pointsOffTurnovers:Float = 0F,
    val fouls: Float = 0F,
    val timeoutRemaining:Int = 0,
) : Parcelable{
}