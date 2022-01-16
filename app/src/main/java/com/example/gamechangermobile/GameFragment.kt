package com.example.gamechangermobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.MainActivity.Companion.Braves
import com.example.gamechangermobile.MainActivity.Companion.Dreamers
import com.example.gamechangermobile.MainActivity.Companion.Kings
import com.example.gamechangermobile.MainActivity.Companion.Lioneers
import com.example.gamechangermobile.MainActivity.Companion.Pilots
import com.example.gamechangermobile.MainActivity.Companion.Steelers
import com.example.gamechangermobile.gametab.GameAdapter
import com.example.gamechangermobile.models.Game
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

    private fun initGames() {
        repeat(5) {
            gameList.add(Game(GuestTeam = Kings, HostTeam = Lioneers, guestScore = 18, hostScore = 12, status = GameStatus.INGAME, quarter = "1st", remainingTime = "4:17"))
            gameList.add(Game(GuestTeam = Braves,HostTeam = Pilots, guestScore = 99, hostScore = 87, status = GameStatus.INGAME, quarter = "4nd", remainingTime = "0:12"))
            gameList.add(Game(GuestTeam = Steelers, HostTeam = Dreamers, guestScore = 98, hostScore = 121, status = GameStatus.END, quarter = "none",remainingTime = "none"))
        }
    }
}