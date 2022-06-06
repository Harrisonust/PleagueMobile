package com.example.gamechangermobile.teampage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.TeamID
import com.example.gamechangermobile.models.getTeamById
import kotlinx.android.synthetic.main.fragment_team_page_info.*


class TeamPageInfoFragment(private val teamID: TeamID) : Fragment() {
    val model: TeamViewModel by viewModels { TeamViewModelFactory(teamID.ID) }

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
        val team = getTeamById(teamID)
        if (team != null) {
            model.totalRecord.observe(viewLifecycleOwner) {
                total_record.text = it.getRecord()
            }

//            home_record.text = team.homeRecord.getTotalRecord()
//            away_record.text = team.awayRecord.getTotalRecord()

            model.homeRecord.observe(viewLifecycleOwner){
                home_record.text = it.getRecord()
            }

            model.roadRecord.observe(viewLifecycleOwner){
                away_record.text = it.getRecord()
            }

            model.streak.observe(viewLifecycleOwner) {
                streak.text = it
            }

            model.arena.observe(viewLifecycleOwner) {
                arena.text = it
            }

//            last10.text = team.last10.getTotalRecord()
            model.last10.observe(viewLifecycleOwner){
                last10.text = it
            }

            model.foundingDate.observe(viewLifecycleOwner) {
                founding_date.text = it
            }

            model.bio.observe(viewLifecycleOwner) {
                team_bio.text = it
            }
        }
    }
}