package com.example.gamechangermobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_stats.*


class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tab_layout.addTab(tab_layout.newTab().setText("Teams"))
        tab_layout.addTab(tab_layout.newTab().setText("Players"))

        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        view_pager.adapter = PagerAdapter(childFragmentManager)
        view_pager.setCurrentItem(0)
        tab_layout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))
    }

    inner class PagerAdapter(f: FragmentManager) : FragmentPagerAdapter(f) {
        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> StatsFragmentTeamTab()
                else -> StatsFragmentPlayerTab()
            }
        }
    }
}