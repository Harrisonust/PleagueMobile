package com.example.gamechangermobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gamechangermobile.models.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()

        val gamesFrag = GameFragment()
        val statsFrag = StatsFragment()
        replaceFragment(gamesFrag)

        debugButton.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            val team = Lioneers
            intent.putExtra("SELECTED_TEAM", team)
            startActivity(intent)
        }
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.games_page -> {
                    replaceFragment(gamesFrag)
                    true
                }
                R.id.stats_page -> {
                    replaceFragment(statsFrag)
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragView, fragment)
            commit()
        }
    }

    companion object {
        var chih_chieh_lin =
                Player(
                        firstName = "chih-chieh",
                        lastName = "Lin",
                        profilePic = R.drawable.braves_chih_chieh_lin,
                        stats =
                        mutableMapOf(
                                Date(2001, 10, 2)
                                        to PlayerStats(
                                        9.5F,
                                        3.33F,
                                        1.83F
                                )
                        ),
                        age = 38,
                        number = "12",
                        position = "F"
                )
        var hsiang_chun_tseng =
                Player(
                        firstName = "Hsiang-chun",
                        lastName = "Tseng",
                        profilePic = R.drawable.braves_hsiang_chun_tseng,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                3.38F,
                                                3.25F,
                                                0.5F
                                        )
                        ),
                        age = 22,
                        number = "21",
                        position = "C"
                )
        var michael_sigletary =
                Player(
                        firstName = "Michael",
                        lastName = "Singletary",
                        profilePic = R.drawable.braves_michael_singletary,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                12.8F,
                                                3.4F,
                                                3.1F
                                        )
                        ),
                        age = 28,
                        number = "17",
                        position = "F"
                )
        var shu_wei_lin =
                Player(
                        firstName = "Shu_wei",
                        lastName = "Lin",
                        profilePic = R.drawable.braves_shu_wei_lin,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                12.13F,
                                                2.88F,
                                                4.13F
                                        )
                        ),
                        age = 28,
                        number = "1",
                        position = "G"
                )

        var q_davis =
                Player(
                        firstName = "Q",
                        lastName = "Davis",
                        profilePic = R.drawable.kings_q_davis,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2, 19, 0)
                                        to
                                        PlayerStats(
                                                5F,
                                                11F,
                                                1F
                                        )
                        ),
                        number = "50",
                        position = "C"
                )
        var thomas_welsh =
                Player(
                        firstName = "Thomas",
                        lastName = "Welsh",
                        profilePic = R.drawable.kings_thomas_welsh,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2, 19, 0)
                                        to
                                        PlayerStats(
                                                18.57F,
                                                19.57F,
                                                2.71F
                                        )
                        ),
                        age = 24,
                        number = "40",
                        position = "C"
                )
        var chun_nan_chen =
                Player(
                        firstName = "Chun-nan",
                        lastName = "Chen",
                        profilePic = R.drawable.kings_chun_nan_chen,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2, 19, 0)
                                        to
                                        PlayerStats(
                                                3.2F,
                                                1.8F,
                                                0.8F
                                        )
                        ),
                        age = 20,
                        number = "3",
                        position = "F"
                )
        var hsing_chih_yang =
                Player(
                        firstName = "Hsing-chih",
                        lastName = "Yang",
                        profilePic = R.drawable.kings_hsing_chih_yang,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2, 19, 0)
                                        to
                                        PlayerStats(
                                                4.11F,
                                                3.22F,
                                                0.44F
                                        )
                        ),
                        age = 27,
                        number = "33",
                        position = "F"
                )

        var d_roboson =
                Player(
                        firstName = "Devin",
                        lastName = "Roboson",
                        profilePic = R.drawable.pilots_devin_robinson,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                24.8F,
                                                14.6F,
                                                2.2F
                                        )
                        ),
                        age = 25,
                        number = "0",
                        position = "F"
                )
        var chuh_hsiang_lu =
                Player(
                        firstName = "Chun-hsiang",
                        lastName = "Lu",
                        profilePic = R.drawable.pilots_chun_hsiang_lu,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                15.33F,
                                                5F,
                                                0.33F
                                        )
                        ),
                        number = "69",
                        position = "G"
                )
        var jordan_tolbert =
                Player(
                        firstName = "Chun-nan",
                        lastName = "Chen",
                        profilePic = R.drawable.pilots_jordan_tolbert,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                19.17F,
                                                14.17F,
                                                4.33F
                                        )
                        ),
                        age = 28,
                        number = "1",
                        position = "F"
                )
        var chih_yao_shih =
                Player(
                        firstName = "Chin-Yao",
                        lastName = "Shih",
                        profilePic = R.drawable.pilots_chin_yao_shih,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                14.17F,
                                                3.25F,
                                                3.63F
                                        )
                        ),
                        age = 30,
                        number = "28",
                        position = "F"
                )

        var kuo_hao_kao =
                Player(
                        firstName = "Oscar",
                        lastName = "Kao",
                        profilePic = R.drawable.lioneers_kuo_hao_kao,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                7.17F,
                                                6.17F,
                                                3F
                                        )
                        ),
                        number = "4",
                        position = "G"
                )
        var nick_faust =
                Player(
                        firstName = "Nicholas",
                        lastName = "Faust",
                        profilePic = R.drawable.lioneers_nicholas_faust,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                27F,
                                                8F,
                                                3F
                                        ),
                                Date(2021, 1, 3)
                                        to
                                        PlayerStats(
                                                45F,
                                                12F,
                                                2F
                                        )
                        ),
                        age = 27,
                        number = "7",
                        position = "F"
                )
        var b_dawson =
                Player(
                        firstName = "Branden",
                        lastName = "Dawson",
                        profilePic = R.drawable.lioneers_branden_dawson,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                14.33F,
                                                14F,
                                                1.67F
                                        )
                        ),
                        number = "22",
                        position = "F"
                )
        var even_lee =
                Player(
                        firstName = "Chia-jul",
                        lastName = "Lee",
                        profilePic = R.drawable.lioneers_chia_jul_lee,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                6.89F,
                                                4.22F,
                                                2.67F
                                        )
                        ),
                        age = 26,
                        number = "12",
                        position = "F"
                )
        var ming_yi =
                Player(
                        firstName = "Ming-yi",
                        lastName = "Lin",
                        profilePic = R.drawable.lioneers_ming_yi_lin,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                3.94F,
                                                2.71F,
                                                2.88F
                                        )
                        ),
                        age = 24,
                        number = "3",
                        position = "G"
                )
        var bhullar =
                Player(
                        firstName = "Singh",
                        lastName = "Bhullar",
                        profilePic = R.drawable.lioneers_simbhullar,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                26.33F,
                                                21.89F,
                                                2.44F
                                        )
                        ),
                        age = 28,
                        number = "35",
                        position = "C"
                )
        var yi_huei =
                Player(
                        firstName = "Yi-huei",
                        lastName = "Lin",
                        profilePic = R.drawable.lioneers_yi_huei_lin,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                11.56F,
                                                4.22F,
                                                3.4F
                                        )
                        ),
                        age = 34,
                        number = "36",
                        position = "F"
                )
        var leon_sung =
                Player(
                        firstName = "Yu-hsuan",
                        lastName = "Sung",
                        profilePic = R.drawable.lioneers_yu_hsuan_sung,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                5.2F,
                                                0.8F,
                                                1F
                                        )
                        ),
                        age = 31,
                        number = "5",
                        position = "F"
                )
        var elliot =
                Player(
                        firstName = "Elliot",
                        lastName = "Tan",
                        profilePic = R.drawable.lioneers_elliot_tan,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                7.82F,
                                                2.88F,
                                                1.53F
                                        )
                        ),
                        age = 32,
                        number = "6",
                        position = "G"
                )
        var yun_hao =
                Player(
                        firstName = "Yun-hao",
                        lastName = "Chu",
                        profilePic = R.drawable.lioneers_yun_hao_chu,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                5.44F,
                                                5F,
                                                1.33F
                                        )
                        ),
                        number = "8",
                        position = "F"
                )
        var hao_tien =
                Player(
                        firstName = "Hao",
                        lastName = "Tien",
                        profilePic = R.drawable.lioneers_hao_tien,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                4.84F,
                                                2.74F,
                                                3.16F
                                        )
                        ),
                        age = 22,
                        number = "9",
                        position = "G"
                )
        var shun_yi =
                Player(
                        firstName = "Shun-yi",
                        lastName = "Hsiao",
                        profilePic = R.drawable.lioneers_shun_yi_hsiao,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                4.33F,
                                                1.71F,
                                                0.81F
                                        )
                        ),
                        age = 28,
                        number = "11",
                        position = "F"
                )
        var shao_chieh =
                Player(
                        firstName = "Shao-chieh",
                        lastName = "Kuo",
                        profilePic = R.drawable.lioneers_shao_chieh_kuo,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                2.29F,
                                                0.86F,
                                                0.29F
                                        )
                        ),
                        age = 30,
                        number = "24",
                        position = "F"
                )
        var tai_hao =
                Player(
                        firstName = "Tai-hao",
                        lastName = "Wu",
                        profilePic = R.drawable.lioneers_tai_hao_wu,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                2.67F,
                                                1.89F,
                                                1.17F
                                        )
                        ),
                        age = 35,
                        number = "54",
                        position = "F"
                )

        var calvin_chieng =
                Player(
                        firstName = "Li-Huan",
                        lastName = "Chieng",
                        profilePic = R.drawable.dreamers_li_huan_chieng,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                13.25F,
                                                4.13F,
                                                2.29F
                                        )
                        ),
                        age = 32,
                        number = "7",
                        position = "F"
                )
        var chun_chi_lin =
                Player(
                        firstName = "Chun-chi",
                        lastName = "Lin",
                        profilePic = R.drawable.dreamers_chun_chi_lin,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                9.9F,
                                                1.6F,
                                                1.5F
                                        )
                        ),
                        age = 22,
                        number = "11",
                        position = "G"
                )
        var derek_Lee =
                Player(
                        firstName = "Te-wei",
                        lastName = "Lee",
                        profilePic = R.drawable.dreamers_te_wei_lee,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                8.5F,
                                                8.27F,
                                                1.5F
                                        )
                        ),
                        age = 29,
                        number = "26",
                        position = "C"
                )
        var kenneth =
                Player(
                        firstName = "Kenneth",
                        lastName = "Chen",
                        profilePic = R.drawable.dreamers_kenneth_chen,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                6.62F,
                                                3.14F,
                                                2F
                                        )
                        ),
                        age = 25,
                        number = "23",
                        position = "F"
                )

        var jerry_chen =
                Player(
                        firstName = "Yu-wei",
                        lastName = "Chen",
                        profilePic = R.drawable.steelers_yu_wei_chen,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                11.14F,
                                                2.71F,
                                                2.29F
                                        )
                        ),
                        age = 21,
                        number = "4",
                        position = "G"
                )
        var tucker =
                Player(
                        firstName = "Anthony",
                        lastName = "Tucker",
                        profilePic = R.drawable.steelers_anthony_tucker,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                27.88F,
                                                8.12F,
                                                5.59F
                                        )
                        ),
                        age = 31,
                        number = "1",
                        position = "G"
                )
        var cheng_ju_lu =
                Player(
                        firstName = "Cheng-ju",
                        lastName = "Lu",
                        profilePic = R.drawable.steelers_cheng_ju_lu,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                15.71F,
                                                2.86F,
                                                2.29F
                                        )
                        ),
                        age = 34,
                        number = "13",
                        position = "F"
                )
        var po_chih_wang =
                Player(
                        firstName = "Po-chih",
                        lastName = "Wang",
                        profilePic = R.drawable.steelers_po_chih_wang,
                        stats =
                        mutableMapOf(
                                Date(2021, 1, 2)
                                        to
                                        PlayerStats(
                                                2F,
                                                1.4F,
                                                0.13F
                                        )
                        ),
                        age = 24,
                        number = "10",
                        position = "C"
                )

        var Braves =
                Team(
                        name = "Braves",
                        location = "Taipei",
                        profilePic = R.drawable.braves,
                        totalRecord = "5 - 5",
                        homeRecord = "4 - 1",
                        awayRecord = "1 - 4",
                        ranking = "4th",
                        foundingDate = Date(2001, 1, 1),
                        arena = "Hoping",
                        last10 = "5 - 5",
                        streak = "W3",
                        playerList =
                        arrayListOf(
                                chih_chieh_lin,
                                hsiang_chun_tseng,
                                michael_sigletary,
                                shu_wei_lin
                        )
                )
        var Kings =
                Team(
                        name = "Kings",
                        location = "New Taipei",
                        profilePic = R.drawable.kings,
                        totalRecord = "5 - 4",
                        homeRecord = "5 - 0",
                        awayRecord = "1 - 4",
                        ranking = "3rd",
                        foundingDate = Date(2021, 1, 1),
                        arena = "HsinChung",
                        last10 = "5 - 5",
                        streak = "W3",
                        playerList =
                        arrayListOf(
                                q_davis,
                                thomas_welsh,
                                chun_nan_chen,
                                hsing_chih_yang
                        )
                )
        var Pilots =
                Team(
                        name = "Pilots",
                        location = "Taoyuan",
                        profilePic = R.drawable.pilots,
                        totalRecord = "3 - 4",
                        homeRecord = "2 - 2",
                        awayRecord = "1 - 2",
                        ranking = "5th",
                        foundingDate = Date(1999, 1, 1),
                        arena = "Taoyuan",
                        last10 = "5 - 5",
                        streak = "W3",
                        playerList =
                        arrayListOf(
                                d_roboson,
                                chuh_hsiang_lu,
                                jordan_tolbert,
                                chih_yao_shih
                        )
                )
        var Lioneers =
                Team(
                        name = "Lioneers",
                        location = "Hsinchu",
                        profilePic = R.drawable.lioneers,
                        totalRecord = "5 - 4",
                        homeRecord = "2 - 2",
                        awayRecord = "3 - 2",
                        ranking = "2nd",
                        foundingDate = Date(2020, 1, 1),
                        arena = "ChuPei",
                        last10 = "5 - 5",
                        streak = "W3",
                        playerList =
                        arrayListOf(
                                kuo_hao_kao,
                                nick_faust,
                                b_dawson,
                                even_lee,
                                ming_yi,
                                bhullar,
                                yi_huei,
                                leon_sung,
                                elliot,
                                yun_hao,
                                hao_tien,
                                shun_yi,
                                shao_chieh,
                                tai_hao
                        )
                )

        var Dreamers =
                Team(
                        name = "Dreamers",
                        location = "Changhua",
                        profilePic = R.drawable.dreamers,
                        totalRecord = "5 - 3",
                        homeRecord = "4 - 1",
                        awayRecord = "1 - 2",
                        ranking = "1st",
                        foundingDate = Date(2005, 1, 1),
                        arena = "ChungHau",
                        last10 = "5 - 5",
                        streak = "L3",
                        playerList =
                        arrayListOf(
                                calvin_chieng,
                                chun_chi_lin,
                                derek_Lee,
                                kenneth
                        )
                )
        var Steelers =
                Team(
                        name = "Steelers",
                        location = "Kaoshung",
                        profilePic = R.drawable.steelers,
                        totalRecord = "2 - 5",
                        homeRecord = "1 - 1",
                        awayRecord = "1 - 4",
                        ranking = "6th",
                        foundingDate = Date(2021, 1, 1),
                        arena = "KH",
                        last10 = "5 - 5",
                        streak = "L3",
                        playerList =
                        arrayListOf(
                                jerry_chen,
                                tucker,
                                cheng_ju_lu,
                                po_chih_wang
                        )
                )

        val fakeGuestPlayerStats1 = mutableMapOf<Player, PlayerStats>(
                q_davis to PlayerStats(11F, 4F, 1F, steals = 0F, blocks = 1F),
                hsing_chih_yang to PlayerStats(4F, 4F, 1F, steals = 0F, blocks = 1F),
                thomas_welsh to PlayerStats(22F, 21F, 4F, steals = 2F, blocks = 4F),
                chun_nan_chen to PlayerStats(0F, 0F, 0F, steals = 0F, blocks = 0F))
        val fakeHostPlayerStats1 = mutableMapOf<Player, PlayerStats>(
                kuo_hao_kao to PlayerStats(13F, 8F, 4F, steals = 5F, blocks = 0F),
                ming_yi to PlayerStats(3F, 1F, 1F, steals = 1F, blocks = 0F),
                nick_faust to PlayerStats(33F, 11F, 1F, steals = 2F, blocks = 3F),
                bhullar to PlayerStats(19F, 22F, 3F, steals = 1F, blocks = 6F))

        val fakeGuestPlayerStats2 = mutableMapOf<Player, PlayerStats>(
                chih_chieh_lin to PlayerStats(11F, 4F, 1F, steals = 0F, blocks = 1F),
                hsiang_chun_tseng to PlayerStats(4F, 4F, 1F, steals = 0F, blocks = 1F),
                michael_sigletary to PlayerStats(22F, 21F, 4F, steals = 2F, blocks = 4F),
                shu_wei_lin to PlayerStats(0F, 0F, 0F, steals = 0F, blocks = 0F))
        val fakeHostPlayerStats2 = mutableMapOf<Player, PlayerStats>(
                d_roboson to PlayerStats(13F, 8F, 4F, steals = 5F, blocks = 0F),
                chuh_hsiang_lu to PlayerStats(3F, 1F, 1F, steals = 1F, blocks = 0F),
                jordan_tolbert to PlayerStats(33F, 11F, 1F, steals = 2F, blocks = 3F),
                chih_yao_shih to PlayerStats(19F, 22F, 3F, steals = 1F, blocks = 6F))

        val fakeGuestPlayerStats3 = mutableMapOf<Player, PlayerStats>(
                jerry_chen to PlayerStats(11F, 4F, 1F, steals = 0F, blocks = 1F),
                tucker to PlayerStats(4F, 4F, 1F, steals = 0F, blocks = 1F),
                cheng_ju_lu to PlayerStats(22F, 21F, 4F, steals = 2F, blocks = 4F),
                po_chih_wang to PlayerStats(0F, 0F, 0F, steals = 0F, blocks = 0F))
        val fakeHostPlayerStats3 = mutableMapOf<Player, PlayerStats>(
                calvin_chieng to PlayerStats(13F, 8F, 4F, steals = 5F, blocks = 0F),
                chun_chi_lin to PlayerStats(3F, 1F, 1F, steals = 1F, blocks = 0F),
                derek_Lee to PlayerStats(33F, 11F, 1F, steals = 2F, blocks = 3F),
                kenneth to PlayerStats(19F, 22F, 3F, steals = 1F, blocks = 6F))

        val game1 = Game(
                GuestTeam = Kings, HostTeam = Lioneers,
                GuestPlayerStats = fakeGuestPlayerStats1,
                HostPlayerStats = fakeHostPlayerStats1,
                status = GameStatus.INGAME, quarter = "1st", remainingTime = "4:17")
        val game2 = Game(
                GuestTeam = Braves, HostTeam = Pilots,
                GuestPlayerStats = fakeGuestPlayerStats2,
                HostPlayerStats = fakeHostPlayerStats2,
                status = GameStatus.NOT_YET_START, startingTime = Date(2022, 1, 1, 19, 0))
        val game3 = Game(
                GuestTeam = Steelers, HostTeam = Dreamers,
                GuestPlayerStats = fakeGuestPlayerStats3,
                HostPlayerStats = fakeHostPlayerStats3,
                status = GameStatus.END)

    }
}
