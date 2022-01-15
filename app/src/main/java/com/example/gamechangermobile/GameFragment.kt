package com.example.gamechangermobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechangermobile.gametab.GameAdapter
import com.example.gamechangermobile.models.Game
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
        repeat(10) {
            gameList.add(Game("108:127", "12:17",
                "Warriors", "30-11", "",
                "Lakers", "4-15", ""))
        }
    }
}