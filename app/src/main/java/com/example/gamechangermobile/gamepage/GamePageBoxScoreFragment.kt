package com.example.gamechangermobile.gamepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.R
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.PlgGame
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.PlayerID
import com.example.gamechangermobile.models.PlayerStats
import com.example.gamechangermobile.models.getTeamById
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_game_page_box_score.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GamePageBoxScoreFragment(val game: Game) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_page_box_score, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        game_page_score_tab_team_tab.addTab(
            game_page_score_tab_team_tab.newTab().setText(
                getTeamById(game.guestTeam)?.name
            )
        )
        game_page_score_tab_team_tab.addTab(
            game_page_score_tab_team_tab.newTab().setText(
                getTeamById(game.hostTeam)?.name
            )
        )

        game_page_score_tab_view_pager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                game_page_score_tab_team_tab
            )
        )
        game_page_score_tab_view_pager.adapter = PagerAdapter(childFragmentManager, 2, game)
        game_page_score_tab_view_pager.setCurrentItem(0)
        game_page_score_tab_team_tab.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                game_page_score_tab_view_pager
            )
        )
    }

    inner class PagerAdapter(f: FragmentManager, bh: Int, val game: Game) :
        FragmentPagerAdapter(f, bh) {
        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> GamePageBoxScoreFragmentGuestTab(game)
                else -> GamePageBoxScoreFragmentHostTab(game)
            }
        }
    }

}