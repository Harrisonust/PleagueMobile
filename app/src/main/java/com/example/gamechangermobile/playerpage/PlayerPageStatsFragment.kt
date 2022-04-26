package com.example.gamechangermobile.playerpage

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.Player
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import kotlinx.android.synthetic.main.fragment_player_page_stats.*
import java.util.*
import kotlin.collections.ArrayList

class PlayerPageStatsFragment(val player: Player) : Fragment() {

    private var radarData: RadarData? = null
    private val xDatas = ArrayList<String>()
    private val yDatas1 = ArrayList<RadarEntry>()
    private val playerViewModel: PlayerViewModel by activityViewModels { PlayerViewModelFactory(player.GCID) }
//    private val yDatas2 = ArrayList<RadarEntry>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_page_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val xAxis = radarchart.xAxis
        val labels = listOf("Points", "Rebounds", "Assists", "Steals", "Blocks", "Turnovers")
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)

        if (xDatas.isNullOrEmpty() and yDatas1.isNullOrEmpty()) {
            playerViewModel.getChartStats().observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    xDatas.add("Points")
                    yDatas1.add(RadarEntry(it["PTS"]!!))
                    xDatas.add("Rebounds")
                    yDatas1.add(RadarEntry(it["REB"]!!))
                    xDatas.add("Assists")
                    yDatas1.add(RadarEntry(it["AST"]!!))
                    xDatas.add("Steals")
                    yDatas1.add(RadarEntry(it["STL"]!!))
                    xDatas.add("Blocks")
                    yDatas1.add(RadarEntry(it["BLK"]!!))
                    xDatas.add("Turnovers")
                    yDatas1.add(RadarEntry(it["TOV"]!!))
                    radarData = getRadarData()
                    showRadarChart(radarchart, radarData!!)
                }
            })
        }
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

//        val radarDataSet2 = RadarDataSet(xDatas, "2021 SEASON")
//        radarDataSet2.color = Color.rgb(121, 162, 175)
//        radarDataSet2.fillColor = Color.rgb(147, 112, 219)
//        radarDataSet2.setDrawFilled(true)
//        radarDataSet2.fillAlpha = 180
//        radarDataSet2.lineWidth = 2f
//        radarDataSet2.isDrawHighlightCircleEnabled = true
//        radarDataSet2.setDrawHighlightIndicators(false)


        val radarDataSets = ArrayList<IRadarDataSet>()
        radarDataSets.add(radarDataSet1)
//        radarDataSets.add(radarDataSet2)

        return RadarData(radarDataSets)
    }

    private fun showRadarChart(radarChart: RadarChart, radarData: RadarData) {
        radarChart.setDrawWeb(true)
        radarChart.webLineWidth = 1f
        radarChart.webColor = Color.rgb(0, 0, 0)
        radarChart.webAlpha = 50
        radarChart.webColorInner = Color.rgb(0, 0, 0)
        radarChart.webLineWidthInner = 1f

        radarchart.setTouchEnabled(false)
        radarchart.extraTopOffset = 10f
        radarchart.description.isEnabled = false

        val legend = radarchart.legend
//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART_INSIDE) // todo
        legend.form = Legend.LegendForm.CIRCLE

        radarchart.data = radarData
        radarchart.invalidate()         // refresh screen
    }
}