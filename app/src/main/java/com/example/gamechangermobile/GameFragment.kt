package com.example.gamechangermobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.Braves
import com.example.gamechangermobile.MainActivity.Companion.Dreamers
import com.example.gamechangermobile.MainActivity.Companion.Kings
import com.example.gamechangermobile.MainActivity.Companion.Lioneers
import com.example.gamechangermobile.MainActivity.Companion.Pilots
import com.example.gamechangermobile.MainActivity.Companion.Steelers
import com.example.gamechangermobile.MainActivity.Companion.b_dawson
import com.example.gamechangermobile.MainActivity.Companion.hao_tien
import com.example.gamechangermobile.MainActivity.Companion.hsing_chih_yang
import com.example.gamechangermobile.MainActivity.Companion.kuo_hao_kao
import com.example.gamechangermobile.MainActivity.Companion.nick_faust
import com.example.gamechangermobile.MainActivity.Companion.q_davis
import com.example.gamechangermobile.MainActivity.Companion.thomas_welsh
import com.example.gamechangermobile.gametab.GameAdapter
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.GameStats
import com.example.gamechangermobile.models.GameStatus
import kotlinx.android.synthetic.main.fragment_game.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    private val gameList = ArrayList<Game>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initGames()
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        game_recyclerview?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = GameAdapter(gameList)
        }
    }
    val fakeGameStats1 = GameStats(points = 102F, rebounds = 35F, offensive_rebounds = 3F, defensive_rebounds = 32F, assists = 27F, fieldGoal = 38F, fieldGoalAttempt = 76F, fieldGoalPercentage = 50F, fieldGoal_3pt = 11F, fieldGoalAttempt_3pt = 28F, fieldGoalPercentage_3pt = 39.3F, freeThrow = 15F, freeThrowAttempt = 22F, freeThrowAttemptPercentage = 68.2F, steals = 10F, blocks = 2F, turnovers = 12F, timeout_remaining = 1, fouls = 26F, point_leader_player = kuo_hao_kao, rebound_leader_player = b_dawson, assist_leader_player = kuo_hao_kao, steal_leader_player = nick_faust, block_leader_player = hao_tien)
    val fakeGameStats2 = GameStats(points = 91F, rebounds = 48F, offensive_rebounds = 14F, defensive_rebounds = 34F, assists = 21F, fieldGoal = 29F, fieldGoalAttempt = 77F, fieldGoalPercentage = 37.7F, fieldGoal_3pt = 9F, fieldGoalAttempt_3pt = 38F, fieldGoalPercentage_3pt = 23.7F, freeThrow = 24F, freeThrowAttempt = 36F, freeThrowAttemptPercentage = 66.7F, steals = 6F, blocks = 5F, turnovers = 15F, timeout_remaining = 0, fouls = 21F, point_leader_player = hsing_chih_yang, rebound_leader_player = hsing_chih_yang, assist_leader_player = thomas_welsh, steal_leader_player = thomas_welsh, block_leader_player = q_davis)
    private fun initGames() {
        repeat(5) {
            gameList.add(Game(GuestTeam = Kings, HostTeam = Lioneers, GuestStats = fakeGameStats1, HostStats = fakeGameStats2, status = GameStatus.INGAME, quarter = "1st", remaining_time = "4:17"))
            gameList.add(Game(GuestTeam = Braves,HostTeam = Pilots, GuestStats = fakeGameStats1, HostStats = fakeGameStats2, status = GameStatus.NOT_YET_START, starting_time = "19:00"))
            gameList.add(Game(GuestTeam = Steelers, HostTeam = Dreamers, GuestStats = fakeGameStats1, HostStats = fakeGameStats2, status = GameStatus.END))
        }
    }
}