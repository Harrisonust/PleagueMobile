package com.example.gamechangermobile.teampage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Team
import kotlinx.android.synthetic.main.fragment_team_page_info.*


class TeamPageInfoFragment(val team: Team) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_team_page_info, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        total_record.text = team.total_record
        home_record.text = team.home_record
        away_record.text = team.away_record
        last10.text = team.last10
        streak.text = team.streak
        arena.text = team.arena
        founding_date.text = team.founding_date
    }

}