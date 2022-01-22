package com.example.gamechangermobile.gamepage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Game
import kotlinx.android.synthetic.main.fragment_game_page_summary.*
import kotlinx.android.synthetic.main.fragment_team_page_info.*
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
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

        fun startPlayerActivity(player: Player){
            val intent = Intent(view.context, PlayerActivity::class.java)
            intent.putExtra("SELECTED_PLAYER",player)
            startActivity(intent)
        }
        fun startTeamActivity(team: Team){
            val intent = Intent(view.context, TeamActivity::class.java)
            intent.putExtra("SELECTED_TEAM",team)
            startActivity(intent)
        }

        val gs = game.GuestStats

        guest_point_leader_image.setImageResource(gs.point_leader_player.ProfilePic)
        guest_point_leader_name.text = gs.point_leader_player.FullName
        guest_rebounds_leader_image.setImageResource(gs.rebound_leader_player.ProfilePic)
        guest_rebounds_leader_name.text = gs.rebound_leader_player.FullName
        guest_assist_leader_image.setImageResource(gs.assist_leader_player.ProfilePic)
        guest_assist_leader_name.text = gs.assist_leader_player.FullName
        guest_steal_leader_image.setImageResource(gs.steal_leader_player.ProfilePic)
        guest_steal_leader_name.text = gs.steal_leader_player.FullName
        guest_block_leader_image.setImageResource(gs.block_leader_player.ProfilePic)
        guest_block_leader_name.text = gs.block_leader_player.FullName

        guest_point_leader_image.setOnClickListener {
            startPlayerActivity(gs.point_leader_player)
        }
        guest_rebounds_leader_image.setOnClickListener {
            startPlayerActivity(gs.rebound_leader_player)
        }
        guest_assist_leader_image.setOnClickListener {
            startPlayerActivity(gs.assist_leader_player)
        }
        guest_steal_leader_image.setOnClickListener {
            startPlayerActivity(gs.steal_leader_player)
        }
        guest_block_leader_image.setOnClickListener {
            startPlayerActivity(gs.block_leader_player)
        }
        guest_icon.setOnClickListener {
            startTeamActivity(game.GuestTeam)
        }

        guest_field_goal.text = gs.fieldGoal.toInt().toString() + "/" + gs.fieldGoalAttempt.toInt().toString() + "(" + gs.fieldGoalPercentage.toString()+ "%)"
//        guest_field_goal_bar.layoutParams.width = 50
        guest_3_pointer.text = gs.fieldGoal_3pt.toInt().toString() + "/" + gs.fieldGoalAttempt_3pt.toInt().toString() + "(" + gs.fieldGoalPercentage_3pt.toString()+ "%)"
//        guest_3_pointer_bar.layoutParams.width = 10
        guest_free_throw.text = gs.freeThrow.toInt().toString() + "/" + gs.freeThrowAttempt.toInt().toString() + "(" + gs.freeThrowAttemptPercentage.toString()+ "%)"
//        guest_fouls_bar.layoutParams.width =
        guest_assist.text = gs.assists.toInt().toString()
//        guest_assist_bar.layoutParams.width =
        guest_total_rebound.text = gs.rebounds.toInt().toString()
//        guest_total_rebound_bar.layoutParams.width = 10
        guest_offensive_rebound.text = gs.offensive_rebounds.toInt().toString()
//        guest_offensive_rebound_bar.layoutParams.width
        guest_defensive_rebound.text = gs.defensive_rebounds.toInt().toString()
//        guest_defensive_rebound_bar.layoutParams.width =
        guest_steal.text = gs.steals.toInt().toString()
//        guest_steal_bar.layoutParams.width =
        guest_block.text = gs.blocks.toInt().toString()
//        guest_steal_bar.layoutParams.width =
        guest_turnover.text = gs.turnovers.toInt().toString()
//        guest_turnover_bar.layoutParams.width =
        guest_points_off_turnovers.text = gs.points_off_turnovers.toInt().toString()
//        guest_points_off_turnovers_bar.layoutParams.width =
        guest_fouls.text = gs.fouls.toInt().toString()
//        guest_fouls_bar.layoutParams.width =
        guest_timeouts_remaining.text = gs.timeout_remaining.toString()


        val hs = game.HostStats

        host_point_leader_image.setImageResource(hs.point_leader_player.ProfilePic)
        host_point_leader_name.text = hs.point_leader_player.FullName
        host_rebounds_leader_image.setImageResource(hs.rebound_leader_player.ProfilePic)
        host_rebounds_leader_name.text = hs.rebound_leader_player.FullName
        host_assist_leader_image.setImageResource(hs.assist_leader_player.ProfilePic)
        host_assist_leader_name.text = hs.assist_leader_player.FullName
        host_steal_leader_image.setImageResource(hs.steal_leader_player.ProfilePic)
        host_steal_leader_name.text = hs.steal_leader_player.FullName
        host_block_leader_image.setImageResource(hs.block_leader_player.ProfilePic)
        host_block_leader_name.text = hs.block_leader_player.FullName

        host_point_leader_image.setOnClickListener {
            startPlayerActivity(hs.point_leader_player)
        }
        host_rebounds_leader_image.setOnClickListener {
            startPlayerActivity(hs.rebound_leader_player)
        }
        host_assist_leader_image.setOnClickListener {
            startPlayerActivity(hs.assist_leader_player)
        }
        host_steal_leader_image.setOnClickListener {
            startPlayerActivity(hs.steal_leader_player)
        }
        host_block_leader_image.setOnClickListener {
            startPlayerActivity(hs.block_leader_player)
        }
        host_icon.setOnClickListener {
            startTeamActivity(game.HostTeam)
        }

        host_field_goal.text = hs.fieldGoal.toInt().toString() + "/" + hs.fieldGoalAttempt.toInt().toString() + "(" + hs.fieldGoalPercentage.toString()+ "%)"
//        host_field_goal_bar.layoutParams.width = 50
        host_3_pointer.text = hs.fieldGoal_3pt.toInt().toString() + "/" + hs.fieldGoalAttempt_3pt.toInt().toString() + "(" + hs.fieldGoalPercentage_3pt.toString()+ "%)"
//        host_3_pointer_bar.layoutParams.width = 10
        host_free_throw.text = hs.freeThrow.toInt().toString() + "/" + hs.freeThrowAttempt.toInt().toString() + "(" + hs.freeThrowAttemptPercentage.toString()+ "%)"
//        host_fouls_bar.layoutParams.width =
        host_assist.text = hs.assists.toInt().toString()
//        host_assist_bar.layoutParams.width =
        host_total_rebound.text = hs.rebounds.toInt().toString()
//        host_total_rebound_bar.layoutParams.width = 10
        host_offensive_rebound.text = hs.offensive_rebounds.toInt().toString()
//        host_offensive_rebound_bar.layoutParams.width
        host_defensive_rebound.text = hs.defensive_rebounds.toInt().toString()
//        host_defensive_rebound_bar.layoutParams.width =
        host_steal.text = hs.steals.toInt().toString()
//        host_steal_bar.layoutParams.width =
        host_block.text = hs.blocks.toInt().toString()
//        host_steal_bar.layoutParams.width =
        host_turnover.text = hs.turnovers.toInt().toString()
//        host_turnover_bar.layoutParams.width =
        host_points_off_turnovers.text = hs.points_off_turnovers.toInt().toString()
//        host_points_off_turnovers_bar.layoutParams.width =
        host_fouls.text = hs.fouls.toInt().toString()
//        host_fouls_bar.layoutParams.width =
        host_timeouts_remaining.text = hs.timeout_remaining.toString()
    }
}