package com.example.gamechangermobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.games
import com.example.gamechangermobile.gametab.GameAdapter
import com.example.gamechangermobile.models.*
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.collections.ArrayList

class GameFragment : Fragment() {
    private val gameList = ArrayList<Game>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        game_recyclerview?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = GameAdapter(games)
        }
    }
}