package com.example.gamechangermobile.playerpage

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.MainActivity.Companion.league_average_player
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Player
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import kotlinx.android.synthetic.main.fragment_game_page_summary.*
import kotlinx.android.synthetic.main.fragment_player_page_stats.*
import java.util.*
import kotlin.collections.ArrayList

class PlayerPageStatsFragment(val player: Player) : Fragment() {

    private var radarData: RadarData? = null
    private val xDatas = ArrayList<String>()
    private val yDatas1 = ArrayList<RadarEntry>()
    private val yDatas2 = ArrayList<RadarEntry>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_page_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (xDatas.isNullOrEmpty() and yDatas1.isNullOrEmpty()) {
            val player_s = player.averageStat.data
            val l_avg_player_s = league_average_player.averageStat.data
            xDatas.add("Points")
            yDatas1.add(RadarEntry(player_s["points"]!!))
            yDatas2.add(RadarEntry(l_avg_player_s["points"]!!))
            xDatas.add("Rebounds")
            yDatas1.add(RadarEntry(player_s["rebounds"]!!))
            yDatas2.add(RadarEntry(l_avg_player_s["rebounds"]!!))
            xDatas.add("Assists")
            yDatas1.add(RadarEntry(player_s["assists"]!!))
            yDatas2.add(RadarEntry(l_avg_player_s["assists"]!!))
            xDatas.add("Steals")
            yDatas1.add(RadarEntry(player_s["steals"]!!))
            yDatas2.add(RadarEntry(l_avg_player_s["steals"]!!))
            xDatas.add("Blocks")
            yDatas1.add(RadarEntry(player_s["blocks"]!!))
            yDatas2.add(RadarEntry(l_avg_player_s["blocks"]!!))
            xDatas.add("Turnovers")
            yDatas1.add(RadarEntry(player_s["turnovers"]!!))
            yDatas2.add(RadarEntry(l_avg_player_s["turnovers"]!!))
        }
        radarchart.setTouchEnabled(false)
        radarchart.extraTopOffset = 10f
        radarchart.description.isEnabled = false
        radarData = getRadarData()
        showRadarChart(radarchart, radarData!!)
    }

    private fun getRadarData(): RadarData {
        val radarDataSet1 = RadarDataSet(yDatas1, "2022 SEASON")
        radarDataSet1.color = resources.getColor(R.color.bg_color)      // boarder color
        radarDataSet1.fillColor = resources.getColor(R.color.blue_400_transparent)
        radarDataSet1.setDrawFilled(true)
        radarDataSet1.fillAlpha = 180
        radarDataSet1.lineWidth = 2f                                        // lidar boarder line width
        radarDataSet1.isDrawHighlightCircleEnabled = true
        radarDataSet1.setDrawHighlightIndicators(false)

        val radarDataSet2 = RadarDataSet(yDatas2, "LEAGUE AVERAGE")
        radarDataSet2.color = resources.getColor(R.color.bg_color)      // boarder color
        radarDataSet2.fillColor = resources.getColor(R.color.blue_200_transparent)
        radarDataSet2.setDrawFilled(true)
        radarDataSet2.fillAlpha = 180
        radarDataSet2.lineWidth = 2f
        radarDataSet2.isDrawHighlightCircleEnabled = true
        radarDataSet2.setDrawHighlightIndicators(false)


        val radarDataSets = ArrayList<IRadarDataSet>()
        radarDataSets.add(radarDataSet1)
        radarDataSets.add(radarDataSet2)

        return RadarData(radarDataSets)
    }

    private fun showRadarChart(radarChart: RadarChart, radarData: RadarData) {
        radarChart.setDrawWeb(true)
        radarChart.setWebLineWidth(1f)
        radarChart.setWebColor(Color.rgb(0, 0, 0))
        radarChart.setWebColorInner(Color.rgb(0, 0, 0))
        radarChart.setWebLineWidthInner(1f)

        val legend = radarchart.getLegend()
//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART_INSIDE) // todo
        legend.setForm(Legend.LegendForm.CIRCLE)

        radarchart.setData(radarData)
        radarchart.invalidate()         // refresh screen
    }
}