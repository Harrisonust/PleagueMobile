package com.example.gamechangermobile.gamepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.Highlight
import kotlinx.android.synthetic.main.fragment_game_page_highlights.*

class GamePageHighlightsFragment(val game: Game) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_page_highlights, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val highlightsList = listOf<Highlight>(
            Highlight("https://www.youtube.com/embed/L7OomIRq6AY"),
            Highlight("https://www.youtube.com/embed/r5D1uwyjrvE"),
            Highlight("https://www.youtube.com/embed/F9mI5dApJog"),
        )
        highlight_recyclerview.adapter = HighlightAdapter(highlightsList)
        highlight_recyclerview.layoutManager = LinearLayoutManager(context)
    }

}