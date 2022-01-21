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
    val fieldGoal_2pt: Float = 0F,
    val fieldGoalAttempt_2pt: Float = 0F,
    val fieldGoalPercentage_2pt: Float = 0F,
    val fieldGoal_3pt: Float = 0F,
    val fieldGoalAttempt_3pt: Float = 0F,
    val fieldGoalPercentage_3pt: Float = 0F,
    val freeThrow: Float = 0F,
    val freeThrowAttempt: Float = 0F,
    val freeThrowAttemptPercentage: Float = 0F,
    val offensive_rebounds: Float = 0F,
    val defensive_rebounds: Float = 0F,
    val steals: Float = 0F,
    val blocks: Float = 0F,
    val turnovers: Float = 0F,
    val points_off_turnovers:Float = 0F,
    val fouls: Float = 0F,
    val timeout_remaining:Int = 0,
    val point_leader_player: Player = Player(),
    val rebound_leader_player: Player = Player(),
    val assist_leader_player: Player = Player(),
    val steal_leader_player: Player = Player(),
    val block_leader_player: Player = Player(),
) : Parcelable{
}