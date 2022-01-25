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
        //        debugButton.setOnClickListener {
        //            val intent = Intent(this, PlayerActivity::class.java)
        //            val player = Player("Oscar", "Kao", R.drawable.lioneers_kuo_hao_kao)
        //            player._pts = 10.2F
        //            player._reb = 5.4F
        //            player._ast = 5.6F
        //            player.position = "PG"
        //            player.number = 4
        //            player.team = Team("Lioneers","Hsinchu", R.drawable.lioneers)
        //            intent.putExtra("selected_player", player)
        //            startActivity(intent)
        //        }

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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
                    Date(2021, 1, 2)
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
                mapOf(
                    Date(2021, 1, 2)
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
                mapOf(
                    Date(2021, 1, 2)
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
                mapOf(
                    Date(2021, 1, 2)
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
                    Date(2021, 1, 2)
                            to
                            PlayerStats(
                                27.17F,
                                8.5F,
                                2.83F
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                mapOf(
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
                foundingDate = Date(2001, 1,1),
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
        val fakeGameStats1 = GameStats(points = 102F, rebounds = 35F, offensiveRebounds = 3F, defensiveRebounds = 32F, assists = 27F, fieldGoal = 38F, fieldGoalAttempt = 76F, fieldGoalPercentage = 50F, fieldGoal3pt = 11F, fieldGoalAttempt3pt = 28F, fieldGoalPercentage3pt = 39.3F, freeThrow = 15F, freeThrowAttempt = 22F, freeThrowAttemptPercentage = 68.2F, steals = 10F, blocks = 2F, turnovers = 12F, timeoutRemaining = 1, fouls = 26F)
        val fakeGameStats2 = GameStats(points = 91F, rebounds = 48F, offensiveRebounds = 14F, defensiveRebounds = 34F, assists = 21F, fieldGoal = 29F, fieldGoalAttempt = 77F, fieldGoalPercentage = 37.7F, fieldGoal3pt = 9F, fieldGoalAttempt3pt = 38F, fieldGoalPercentage3pt = 23.7F, freeThrow = 24F, freeThrowAttempt = 36F, freeThrowAttemptPercentage = 66.7F, steals = 6F, blocks = 5F, turnovers = 15F, timeoutRemaining = 0, fouls = 21F)
        val fakeHostPlayerStats = mutableMapOf<Player, PlayerStats>( q_davis to PlayerStats(10F, 10F, 10F), hsing_chih_yang to PlayerStats(9F, 9F, 9.1F), thomas_welsh to PlayerStats(13F, 22.1F, 12F), chun_nan_chen to PlayerStats(10F,1F, 1F))
        val fakeGuestPlayerStats = mutableMapOf<Player, PlayerStats>( kuo_hao_kao to PlayerStats(1F, 1F, 1F), ming_yi to PlayerStats(9F, 1F,0.4F), nick_faust to PlayerStats(13F, 0.1F, 0.4F), bhullar to PlayerStats(10F, 2F, 3F))
        val game1 = Game(GuestTeam = Kings, HostTeam = Lioneers, GuestPlayerStats = fakeGuestPlayerStats, HostPlayerStats = fakeHostPlayerStats, status = GameStatus.INGAME, quarter = "1st", remainingTime = "4:17")
        val game2 = Game(GuestTeam = Braves,HostTeam = Pilots, GuestPlayerStats = fakeGuestPlayerStats, HostPlayerStats = fakeHostPlayerStats, status = GameStatus.NOT_YET_START, startingTime = Date(2022, 1,1,19, 0))
        val game3 = Game(GuestTeam = Steelers, HostTeam = Dreamers, GuestPlayerStats = fakeGuestPlayerStats, HostPlayerStats = fakeHostPlayerStats, status = GameStatus.END)

    }
}
