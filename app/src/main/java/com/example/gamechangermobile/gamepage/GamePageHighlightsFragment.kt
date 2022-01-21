package com.example.gamechangermobile.gamepage

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Highlight
import kotlinx.android.synthetic.main.fragment_game_page_highlights.*

class GamePageHighlightsFragment : Fragment() {

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
            Highlight(Uri.parse("https://www.youtube.com/watch?v=r5D1uwyjrvE")),
            Highlight(Uri.parse("https://www.youtube.com/watch?v=r5D1uwyjrvE")),
            Highlight(Uri.parse("https://www.youtube.com/watch?v=r5D1uwyjrvE")),
        )
        highlight_recyclerview.adapter = HighlightAdapter(highlightsList)
        highlight_recyclerview.layoutManager = LinearLayoutManager(context)
    }

}