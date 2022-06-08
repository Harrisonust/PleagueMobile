package com.example.gamechangermobile.gamepage

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.gamechangermobile.MainActivity.Companion.teamsMap
import com.example.gamechangermobile.R
import kotlinx.android.synthetic.main.fragment_game_page_summary.*
import com.example.gamechangermobile.TeamActivity
import com.example.gamechangermobile.database.Dictionary
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.playerpage.PlayerActivity
import java.util.*
import kotlin.math.roundToInt


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

        fun startPlayerActivity(player: Player) {
            val intent = Intent(view.context, PlayerActivity::class.java)
            intent.putExtra("SELECTED_PLAYER", player.playerID)
            startActivity(intent)
        }

        fun startTeamActivity(team: Team) {
            val intent = Intent(view.context, TeamActivity::class.java)
            intent.putExtra("SELECTED_TEAM", team.teamId)
            startActivity(intent)
        }

        guest_name.text = teamsMap[game.guestTeam]?.name
        host_name.text = teamsMap[game.hostTeam]?.name

        val model: GameViewModel by activityViewModels { GameViewModelFactory(game.gameId.ID.toInt()) } // TODO change gameID with plgID
        model.getGame().observe(viewLifecycleOwner, {
            guest_game_q1.text = it.guestScorePerQuarter[0]
            guest_game_q2.text = it.guestScorePerQuarter[1]
            guest_game_q3.text = it.guestScorePerQuarter[2]
            guest_game_q4.text = it.guestScorePerQuarter[3]
            guest_game_tot.text = it.guestScorePerQuarter.map { e -> e.toInt() }.toTypedArray().sum().toString()

            host_game_q1.text = it.hostScorePerQuarter[0]
            host_game_q2.text = it.hostScorePerQuarter[1]
            host_game_q3.text = it.hostScorePerQuarter[2]
            host_game_q4.text = it.hostScorePerQuarter[3]
            host_game_tot.text = it.hostScorePerQuarter.map { e -> e.toInt() }.toTypedArray().sum().toString()

        })

        model.getHostLeaders().observe(viewLifecycleOwner, {  leaders ->
            host_point_leader_image.setOnClickListener {
                startPlayerActivity(leaders["points"]!!)
            }
            host_rebounds_leader_image.setOnClickListener {
                startPlayerActivity(leaders["rebounds"]!!)
            }
            host_assist_leader_image.setOnClickListener {
                startPlayerActivity(leaders["assists"]!!)
            }
            host_steal_leader_image.setOnClickListener {
                startPlayerActivity(leaders["steals"]!!)
            }
            host_block_leader_image.setOnClickListener {
                startPlayerActivity(leaders["blocks"]!!)
            }

            // host point leader
            host_point_leader_image.setImageResource(leaders["points"]!!.profilePic)
            host_point_leader_name.text = leaders["points"]!!.fullName

            // host rebound leader
            host_rebounds_leader_image.setImageResource(leaders["rebounds"]!!.profilePic)
            host_rebounds_leader_name.text = leaders["rebounds"]!!.fullName

            // host assist leader
            host_assist_leader_image.setImageResource(leaders["assists"]!!.profilePic)
            host_assist_leader_name.text = leaders["assists"]!!.fullName

            // host steal leader
            host_steal_leader_image.setImageResource(leaders["steals"]!!.profilePic)
            host_steal_leader_name.text = leaders["steals"]!!.fullName


            // host block leader
            host_block_leader_image.setImageResource(leaders["blocks"]!!.profilePic)
            host_block_leader_name.text = leaders["blocks"]!!.fullName
        })

        model.getGuestLeaders().observe(viewLifecycleOwner, {  leaders ->
            guest_point_leader_image.setOnClickListener {
                startPlayerActivity(leaders["points"]!!)
            }
            guest_rebounds_leader_image.setOnClickListener {
                startPlayerActivity(leaders["rebounds"]!!)
            }
            guest_assist_leader_image.setOnClickListener {
                startPlayerActivity(leaders["assists"]!!)
            }
            guest_steal_leader_image.setOnClickListener {
                startPlayerActivity(leaders["steals"]!!)
            }
            guest_block_leader_image.setOnClickListener {
                startPlayerActivity(leaders["blocks"]!!)
            }

            // guest point leader
            guest_point_leader_image.setImageResource(leaders["points"]!!.profilePic)
            guest_point_leader_name.text = leaders["points"]!!.fullName

            // guest rebound leader
            guest_rebounds_leader_image.setImageResource(leaders["rebounds"]!!.profilePic)
            guest_rebounds_leader_name.text = leaders["rebounds"]!!.fullName

            // guest assist leader
            guest_assist_leader_image.setImageResource(leaders["assists"]!!.profilePic)
            guest_assist_leader_name.text = leaders["assists"]!!.fullName

            // guest steal leader
            guest_steal_leader_image.setImageResource(leaders["steals"]!!.profilePic)
            guest_steal_leader_name.text = leaders["steals"]!!.fullName


            // guest block leader
            guest_block_leader_image.setImageResource(leaders["blocks"]!!.profilePic)
            guest_block_leader_name.text = leaders["blocks"]!!.fullName
        })

        model.getHostLeadersPoints().observe(viewLifecycleOwner, {
            host_point_leader_point.text = it["points"]?.toInt().toString()
            host_point_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))

            host_rebounds_leader_point.text = it["rebounds"]?.toInt().toString()
            host_rebounds_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))

            host_assist_leader_point.text = it["assists"]?.toInt().toString()
            host_assist_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))

            host_steal_leader_point.text = it["steals"]?.toInt().toString()
            host_steal_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))

            host_block_leader_point.text = it["blocks"]?.toInt().toString()
            host_block_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))
        })

        model.getGuestLeadersPoints().observe(viewLifecycleOwner, {
            guest_point_leader_point.text = it["points"]?.toInt().toString()
            guest_point_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))

            guest_rebounds_leader_point.text = it["rebounds"]?.toInt().toString()
            guest_rebounds_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))

            guest_assist_leader_point.text = it["assists"]?.toInt().toString()
            guest_assist_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))

            guest_steal_leader_point.text = it["steals"]?.toInt().toString()
            guest_steal_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))

            guest_block_leader_point.text = it["blocks"]?.toInt().toString()
            guest_block_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))
        })

        var hts = mutableMapOf<String, Float>()
        model.getHostTotalStats().observe(viewLifecycleOwner, {
            val hs = it.data
            hts = hs
            // host stats chart
            val fieldGoalPercentage = if (hs["fieldGoalAttempt"] != 0F) hs["fieldGoalMade"]!!/ hs["fieldGoalAttempt"]!! else 0F
            host_field_goal.text =
                hs["fieldGoalMade"]?.toInt().toString() +
                        "/" + hs["fieldGoalAttempt"]?.toInt().toString() +
                        "(" + ((fieldGoalPercentage*10000).roundToInt()/100F)?.toString() + "%)"
            host_field_goal_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))

            val threePointPercentage = if (hs["threePointAttempt"] != 0F) hs["threePointMade"]!!/ hs["threePointAttempt"]!! else 0F
            host_3_pointer.text =
                hs["threePointMade"]?.toInt().toString() +
                        "/" + hs["threePointAttempt"]?.toInt().toString() +
                        "(" + ((threePointPercentage*10000).roundToInt()/100F)?.toString() + "%)"
            host_3_pointer_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))

            val freeThrowPercentage = if (hs["freeThrowAttempt"] != 0F) hs["freeThrowMade"]!!/ hs["freeThrowAttempt"]!! else 0F
            host_free_throw.text =
                hs["freeThrowMade"]?.toInt().toString() +
                        "/" + hs["freeThrowAttempt"]?.toInt().toString() +
                        "(" + ((freeThrowPercentage*10000).roundToInt()/100F)?.toString() + "%)"
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
        })
        model.getGuestTotalStats().observe(viewLifecycleOwner, {
            // guest stats chart
            val gs = it.data

            val fieldGoalPercentage = if (gs["fieldGoalAttempt"] != 0F) gs["fieldGoalMade"]!!/ gs["fieldGoalAttempt"]!! else 0F
            guest_field_goal.text =
                gs["fieldGoalMade"]?.toInt().toString() +
                        "/" + gs["fieldGoalAttempt"]?.toInt().toString() +
                        "(" + ((fieldGoalPercentage*10000).roundToInt()/100F)?.toString() + "%)"
            guest_field_goal_bar.setBackgroundColor(
                resources.getColor(
                    guestTeam?.color ?: R.color.black
                )
            )

            val threePointPercentage = if (gs["threePointAttempt"] != 0F) gs["threePointMade"]!!/ gs["threePointAttempt"]!! else 0F
            guest_3_pointer.text =
                gs["threePointMade"]?.toInt().toString() +
                        "/" + gs["threePointAttempt"]?.toInt().toString() +
                        "(" + ((threePointPercentage*10000).roundToInt()/100F)?.toString() + "%)"
            guest_3_pointer_bar.setBackgroundColor(
                resources.getColor(
                    guestTeam?.color ?: R.color.black
                )
            )

            val freeThrowPercentage = if (gs["freeThrowAttempt"] != 0F) gs["freeThrowMade"]!!/ gs["freeThrowAttempt"]!! else 0F
            guest_free_throw.text =
                gs["freeThrowMade"]?.toInt().toString() +
                        "/" + gs["freeThrowAttempt"]?.toInt().toString() +
                        "(" + ((freeThrowPercentage*10000).roundToInt()/100F)?.toString() + "%)"
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
        })


//        guest_game_q1.text = game.guestScorePerQuarter[0]
//        guest_game_q2.text = game.guestScorePerQuarter[1]
//        guest_game_q3.text = game.guestScorePerQuarter[2]
//        guest_game_q4.text = game.guestScorePerQuarter[3]
//        guest_game_tot.text = game.guestScore.toString()

//        host_game_q1.text = game.hostScorePerQuarter[0]
//        host_game_q2.text = game.hostScorePerQuarter[1]
//        host_game_q3.text = game.hostScorePerQuarter[2]
//        host_game_q4.text = game.hostScorePerQuarter[3]
//        host_game_tot.text = game.hostScore.toString()

        /**
         * guest team leader begin
         */
        guest_icon.setImageResource(
            guestTeam?.profilePic ?: R.drawable.ic_user_foreground
        )

//        // guest's listener
//        guest_point_leader_image.setOnClickListener {
//            game.guestPointLeader?.let { it1 -> startPlayerActivity(it1) }
//        }
//        guest_rebounds_leader_image.setOnClickListener {
//            game.guestReboundLeader?.let { it1 -> startPlayerActivity(it1) }
//        }
//        guest_assist_leader_image.setOnClickListener {
//            game.guestAssistLeader?.let { it1 -> startPlayerActivity(it1) }
//        }
//        guest_steal_leader_image.setOnClickListener {
//            game.guestStealLeader?.let { it1 -> startPlayerActivity(it1) }
//        }
//        guest_block_leader_image.setOnClickListener {
//            game.guestBlockLeader?.let { it1 -> startPlayerActivity(it1) }
//        }
        guest_icon.setOnClickListener {
            guestTeam?.let { it1 -> startTeamActivity(it1) }
        }

//        // guest point leader
//        guest_point_leader_image.setImageResource(
//            getPlayerById(game.guestPointLeader)?.profilePic ?: R.drawable.ic_user_foreground
//        )
//        getPlayerById(game.guestPointLeader)?.fullName?.let {
//            guest_point_leader_name.text = it
//        }
//        game.guestPointLeader?.let {
//            game.getPlayerStats(it)?.data?.get("points")?.let { points ->
//                guest_point_leader_point.text = points.toInt().toString()
//            }
//        }
//        guest_point_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))
//
//        // guest rebounds leader
//        guest_rebounds_leader_image.setImageResource(
//            getPlayerById(game.guestReboundLeader)?.profilePic ?: R.drawable.ic_user_foreground
//        )
//        getPlayerById(game.guestReboundLeader)?.fullName?.let {
//            guest_rebounds_leader_name.text = it
//        }
//        game.guestReboundLeader?.let {
//            game.getPlayerStats(it)?.data?.get("rebounds")?.let { rebounds ->
//                guest_rebounds_leader_point.text = rebounds.toInt().toString()
//            }
//        }
//        guest_rebounds_leader_point.setTextColor(
//            resources.getColor(
//                guestTeam?.color ?: R.color.black
//            )
//        )
//
//        // guest assist leader
//        guest_assist_leader_image.setImageResource(
//            getPlayerById(game.guestAssistLeader)?.profilePic ?: R.drawable.ic_user_foreground
//        )
//        getPlayerById(game.guestAssistLeader)?.fullName?.let {
//            guest_assist_leader_name.text = it
//        }
//        game.guestAssistLeader?.let {
//            game.getPlayerStats(it)?.data?.get("assists")?.let { assists ->
//                guest_assist_leader_point.text = assists.toInt().toString()
//            }
//        }
//        guest_assist_leader_point.setTextColor(
//            resources.getColor(
//                guestTeam?.color ?: R.color.black
//            )
//        )
//
//        // guest steal leader
//        guest_steal_leader_image.setImageResource(
//            getPlayerById(game.guestStealLeader)?.profilePic ?: R.drawable.ic_user_foreground
//        )
//        getPlayerById(game.guestStealLeader)?.fullName?.let {
//            guest_steal_leader_name.text = it
//        }
//        game.guestStealLeader?.let {
//            game.getPlayerStats(it)?.data?.get("steals")?.let { steals ->
//                guest_steal_leader_point.text = steals.toInt().toString()
//            }
//        }
//        guest_steal_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))
//
//        // guest block leader
//        guest_block_leader_image.setImageResource(
//            getPlayerById(game.guestBlockLeader)?.profilePic ?: R.drawable.ic_user_foreground
//        )
//        getPlayerById(game.guestBlockLeader)?.fullName?.let {
//            guest_block_leader_name.text = it
//        }
//        game.guestBlockLeader?.let {
//            game.getPlayerStats(it)?.data?.get("blocks")?.let { blocks ->
//                guest_block_leader_point.text = blocks.toInt().toString()
//            }
//        }
//        guest_block_leader_point.setTextColor(resources.getColor(guestTeam?.color ?: R.color.black))


        /**
         * host team leader begin
         */
        host_icon.setImageResource(
            hostTeam?.profilePic ?: R.drawable.ic_user_foreground
        )

//        host_point_leader_image.setOnClickListener {
//            game.hostPointLeader?.let { it1 -> startPlayerActivity(it1) }
//        }
//        host_rebounds_leader_image.setOnClickListener {
//            game.hostReboundLeader?.let { it1 -> startPlayerActivity(it1) }
//        }
//        host_assist_leader_image.setOnClickListener {
//            game.hostAssistLeader?.let { it1 -> startPlayerActivity(it1) }
//        }
//        host_steal_leader_image.setOnClickListener {
//            game.hostStealLeader?.let { it1 -> startPlayerActivity(it1) }
//        }
//        host_block_leader_image.setOnClickListener {
//            game.hostBlockLeader?.let { it1 -> startPlayerActivity(it1) }
//        }
        host_icon.setOnClickListener {
            if (hostTeam != null)
                startTeamActivity(hostTeam)
        }

//        // host point leader
//        host_point_leader_image.setImageResource(
//            getPlayerById(game.hostPointLeader)?.profilePic ?: R.drawable.ic_user_foreground
//        )
//        getPlayerById(game.hostPointLeader)?.fullName?.let {
//            host_point_leader_name.text = it
//        }
//        game.hostPointLeader?.let {
//            game.getPlayerStats(it)?.data?.get("points")?.let {
//                host_point_leader_point.text = it.toInt().toString()
//            }
//        }
//        host_point_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        // host rebound leader
//        host_rebounds_leader_image.setImageResource(
//            getPlayerById(game.hostReboundLeader)?.profilePic ?: R.drawable.ic_user_foreground
//        )
//        getPlayerById(game.hostReboundLeader)?.fullName?.let {
//            host_rebounds_leader_name.text = it
//        }
//        game.hostReboundLeader?.let {
//            game.getPlayerStats(it)?.data?.get("rebounds")?.let {
//                host_rebounds_leader_point.text = it.toInt().toString()
//            }
//        }
//        host_rebounds_leader_point.setTextColor(
//            resources.getColor(
//                hostTeam?.color ?: R.color.black
//            )
//        )
//
//        // host assist leader
//        host_assist_leader_image.setImageResource(
//            getPlayerById(game.hostAssistLeader)?.profilePic ?: R.drawable.ic_user_foreground
//        )
//        getPlayerById(game.hostAssistLeader)?.fullName?.let {
//            host_assist_leader_name.text = it
//        }
//        game.hostAssistLeader?.let {
//            game.getPlayerStats(it)?.data?.get("assists")?.let {
//                host_assist_leader_point.text = it.toInt().toString()
//            }
//        }
//        host_assist_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        // host steal leader
//        host_steal_leader_image.setImageResource(
//            getPlayerById(game.hostStealLeader)?.profilePic ?: R.drawable.ic_user_foreground
//        )
//        getPlayerById(game.hostStealLeader)?.fullName?.let {
//            host_steal_leader_name.text = it
//        }
//        game.hostStealLeader?.let {
//            game.getPlayerStats(it)?.data?.get("steals")?.let {
//                host_steal_leader_point.text = it.toInt().toString()
//            }
//        }
//        host_steal_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        // host block leader
//        host_block_leader_image.setImageResource(
//            getPlayerById(game.hostBlockLeader)?.profilePic ?: R.drawable.ic_user_foreground
//        )
//        getPlayerById(game.hostBlockLeader)?.fullName?.let {
//            host_block_leader_name.text = it
//        }
//        game.hostBlockLeader?.let { it ->
//            game.getPlayerStats(it)?.data?.get("blocks")?.let { it1 ->
//                host_block_leader_point.text = it1.toInt().toString()
//            }
//        }
//        host_block_leader_point.setTextColor(resources.getColor(hostTeam?.color ?: R.color.black))


        /**
         * stats bar begin
         */
//        // guest stats chart
//        guest_field_goal.text =
//            gs["fieldGoalMade"]?.toInt().toString() +
//                    "/" + gs["fieldGoalAttempt"]?.toInt().toString() +
//                    "(" + gs["fieldGoalPercentage"]?.toString() + "%)"
//        guest_field_goal_bar.setBackgroundColor(
//            resources.getColor(
//                guestTeam?.color ?: R.color.black
//            )
//        )
//
//        guest_3_pointer.text =
//            gs["threePointMade"]?.toInt().toString() +
//                    "/" + gs["threePointAttempt"]?.toInt().toString() +
//                    "(" + gs["threePointPercentage"]?.toString() + "%)"
//        guest_3_pointer_bar.setBackgroundColor(
//            resources.getColor(
//                guestTeam?.color ?: R.color.black
//            )
//        )
//
//        guest_free_throw.text =
//            gs["freeThrow"]?.toInt().toString() +
//                    "/" + gs["freeThrowAttempt"]?.toInt().toString() +
//                    "(" + gs["freeThrowPercentage"]?.toString() + "%)"
//        guest_free_throw_bar.setBackgroundColor(
//            resources.getColor(
//                guestTeam?.color ?: R.color.black
//            )
//        )
//
//        guest_assist.text = gs["assists"]?.toInt().toString()
//        guest_assist_bar.setBackgroundColor(resources.getColor(guestTeam?.color ?: R.color.black))
//
//        guest_total_rebound.text = gs["rebounds"]?.toInt().toString()
//        guest_total_rebound_bar.setBackgroundColor(
//            resources.getColor(
//                guestTeam?.color ?: R.color.black
//            )
//        )
//
//        guest_offensive_rebound.text = gs["offensiveRebounds"]?.toInt().toString()
//        guest_offensive_rebound_bar.setBackgroundColor(
//            resources.getColor(
//                guestTeam?.color ?: R.color.black
//            )
//        )
//
//        guest_defensive_rebound.text = gs["defensiveRebounds"]?.toInt().toString()
//        guest_defensive_rebound_bar.setBackgroundColor(
//            resources.getColor(
//                guestTeam?.color ?: R.color.black
//            )
//        )
//
//        guest_steal.text = gs["steals"]?.toInt().toString()
//        guest_steal_bar.setBackgroundColor(resources.getColor(guestTeam?.color ?: R.color.black))
//
//        guest_block.text = gs["blocks"]?.toInt().toString()
//        guest_block_bar.setBackgroundColor(resources.getColor(guestTeam?.color ?: R.color.black))
//
//        guest_turnover.text = gs["turnovers"]?.toInt().toString()
//        guest_turnover_bar.setBackgroundColor(resources.getColor(guestTeam?.color ?: R.color.black))
//
//        guest_points_off_turnovers.text =
//            game.guestStats.data["pointsOffTurnovers"]?.toInt().toString()
//        guest_points_off_turnovers_bar.setBackgroundColor(
//            resources.getColor(
//                guestTeam?.color ?: R.color.black
//            )
//        )
//
//        guest_fouls.text = gs["personalFouls"]?.toInt().toString()
//        guest_fouls_bar.setBackgroundColor(resources.getColor(guestTeam?.color ?: R.color.black))
//
//        guest_timeouts_remaining.text =
//            game.guestStats.data["timeoutRemaining"]?.toInt().toString()
//        guest_timeouts_remaining_bar.setBackgroundColor(
//            resources.getColor(
//                guestTeam?.color ?: R.color.black
//            )
//        )

//        // host stats chart
//        host_field_goal.text =
//            hs["fieldGoalMade"]?.toInt().toString() +
//                    "/" + hs["fieldGoalAttempt"]?.toInt().toString() +
//                    "(" + hs["fieldGoalPercentage"]?.toString() + "%)"
//        host_field_goal_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        host_3_pointer.text =
//            hs["threePointMade"]?.toInt().toString() +
//                    "/" + hs["threePointAttempt"]?.toInt().toString() +
//                    "(" + hs["threePointPercentage"]?.toString() + "%)"
//        host_3_pointer_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        host_free_throw.text =
//            hs["freeThrowMade"]?.toInt().toString() +
//                    "/" + hs["freeThrowAttempt"]?.toInt().toString() +
//                    "(" + hs["freeThrowPercentage"]?.toString() + "%)"
//        host_free_throw_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        host_assist.text = hs["assists"]?.toInt().toString()
//        host_assist_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        host_total_rebound.text = hs["rebounds"]?.toInt().toString()
//        host_total_rebound_bar.setBackgroundColor(
//            resources.getColor(
//                hostTeam?.color ?: R.color.black
//            )
//        )
//
//        host_offensive_rebound.text = hs["offensiveRebounds"]?.toInt().toString()
//        host_offensive_rebound_bar.setBackgroundColor(
//            resources.getColor(
//                hostTeam?.color ?: R.color.black
//            )
//        )
//
//        host_defensive_rebound.text = hs["defensiveRebounds"]?.toInt().toString()
//        host_defensive_rebound_bar.setBackgroundColor(
//            resources.getColor(
//                hostTeam?.color ?: R.color.black
//            )
//        )
//
//        host_steal.text = hs["steals"]?.toInt().toString()
//        host_steal_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        host_block.text = hs["blocks"]?.toInt().toString()
//        host_block_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        host_turnover.text = hs["turnovers"]?.toInt().toString()
//        host_turnover_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        host_points_off_turnovers.text =
//            game.hostStats.data["pointsOffTurnovers"]?.toInt().toString()
//        host_points_off_turnovers_bar.setBackgroundColor(
//            resources.getColor(
//                hostTeam?.color ?: R.color.black
//            )
//        )
//
//        host_fouls.text = hs["personalFouls"]?.toInt().toString()
//        host_fouls_bar.setBackgroundColor(resources.getColor(hostTeam?.color ?: R.color.black))
//
//        host_timeouts_remaining.text = game.hostStats.data["timeoutRemaining"]?.toInt().toString()
//        host_timeouts_remaining_bar.setBackgroundColor(
//            resources.getColor(
//                hostTeam?.color ?: R.color.black
//            )
//        )

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
            gs["threePointPercentage"],
            hs["threePointPercentage"]
        )
        changeBarWidth(
            completeBarWidth,
            guest_free_throw_bar,
            host_free_throw_bar,
            gs["freeThrowPercentage"],
            hs["freeThrowPercentage"]
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