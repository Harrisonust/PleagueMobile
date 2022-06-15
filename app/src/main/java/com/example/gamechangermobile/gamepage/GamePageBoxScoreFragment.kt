package com.example.gamechangermobile.gamepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.getTeamById
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_game_page_box_score.*

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

        game_page_score_tab_view_pager.addOnPageChangeListener(
            object: ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    Log.d("TEST", "Position: $position")
                }

                override fun onPageSelected(position: Int) {
                    Log.d("TEST", "Selected Position $position")
                    if (position == 0) {
                        val constraintSet = ConstraintSet()
                        constraintSet.clone(tab_constraint_layout)
                        constraintSet.clear(R.id.indicator, ConstraintSet.RIGHT)
                        constraintSet.connect(R.id.indicator, ConstraintSet.LEFT, R.id.game_page_score_tab_team_tab, ConstraintSet.LEFT, 0)
                        constraintSet.connect(R.id.indicator, ConstraintSet.TOP, R.id.game_page_score_tab_team_tab, ConstraintSet.TOP, 0)
                        constraintSet.applyTo(tab_constraint_layout)
                    }
                    else if (position == 1) {
                        Log.d("TEST", "Position: 1")
                        val constraintSet = ConstraintSet()
                        constraintSet.clone(tab_constraint_layout)
                        constraintSet.clear(R.id.indicator, ConstraintSet.LEFT)
                        constraintSet.connect(R.id.indicator, ConstraintSet.RIGHT, R.id.game_page_score_tab_team_tab, ConstraintSet.RIGHT, 0)
                        constraintSet.connect(R.id.indicator, ConstraintSet.TOP, R.id.game_page_score_tab_team_tab, ConstraintSet.TOP, 0)
                        constraintSet.applyTo(tab_constraint_layout)
                    }
                }

                override fun onPageScrollStateChanged(state: Int) {
                    Log.d("TEST", "StateChanged $state")
                }

            }
        )
        game_page_score_tab_view_pager.adapter = PagerAdapter(childFragmentManager, 2, game)
        game_page_score_tab_view_pager.currentItem = 0
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