package com.example.gamechangermobile.gamepage

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.R
import kotlinx.android.synthetic.main.fragment_game_page_summary.*
import com.example.gamechangermobile.TeamActivity
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.playerpage.PlayerActivity


class GamePageSummaryFragment(val game: Game) : Fragment() {
    private val viewBarMargin = 10
    val gs = game.guestStats.data
    val guestTeam = getTeamById(game.guestTeam)
    val hs = game.hostStats.data
    val hostTeam = getTeamById(game.hostTeam)

    // TODO data["points"]?.toInt().toString() -> "null" string instead of null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_page_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun startPlayerActivity(playerID: PlayerID) {
            val intent = Intent(view.context, PlayerActivity::class.java)
            intent.putExtra("SELECTED_PLAYER", playerID)
            startActivity(intent)
        }

        fun startTeamActivity(team: Team) {
            val intent = Intent(view.context, TeamActivity::class.java)
            intent.putExtra("SELECTED_TEAM", team)
            startActivity(intent)
        }

        /**
         * guest team leader begin
         */
        guest_icon.setImageResource(
            guestTeam?.profilePic ?: R.drawable.ic_user_foreground
        )

        // guest's listener
        guest_point_leader_image.setOnClickListener {
            game.guestPointLeader?.let { it1 -> startPlayerActivity(it1) }
        }
        guest_rebounds_leader_image.setOnClickListener {
            game.guestReboundLeader?.let { it1 -> startPlayerActivity(it1) }
        }
        guest_assist_leader_image.setOnClickListener {
            game.guestAssistLeader?.let { it1 -> startPlayerActivity(it1) }
        }
        guest_steal_leader_image.setOnClickListener {
            game.guestStealLeader?.let { it1 -> startPlayerActivity(it1) }
        }
        guest_block_leader_image.setOnClickListener {
            game.guestBlockLeader?.let { it1 -> startPlayerActivity(it1) }
        }
        guest_icon.setOnClickListener {
            guestTeam?.let { it1 -> startTeamActivity(it1) }
        }

        // guest point leader
        guest_point_leader_image.setImageResource(
            getPlayerById(game.guestPointLeader)?.profilePic ?: R.drawable.ic_user_foreground
        )
        getPlayerById(game.guestPointLeader)?.fullName?.let {
            guest_point_leader_name.text = it
        }
        game.guestPointLeader?.let {
            game.getPlayerStats(it)?.data?.get("points")?.let { points ->
                guest_point_leader_point.text = points.toInt().toString()
            }
        }
        guest_point_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))

        // guest rebounds leader
        guest_rebounds_leader_image.setImageResource(
            getPlayerById(game.guestReboundLeader)?.profilePic ?: R.drawable.ic_user_foreground
        )
        getPlayerById(game.guestReboundLeader)?.fullName?.let {
            guest_rebounds_leader_name.text = it
        }
        game.guestReboundLeader?.let {
            game.getPlayerStats(it)?.data?.get("rebounds")?.let { rebounds ->
                guest_rebounds_leader_point.text = rebounds.toInt().toString()
            }
        }
        guest_rebounds_leader_point.setTextColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )

        // guest assist leader
        guest_assist_leader_image.setImageResource(
            getPlayerById(game.guestAssistLeader)?.profilePic ?: R.drawable.ic_user_foreground
        )
        getPlayerById(game.guestAssistLeader)?.fullName?.let {
            guest_assist_leader_name.text = it
        }
        game.guestAssistLeader?.let {
            game.getPlayerStats(it)?.data?.get("assists")?.let { assists ->
                guest_assist_leader_point.text = assists.toInt().toString()
            }
        }
        guest_assist_leader_point.setTextColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )

        // guest steal leader
        guest_steal_leader_image.setImageResource(
            getPlayerById(game.guestStealLeader)?.profilePic ?: R.drawable.ic_user_foreground
        )
        getPlayerById(game.guestStealLeader)?.fullName?.let {
            guest_steal_leader_name.text = it
        }
        game.guestStealLeader?.let {
            game.getPlayerStats(it)?.data?.get("steals")?.let { steals ->
                guest_steal_leader_point.text = steals.toInt().toString()
            }
        }
        guest_steal_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))

        // guest block leader
        guest_block_leader_image.setImageResource(
            getPlayerById(game.guestBlockLeader)?.profilePic ?: R.drawable.ic_user_foreground
        )
        getPlayerById(game.guestBlockLeader)?.fullName?.let {
            guest_block_leader_name.text = it
        }
        game.guestBlockLeader?.let {
            game.getPlayerStats(it)?.data?.get("blocks")?.let { blocks ->
                guest_block_leader_point.text = blocks.toInt().toString()
            }
        }
        guest_block_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))


        /**
         * host team leader begin
         */
        host_icon.setImageResource(
            hostTeam?.profilePic ?: R.drawable.ic_user_foreground
        )

        host_point_leader_image.setOnClickListener {
            game.hostPointLeader?.let { it1 -> startPlayerActivity(it1) }
        }
        host_rebounds_leader_image.setOnClickListener {
            game.hostReboundLeader?.let { it1 -> startPlayerActivity(it1) }
        }
        host_assist_leader_image.setOnClickListener {
            game.hostAssistLeader?.let { it1 -> startPlayerActivity(it1) }
        }
        host_steal_leader_image.setOnClickListener {
            game.hostStealLeader?.let { it1 -> startPlayerActivity(it1) }
        }
        host_block_leader_image.setOnClickListener {
            game.hostBlockLeader?.let { it1 -> startPlayerActivity(it1) }
        }
        host_icon.setOnClickListener {
            if (hostTeam != null)
                startTeamActivity(hostTeam)
        }

        // host point leader
        host_point_leader_image.setImageResource(
            getPlayerById(game.hostPointLeader)?.profilePic ?: R.drawable.ic_user_foreground
        )
        getPlayerById(game.hostPointLeader)?.fullName?.let {
            host_point_leader_name.text = it
        }
        game.hostPointLeader?.let {
            game.getPlayerStats(it)?.data?.get("points")?.let {
                host_point_leader_point.text = it.toInt().toString()
            }
        }
        host_point_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))

        // host rebound leader
        host_rebounds_leader_image.setImageResource(
            getPlayerById(game.hostReboundLeader)?.profilePic ?: R.drawable.ic_user_foreground
        )
        getPlayerById(game.hostReboundLeader)?.fullName?.let {
            host_rebounds_leader_name.text = it
        }
        game.hostReboundLeader?.let {
            game.getPlayerStats(it)?.data?.get("rebounds")?.let {
                host_rebounds_leader_point.text = it.toInt().toString()
            }
        }
        host_rebounds_leader_point.setTextColor(
            resources.getColor(
                hostTeam?.color ?: R.color.black
            )
        )

        // host assist leader
        host_assist_leader_image.setImageResource(
            getPlayerById(game.hostAssistLeader)?.profilePic ?: R.drawable.ic_user_foreground
        )
        getPlayerById(game.hostAssistLeader)?.fullName?.let {
            host_assist_leader_name.text = it
        }
        game.hostAssistLeader?.let {
            game.getPlayerStats(it)?.data?.get("assists")?.let {
                host_assist_leader_point.text = it.toInt().toString()
            }
        }
        host_assist_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))

        // host steal leader
        host_steal_leader_image.setImageResource(
            getPlayerById(game.hostStealLeader)?.profilePic ?: R.drawable.ic_user_foreground
        )
        getPlayerById(game.hostStealLeader)?.fullName?.let {
            host_steal_leader_name.text = it
        }
        game.hostStealLeader?.let {
            game.getPlayerStats(it)?.data?.get("steals")?.let {
                host_steal_leader_point.text = it.toInt().toString()
            }
        }
        host_steal_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))

        // host block leader
        host_block_leader_image.setImageResource(
            getPlayerById(game.hostBlockLeader)?.profilePic ?: R.drawable.ic_user_foreground
        )
        getPlayerById(game.hostBlockLeader)?.fullName?.let {
            host_block_leader_name.text = it
        }
        game.hostBlockLeader?.let { it ->
            game.getPlayerStats(it)?.data?.get("blocks")?.let { it1 ->
                host_block_leader_point.text = it1.toInt().toString()
            }
        }
        host_block_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))


        /**
         * stats bar begin
         */
        // guest stats chart
        guest_field_goal.text =
            gs["fieldGoal"]?.toInt().toString() +
                    "/" + gs["fieldGoalAttempt"]?.toInt().toString() +
                    "(" + gs["fieldGoalPercentage"]?.toString() + "%)"
        guest_field_goal_bar.setBackgroundColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )

        guest_3_pointer.text =
            gs["fieldGoal3pt"]?.toInt().toString() +
                    "/" + gs["fieldGoalAttempt3pt"]?.toInt().toString() +
                    "(" + gs["fieldGoalPercentage3pt"]?.toString() + "%)"
        guest_3_pointer_bar.setBackgroundColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )

        guest_free_throw.text =
            gs["freeThrow"]?.toInt().toString() +
                    "/" + gs["freeThrowAttempt"]?.toInt().toString() +
                    "(" + gs["freeThrowAttemptPercentage"]?.toString() + "%)"
        guest_free_throw_bar.setBackgroundColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )

        guest_assist.text = gs["assists"]?.toInt().toString()
        guest_assist_bar.setBackgroundColor(resources.getColor(guestTeam?.color ?: R.color.black))

        guest_total_rebound.text = gs["rebounds"]?.toInt().toString()
        guest_total_rebound_bar.setBackgroundColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )

        guest_offensive_rebound.text = gs["offensiveRebounds"]?.toInt().toString()
        guest_offensive_rebound_bar.setBackgroundColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )

        guest_defensive_rebound.text = gs["defensiveRebounds"]?.toInt().toString()
        guest_defensive_rebound_bar.setBackgroundColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )

        guest_steal.text = gs["steals"]?.toInt().toString()
        guest_steal_bar.setBackgroundColor(resources.getColor(guestTeam?.color ?: R.color.black))

        guest_block.text = gs["blocks"]?.toInt().toString()
        guest_block_bar.setBackgroundColor(resources.getColor(guestTeam?.color ?: R.color.black))

        guest_turnover.text = gs["turnovers"]?.toInt().toString()
        guest_turnover_bar.setBackgroundColor(resources.getColor(guestTeam?.color ?: R.color.black))

        guest_points_off_turnovers.text =
            game.guestStats.data["pointsOffTurnovers"]?.toInt().toString()
        guest_points_off_turnovers_bar.setBackgroundColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )

        guest_fouls.text = gs["personalFouls"]?.toInt().toString()
        guest_fouls_bar.setBackgroundColor(resources.getColor(guestTeam?.color ?: R.color.black))

        guest_timeouts_remaining.text =
            game.guestStats.data["timeoutRemaining"]?.toInt().toString()
        guest_timeouts_remaining_bar.setBackgroundColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )

        // host stats chart
        host_field_goal.text =
            hs["fieldGoal"]?.toInt().toString() +
                    "/" + hs["fieldGoalAttempt"]?.toInt().toString() +
                    "(" + hs["fieldGoalPercentage"]?.toString() + "%)"
        host_field_goal_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))

        host_3_pointer.text =
            hs["fieldGoal3pt"]?.toInt().toString() +
                    "/" + hs["fieldGoalAttempt3pt"]?.toInt().toString() +
                    "(" + hs["fieldGoalPercentage3pt"]?.toString() + "%)"
        host_3_pointer_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))

        host_free_throw.text =
            hs["freeThrow"]?.toInt().toString() +
                    "/" + hs["freeThrowAttempt"]?.toInt().toString() +
                    "(" + hs["freeThrowAttemptPercentage"]?.toString() + "%)"
        host_free_throw_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))

        host_assist.text = hs["assists"]?.toInt().toString()
        host_assist_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))

        host_total_rebound.text = hs["rebounds"]?.toInt().toString()
        host_total_rebound_bar.setBackgroundColor(
            resources.getColor(
                hostTeam?.color ?: R.color.black
            )
        )

        host_offensive_rebound.text = hs["offensiveRebounds"]?.toInt().toString()
        host_offensive_rebound_bar.setBackgroundColor(
            resources.getColor(
                hostTeam?.color ?: R.color.black
            )
        )

        host_defensive_rebound.text = hs["defensiveRebounds"]?.toInt().toString()
        host_defensive_rebound_bar.setBackgroundColor(
            resources.getColor(
                hostTeam?.color ?: R.color.black
            )
        )

        host_steal.text = hs["steals"]?.toInt().toString()
        host_steal_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))

        host_block.text = hs["blocks"]?.toInt().toString()
        host_block_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))

        host_turnover.text = hs["turnovers"]?.toInt().toString()
        host_turnover_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))

        host_points_off_turnovers.text =
            game.hostStats.data["pointsOffTurnovers"]?.toInt().toString()
        host_points_off_turnovers_bar.setBackgroundColor(
            resources.getColor(
                hostTeam?.color ?: R.color.black
            )
        )

        host_fouls.text = hs["personalFouls"]?.toInt().toString()
        host_fouls_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))

        host_timeouts_remaining.text = game.hostStats.data["timeoutRemaining"]?.toInt().toString()
        host_timeouts_remaining_bar.setBackgroundColor(
            resources.getColor(
                hostTeam?.color ?: R.color.black
            )
        )

        // set bar width
        val displayMetrics = DisplayMetrics()
        val scale = context?.resources?.displayMetrics?.density
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        val completeBarWidth = screenWidth - (2 * viewBarMargin * scale!! + 0.5F)
        // TODO: handle nullptr situation this seems dangerous

        changeBarWidth(
            completeBarWidth,
            guest_field_goal_bar,
            host_field_goal_bar,
            gs["fieldGoalPercentage"],
            hs["fieldGoalPercentage"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_3_pointer_bar,
            host_3_pointer_bar,
            gs["fieldGoalPercentage3pt"],
            hs["fieldGoalPercentage3pt"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_free_throw_bar,
            host_free_throw_bar,
            gs["freeThrowAttemptPercentage"],
            hs["freeThrowAttemptPercentage"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_assist_bar,
            host_assist_bar,
            gs["assists"],
            hs["assists"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_total_rebound_bar,
            host_total_rebound_bar,
            gs["rebounds"],
            hs["rebounds"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_offensive_rebound_bar,
            host_offensive_rebound_bar,
            gs["offensiveRebounds"],
            hs["offensiveRebounds"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_defensive_rebound_bar,
            host_defensive_rebound_bar,
            gs["defensiveRebounds"],
            hs["defensiveRebounds"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_steal_bar,
            host_steal_bar,
            gs["steals"],
            hs["steals"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_block_bar,
            host_block_bar,
            gs["blocks"],
            hs["blocks"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_turnover_bar,
            host_turnover_bar,
            gs["turnovers"],
            hs["turnovers"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_points_off_turnovers_bar,
            host_points_off_turnovers_bar,
            gs["pointsOffTurnovers"],
            hs["pointsOffTurnovers"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_fouls_bar,
            host_fouls_bar,
            gs["personalFouls"],
            hs["personalFouls"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_timeouts_remaining_bar,
            host_timeouts_remaining_bar,
            gs["timeoutRemaining"],
            hs["timeoutRemaining"]
        )
    }

    private fun changeBarWidth(
        completeBarWidth: Float,
        guestBar: View,
        hostBar: View,
        guestValue: Float?,
        hostValue: Float?
    ) {
        if (guestValue == null || hostValue == null || (guestValue == hostValue && hostValue.toInt() == 0)) {
            // TODO: if both value is 0 then maybe do other stuff
            guestBar.layoutParams.width = (completeBarWidth / 2).toInt()
            hostBar.layoutParams.width = (completeBarWidth / 2).toInt()
        } else {
            val totalValue: Float = guestValue + hostValue
            guestBar.layoutParams.width = ((guestValue / totalValue) * completeBarWidth).toInt()
            hostBar.layoutParams.width = ((hostValue / totalValue) * completeBarWidth).toInt()
        }
        guestBar.setBackgroundColor(
            resources.getColor(
                guestTeam?.color ?: R.color.black
            )
        )
        hostBar.setBackgroundColor(
            resources.getColor(
                hostTeam?.color ?: R.color.black
            )
        )
    }
}