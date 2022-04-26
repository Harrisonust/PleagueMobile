package com.example.gamechangermobile.playerpage

import android.os.AsyncTask
import android.os.AsyncTask.execute
import android.os.Build
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gamechangermobile.MainActivity
import kotlinx.android.synthetic.main.activity_player.*
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.*
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.gamechangermobile.models.Player
import kotlinx.android.synthetic.main.activity_player.*
import com.example.gamechangermobile.database.Dictionary
import com.example.gamechangermobile.models.PlayerID
import com.example.gamechangermobile.models.getPlayerById
import com.example.gamechangermobile.models.getTeamById
import com.example.gamechangermobile.user.addToFavPlayer

import com.google.android.material.tabs.TabLayout
import org.jsoup.Jsoup
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class PlayerActivity : AppCompatActivity() {
    lateinit var player: Player
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val playerID = intent.getParcelableExtra<PlayerID>("SELECTED_PLAYER")
        player = playerID?.let { getPlayerById(it) }!! //TODO dangerous

        val model: PlayerViewModel by viewModels{ PlayerViewModelFactory(player?.GCID!!) }

        val imageResource = if (Dictionary.playerToImageResource.containsKey(player?.fullName?.trim())) Dictionary.playerToImageResource[player?.fullName?.trim()] else R.drawable.ic_baseline_sports_basketball_24
        if (imageResource != null) {
            player_page_profile_pic.setImageResource(
                imageResource
            )
        }
        player_page_player_firstname.text = player?.firstName
        player_page_player_lastname.text = player?.lastName
        player_page_player_team.text = getTeamById(player?.teamId)?.name
        player_page_player_number.text = "#" + player?.number.toString()
        player_page_player_position.text = player?.position
        player?.averageStat?.data?.get("points")?.let {
            player_page_player_pts.text = it.toString()
        }
        player?.averageStat?.data?.get("rebounds")?.let {
            player_page_player_reb.text = it.toString()
        }
        player?.averageStat?.data?.get("assists")?.let {
            player_page_player_ast.text = it.toString()
        }

        player_page_player_favorite_btn.setOnClickListener { view ->
            player?.let { addToFavPlayer(view, it.playerID) }
        }

        player_page_tab.addTab(player_page_tab.newTab().setText("Game Record"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Stats"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Career"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Adv"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Team eff"))

        player_page_viewpager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                player_page_tab
            )
        )
        player_page_viewpager.adapter = player?.let { VPagerAdapter(supportFragmentManager, 5, it) }
        player_page_viewpager.setCurrentItem(0)
        player_page_tab.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                player_page_viewpager
            )
        )
        FetchPlayerStatsTask().execute()
    }

    inner class VPagerAdapter(f: FragmentManager, bh: Int, val player: Player) :
        FragmentPagerAdapter(f, bh) {
        override fun getCount(): Int = 5

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> PlayerPageGameRecordFragment(player)
                1 -> PlayerPageStatsFragment(player)
                2 -> PlayerPageCareerFragment(player)
                3 -> PlayerPageAdvancedStatsFragment(player)
                4 -> PlayerPageTeamEffFragment(player)
                else -> PlayerPageTeamEffFragment(player)
            }
        }
    }

    inner class FetchPlayerStatsTask : AsyncTask<Unit, Int, Boolean>() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg p0: Unit?): Boolean = try {
            val url = "https://pleagueofficial.com/player/${player.playerID.ID}"
            val doc = Jsoup.connect(url).get()
            Log.d("Debug", url)
            doc.select("table.table.fs12.col-md-12.bg-light.table-hover").first().children()
                .select("tbody").first().children()
                .select("tr")
                .forEach {
                    var gameID = -1
                    it.children().select("a")
                        .forEach { it1 ->
                            val regex = "^<a .*?/game/([0-9]*?)\">(.*?)</a>\$".toRegex()
                            gameID = regex.find(it1.toString())?.groups?.get(1)?.value?.toInt()!!
                        }
                    val regex =
                        "^([0-9]*-[0-9]*-[0-9]*) (.*?) (.*?) ([0-9]*[W|L]?) - ([0-9]*[W|L]?) ([0-9]*:[0-9]*) ([0-9]*) - ([0-9]*) (.*?) ([0-9]*) - ([0-9]*) (.*?) ([0-9]*) - ([0-9]*) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?) (.*?)\$".toRegex()
                    val parsed = regex.find(it.text())
                    val date = parsed?.groups?.get(1)?.value
                    val team = parsed?.groups?.get(2)?.value
                    val opponent = parsed?.groups?.get(3)?.value
                    val teamScore = parsed?.groups?.get(4)?.value
                    val opponentScore = parsed?.groups?.get(5)?.value
                    val onCourtTime = parsed?.groups?.get(6)?.value
                    val two_point_made = parsed?.groups?.get(7)?.value?.toFloat()
                    val two_point_attempt = parsed?.groups?.get(8)?.value?.toFloat()
                    val two_point_percentage = parsed?.groups?.get(9)?.value?.toFloat()

                    val three_point_made = parsed?.groups?.get(10)?.value?.toFloat()
                    val three_point_attempt = parsed?.groups?.get(11)?.value?.toFloat()
                    val three_point_percentage = parsed?.groups?.get(12)?.value?.toFloat()

                    val ft_made = parsed?.groups?.get(13)?.value?.toFloat()
                    val ft_attempt = parsed?.groups?.get(14)?.value?.toFloat()
                    val ft_percentage = parsed?.groups?.get(15)?.value?.toFloat()

                    val pts = parsed?.groups?.get(16)?.value?.toFloat()
                    val reb = parsed?.groups?.get(17)?.value?.toFloat()
                    val ast = parsed?.groups?.get(18)?.value?.toFloat()
                    val to = parsed?.groups?.get(19)?.value?.toFloat()
                    val stl = parsed?.groups?.get(20)?.value?.toFloat()
                    val blk = parsed?.groups?.get(21)?.value?.toFloat()
                    val pf = parsed?.groups?.get(22)?.value?.toFloat()
                    Log.d("Debug",
                        "D: $date ${team} vs ${opponent} score: ${teamScore}-$opponentScore time: $onCourtTime" +
                                "\ntwo: $two_point_made/$two_point_attempt - $two_point_percentage%" +
                                "\tthree: $three_point_made/$three_point_attempt - $three_point_percentage%" +
                                "\tft: $ft_made/$ft_attempt - $ft_percentage%" +
                                "\npts: $pts  reb: $reb  ast: $ast  to: $to  stl: $stl  blk: $blk  pf: $pf" +
                                "\n-------------------------------------------"
                    )
                    val stat = PlayerStats(
                        points = pts!!,
                        rebounds = reb!!,
                        assists = ast!!,
                        twoPointMade = two_point_made!!,
                        twoPointAttempt = two_point_attempt!!,
                        twoPointPercentage = two_point_percentage!!,
                        threePointMade = three_point_made!!,
                        threePointAttempt = three_point_attempt!!,
                        threePointPercentage = three_point_percentage!!,
                        freeThrowMade = ft_made!!,
                        freeThrowAttempt = ft_attempt!!,
                        freeThrowPercentage = ft_percentage!!,
                        steals = stl!!,
                        blocks = blk!!,
                        turnovers = to!!,
                        personalFouls = pf!!,
                    )
                    player.stats[GameID(gameID)] = stat
                }

            true
        } catch (e: Exception) {
            false
        }
    }
}


