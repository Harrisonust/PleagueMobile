package com.example.gamechangermobile.gamepage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Game
import kotlinx.android.synthetic.main.fragment_game_page_summary.*
import com.example.gamechangermobile.TeamActivity
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.Team
import com.example.gamechangermobile.playerpage.PlayerActivity


class GamePageSummaryFragment(val game: Game) : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_page_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun startPlayerActivity(player: Player) {
            val intent = Intent(view.context, PlayerActivity::class.java)
            intent.putExtra("SELECTED_PLAYER", player)
            startActivity(intent)
        }

        fun startTeamActivity(team: Team) {
            val intent = Intent(view.context, TeamActivity::class.java)
            intent.putExtra("SELECTED_TEAM", team)
            startActivity(intent)
        }

        val gs = game.guestStats.data

        guest_point_leader_image.setImageResource(game.guestPointLeader.profilePic)
        guest_point_leader_name.text = game.guestPointLeader.fullName
        guest_point_leader_point.text = game.getPlayerStats(game.guestPointLeader)!!.data["points"]!!.toInt().toString()

        guest_rebounds_leader_image.setImageResource(game.guestReboundLeader.profilePic)
        guest_rebounds_leader_name.text = game.guestReboundLeader.fullName
        guest_rebounds_leader_point.text = game.getPlayerStats(game.guestReboundLeader)!!.data["rebounds"]!!.toInt().toString()

        guest_assist_leader_image.setImageResource(game.guestAssistLeader.profilePic)
        guest_assist_leader_name.text = game.guestAssistLeader.fullName
        guest_assist_leader_point.text = game.getPlayerStats(game.guestAssistLeader)!!.data["assists"]!!.toInt().toString()

        guest_steal_leader_image.setImageResource(game.guestStealLeader.profilePic)
        guest_steal_leader_name.text = game.guestStealLeader.fullName
        guest_steal_leader_point.text = game.getPlayerStats(game.guestStealLeader)!!.data["steals"]!!.toInt().toString()

        guest_block_leader_image.setImageResource(game.guestBlockLeader.profilePic)
        guest_block_leader_name.text = game.guestBlockLeader.fullName
        guest_block_leader_point.text = game.getPlayerStats(game.guestBlockLeader)!!.data["blocks"]!!.toInt().toString()

        guest_point_leader_image.setOnClickListener {
            startPlayerActivity(game.guestPointLeader)
        }
        guest_rebounds_leader_image.setOnClickListener {
            startPlayerActivity(game.guestReboundLeader)
        }
        guest_assist_leader_image.setOnClickListener {
            startPlayerActivity(game.guestAssistLeader)
        }
        guest_steal_leader_image.setOnClickListener {
            startPlayerActivity(game.guestStealLeader)
        }
        guest_block_leader_image.setOnClickListener {
            startPlayerActivity(game.guestBlockLeader)
        }
        guest_icon.setOnClickListener {
            startTeamActivity(game.guestTeam)
        }

        guest_field_goal.text = gs["fieldGoal"]!!.toInt().toString() + "/" + gs["fieldGoalAttempt"]!!.toInt().toString() + "(" + gs["fieldGoalPercentage"]!!.toString() + "%)"
//        guest_field_goal_bar.layoutParams.width = 50
        guest_3_pointer.text = gs["fieldGoal3pt"]!!.toInt().toString() + "/" + gs["fieldGoalAttempt3pt"]!!.toInt().toString() + "(" + gs["fieldGoalPercentage3pt"]!!.toString() + "%)"
//        guest_3_pointer_bar.layoutParams.width = 10
        guest_free_throw.text = gs["freeThrow"]!!.toInt().toString() + "/" + gs["freeThrowAttempt"]!!.toInt().toString() + "(" + gs["freeThrowAttemptPercentage"]!!.toString() + "%)"
//        guest_fouls_bar.layoutParams.width =
        guest_assist.text = gs["assists"]!!.toInt().toString()
//        guest_assist_bar.layoutParams.width =
        guest_total_rebound.text = gs["rebounds"]!!.toInt().toString()
//        guest_total_rebound_bar.layoutParams.width = 10
        guest_offensive_rebound.text = gs["offensiveRebounds"]!!.toInt().toString()
//        guest_offensive_rebound_bar.layoutParams.width
        guest_defensive_rebound.text = gs["defensiveRebounds"]!!.toInt().toString()
//        guest_defensive_rebound_bar.layoutParams.width =
        guest_steal.text = gs["steals"]!!.toInt().toString()
//        guest_steal_bar.layoutParams.width =
        guest_block.text = gs["blocks"]!!.toInt().toString()
//        guest_steal_bar.layoutParams.width =
        guest_turnover.text = gs["turnovers"]!!.toInt().toString()
//        guest_turnover_bar.layoutParams.width =
        guest_points_off_turnovers.text = game.guestStats.data["pointsOffTurnovers"]!!.toInt().toString()
//        guest_points_off_turnovers_bar.layoutParams.width =
        guest_fouls.text = gs["personalFouls"]!!.toInt().toString()
//        guest_fouls_bar.layoutParams.width =
        guest_timeouts_remaining.text = game.guestStats.data["timeoutRemaining"]!!.toInt().toString()


        val hs = game.hostStats.data

        host_point_leader_image.setImageResource(game.hostPointLeader.profilePic)
        host_point_leader_name.text = game.hostPointLeader.fullName
        host_point_leader_point.text = game.getPlayerStats(game.hostPointLeader)!!.data["points"]!!.toInt().toString()

        host_rebounds_leader_image.setImageResource(game.hostReboundLeader.profilePic)
        host_rebounds_leader_name.text = game.hostReboundLeader.fullName
        host_rebounds_leader_point.text = game.getPlayerStats(game.hostReboundLeader)!!.data["rebounds"]!!.toInt().toString()

        host_assist_leader_image.setImageResource(game.hostAssistLeader.profilePic)
        host_assist_leader_name.text = game.hostAssistLeader.fullName
        host_assist_leader_point.text = game.getPlayerStats(game.hostAssistLeader)!!.data["assists"]!!.toInt().toString()

        host_steal_leader_image.setImageResource(game.hostStealLeader.profilePic)
        host_steal_leader_name.text = game.hostStealLeader.fullName
        host_steal_leader_point.text = game.getPlayerStats(game.hostStealLeader)!!.data["steals"]!!.toInt().toString()

        host_block_leader_image.setImageResource(game.hostBlockLeader.profilePic)
        host_block_leader_name.text = game.hostBlockLeader.fullName
        host_block_leader_point.text = game.getPlayerStats(game.hostBlockLeader)!!.data["blocks"]!!.toInt().toString()

        host_point_leader_image.setOnClickListener {
            startPlayerActivity(game.hostPointLeader)
        }
        host_rebounds_leader_image.setOnClickListener {
            startPlayerActivity(game.hostReboundLeader)
        }
        host_assist_leader_image.setOnClickListener {
            startPlayerActivity(game.hostAssistLeader)
        }
        host_steal_leader_image.setOnClickListener {
            startPlayerActivity(game.hostStealLeader)
        }
        host_block_leader_image.setOnClickListener {
            startPlayerActivity(game.hostBlockLeader)
        }
        host_icon.setOnClickListener {
            startTeamActivity(game.hostTeam)
        }

        host_field_goal.text = hs["fieldGoal"]!!.toInt().toString() + "/" + hs["fieldGoalAttempt"]!!.toInt().toString() + "(" + hs["fieldGoalPercentage"]!!.toString() + "%)"
//        host_field_goal_bar.layoutParams.width = 50
        host_3_pointer.text = hs["fieldGoal3pt"]!!.toInt().toString() + "/" + hs["fieldGoalAttempt3pt"]!!.toInt().toString() + "(" + hs["fieldGoalPercentage3pt"]!!.toString() + "%)"
//        host_3_pointer_bar.layoutParams.width = 10
        host_free_throw.text = hs["freeThrow"]!!.toInt().toString() + "/" + hs["freeThrowAttempt"]!!.toInt().toString() + "(" + hs["freeThrowAttemptPercentage"]!!.toString() + "%)"
//        host_fouls_bar.layoutParams.width =
        host_assist.text = hs["assists"]!!.toInt().toString()
//        host_assist_bar.layoutParams.width =
        host_total_rebound.text = hs["rebounds"]!!.toInt().toString()
//        host_total_rebound_bar.layoutParams.width = 10
        host_offensive_rebound.text = hs["offensiveRebounds"]!!.toInt().toString()
//        host_offensive_rebound_bar.layoutParams.width
        host_defensive_rebound.text = hs["defensiveRebounds"]!!.toInt().toString()
//        host_defensive_rebound_bar.layoutParams.width =
        host_steal.text = hs["steals"]!!.toInt().toString()
//        host_steal_bar.layoutParams.width =
        host_block.text = hs["blocks"]!!.toInt().toString()
//        host_steal_bar.layoutParams.width =
        host_turnover.text = hs["turnovers"]!!.toInt().toString()
//        host_turnover_bar.layoutParams.width =
        host_points_off_turnovers.text = game.hostStats.data["pointsOffTurnovers"]!!.toInt().toString()
//        host_points_off_turnovers_bar.layoutParams.width =
        host_fouls.text = hs["personalFouls"]!!.toInt().toString()
//        host_fouls_bar.layoutParams.width =
        host_timeouts_remaining.text = game.hostStats.data["timeoutRemaining"]!!.toInt().toString()
    }
}