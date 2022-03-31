package com.example.gamechangermobile

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.UrlRequestCallback
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import org.json.JSONObject
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()
        val gamesFrag = GameFragment()
        val statsFrag = StatsFragment()
        val userFrag = UserFragment()
        replaceFragment(gamesFrag)

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
                R.id.user_page -> {
                    replaceFragment(userFrag)
                    true
                }
                else -> false
            }
        }

        getTeamByName(TeamName.BRAVES)?.gamesIdList?.add(GameID(2021002))
        getTeamByName(TeamName.BRAVES)?.gamesIdList?.add(GameID(2021005))
        getTeamByName(TeamName.BRAVES)?.gamesIdList?.add(GameID(2021009))

        getTeamByName(TeamName.KINGS)?.gamesIdList?.add(GameID(2021001))
        getTeamByName(TeamName.KINGS)?.gamesIdList?.add(GameID(2021004))
        getTeamByName(TeamName.KINGS)?.gamesIdList?.add(GameID(2021008))
        getTeamByName(TeamName.KINGS)?.gamesIdList?.add(GameID(2021010))

        getTeamByName(TeamName.PILOTS)?.gamesIdList?.add(GameID(2021002))
        getTeamByName(TeamName.PILOTS)?.gamesIdList?.add(GameID(2021004))
        getTeamByName(TeamName.PILOTS)?.gamesIdList?.add(GameID(2021006))

        getTeamByName(TeamName.LIONEERS)?.gamesIdList?.add(GameID(2021001))
        getTeamByName(TeamName.LIONEERS)?.gamesIdList?.add(GameID(2021005))
        getTeamByName(TeamName.LIONEERS)?.gamesIdList?.add(GameID(2021007))
        getTeamByName(TeamName.LIONEERS)?.gamesIdList?.add(GameID(2021009))
        getTeamByName(TeamName.LIONEERS)?.gamesIdList?.add(GameID(2021010))

        getTeamByName(TeamName.DREAMERS)?.gamesIdList?.add(GameID(2021003))
        getTeamByName(TeamName.DREAMERS)?.gamesIdList?.add(GameID(2021007))

        getTeamByName(TeamName.STEELERS)?.gamesIdList?.add(GameID(2021003))
        getTeamByName(TeamName.STEELERS)?.gamesIdList?.add(GameID(2021006))
        getTeamByName(TeamName.STEELERS)?.gamesIdList?.add(GameID(2021008))

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragView, fragment)
            commit()
        }
    }

    companion object {
        var currentUser = User()

        var chih_chieh_lin =
            Player(
                firstName = "chih-chieh",
                lastName = "Lin",
                profilePic = R.drawable.braves_chih_chieh_lin,
                stats =
                mutableMapOf(
                    GameID(2001001)
                            to PlayerStats(
                        9.5F,
                        3.33F,
                        1.83F
                    )
                ),
                age = 38,
                number = "12",
                position = "F",
                teamId = getTeamIdByName(TeamName.BRAVES)
            )
        var hsiang_chun_tseng =
            Player(
                firstName = "Hsiang-chun",
                lastName = "Tseng",
                profilePic = R.drawable.braves_hsiang_chun_tseng,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                3.38F,
                                3.25F,
                                0.5F
                            )
                ),
                age = 22,
                number = "21",
                position = "C",
                teamId = getTeamIdByName(TeamName.BRAVES)
            )
        var michael_sigletary =
            Player(
                firstName = "Michael",
                lastName = "Singletary",
                profilePic = R.drawable.braves_michael_singletary,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                12.8F,
                                3.4F,
                                3.1F
                            )
                ),
                age = 28,
                number = "17",
                position = "F",
                teamId = getTeamIdByName(TeamName.BRAVES)
            )
        var shu_wei_lin =
            Player(
                firstName = "Shu_wei",
                lastName = "Lin",
                profilePic = R.drawable.braves_shu_wei_lin,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                12.13F,
                                2.88F,
                                4.13F
                            )
                ),
                age = 28,
                number = "1",
                position = "G",
                teamId = getTeamIdByName(TeamName.BRAVES)
            )

        var q_davis =
            Player(
                firstName = "Q",
                lastName = "Davis",
                profilePic = R.drawable.kings_q_davis,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                5F,
                                11F,
                                1F
                            )
                ),
                number = "50",
                position = "C",
                teamId = getTeamIdByName(TeamName.KINGS)

            )
        var thomas_welsh =
            Player(
                firstName = "Thomas",
                lastName = "Welsh",
                profilePic = R.drawable.kings_thomas_welsh,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                18.57F,
                                19.57F,
                                2.71F
                            )
                ),
                age = 24,
                number = "40",
                position = "C",
                teamId = getTeamIdByName(TeamName.KINGS)
            )
        var chun_nan_chen =
            Player(
                firstName = "Chun-nan",
                lastName = "Chen",
                profilePic = R.drawable.kings_chun_nan_chen,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                3.2F,
                                1.8F,
                                0.8F
                            )
                ),
                age = 20,
                number = "3",
                position = "F",
                teamId = getTeamIdByName(TeamName.KINGS)
            )
        var hsing_chih_yang =
            Player(
                firstName = "Hsing-chih",
                lastName = "Yang",
                profilePic = R.drawable.kings_hsing_chih_yang,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                4.11F,
                                3.22F,
                                0.44F
                            )
                ),
                age = 27,
                number = "33",
                position = "F",
                teamId = getTeamIdByName(TeamName.KINGS)
            )

        var d_roboson =
            Player(
                firstName = "Devin",
                lastName = "Roboson",
                profilePic = R.drawable.pilots_devin_robinson,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                24.8F,
                                14.6F,
                                2.2F
                            )
                ),
                age = 25,
                number = "0",
                position = "F",
                teamId = getTeamIdByName(TeamName.PILOTS)
            )
        var chuh_hsiang_lu =
            Player(
                firstName = "Chun-hsiang",
                lastName = "Lu",
                profilePic = R.drawable.pilots_chun_hsiang_lu,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                15.33F,
                                5F,
                                0.33F
                            )
                ),
                number = "69",
                position = "G",
                teamId = getTeamIdByName(TeamName.PILOTS)
            )
        var jordan_tolbert =
            Player(
                firstName = "Jordan",
                lastName = "Tolbert",
                profilePic = R.drawable.pilots_jordan_tolbert,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                19.17F,
                                14.17F,
                                4.33F
                            )
                ),
                age = 28,
                number = "1",
                position = "F",
                teamId = getTeamIdByName(TeamName.PILOTS)
            )
        var chih_yao_shih =
            Player(
                firstName = "Chin-Yao",
                lastName = "Shih",
                profilePic = R.drawable.pilots_chin_yao_shih,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                14.17F,
                                3.25F,
                                3.63F
                            )
                ),
                age = 30,
                number = "28",
                position = "F",
                teamId = getTeamIdByName(TeamName.PILOTS)
            )

        var kuo_hao_kao =
            Player(
                firstName = "Oscar",
                lastName = "Kao",
                profilePic = R.drawable.lioneers_kuo_hao_kao,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                7.17F,
                                6.17F,
                                3F
                            )
                ),
                number = "4",
                position = "G",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var nick_faust =
            Player(
                firstName = "Nicholas",
                lastName = "Faust",
                profilePic = R.drawable.lioneers_nicholas_faust,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                27F,
                                8F,
                                3F
                            ),
                    GameID(2021002)
                            to
                            PlayerStats(
                                45F,
                                12F,
                                2F
                            )
                ),
                age = 27,
                number = "7",
                position = "F",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var b_dawson =
            Player(
                firstName = "Branden",
                lastName = "Dawson",
                profilePic = R.drawable.lioneers_branden_dawson,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                14.33F,
                                14F,
                                1.67F
                            )
                ),
                number = "22",
                position = "F",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var even_lee =
            Player(
                firstName = "Chia-jul",
                lastName = "Lee",
                profilePic = R.drawable.lioneers_chia_jul_lee,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                6.89F,
                                4.22F,
                                2.67F
                            )
                ),
                age = 26,
                number = "12",
                position = "F",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var ming_yi =
            Player(
                firstName = "Ming-yi",
                lastName = "Lin",
                profilePic = R.drawable.lioneers_ming_yi_lin,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                3.94F,
                                2.71F,
                                2.88F
                            )
                ),
                age = 24,
                number = "3",
                position = "G",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var bhullar =
            Player(
                firstName = "Singh",
                lastName = "Bhullar",
                profilePic = R.drawable.lioneers_simbhullar,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                26.33F,
                                21.89F,
                                2.44F
                            )
                ),
                age = 28,
                number = "35",
                position = "C",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var yi_huei =
            Player(
                firstName = "Yi-huei",
                lastName = "Lin",
                profilePic = R.drawable.lioneers_yi_huei_lin,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                11.56F,
                                4.22F,
                                3.4F
                            )
                ),
                age = 34,
                number = "36",
                position = "F",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var leon_sung =
            Player(
                firstName = "Yu-hsuan",
                lastName = "Sung",
                profilePic = R.drawable.lioneers_yu_hsuan_sung,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                5.2F,
                                0.8F,
                                1F
                            )
                ),
                age = 31,
                number = "5",
                position = "F",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var elliot =
            Player(
                firstName = "Elliot",
                lastName = "Tan",
                profilePic = R.drawable.lioneers_elliot_tan,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                7.82F,
                                2.88F,
                                1.53F
                            )
                ),
                age = 32,
                number = "6",
                position = "G",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var yun_hao =
            Player(
                firstName = "Yun-hao",
                lastName = "Chu",
                profilePic = R.drawable.lioneers_yun_hao_chu,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                5.44F,
                                5F,
                                1.33F
                            )
                ),
                number = "8",
                position = "F",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var hao_tien =
            Player(
                firstName = "Hao",
                lastName = "Tien",
                profilePic = R.drawable.lioneers_hao_tien,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                4.84F,
                                2.74F,
                                3.16F
                            )
                ),
                age = 22,
                number = "9",
                position = "G",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var shun_yi =
            Player(
                firstName = "Shun-yi",
                lastName = "Hsiao",
                profilePic = R.drawable.lioneers_shun_yi_hsiao,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                4.33F,
                                1.71F,
                                0.81F
                            )
                ),
                age = 28,
                number = "11",
                position = "F",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var shao_chieh =
            Player(
                firstName = "Shao-chieh",
                lastName = "Kuo",
                profilePic = R.drawable.lioneers_shao_chieh_kuo,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                2.29F,
                                0.86F,
                                0.29F
                            )
                ),
                age = 30,
                number = "24",
                position = "F",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )
        var tai_hao =
            Player(
                firstName = "Tai-hao",
                lastName = "Wu",
                profilePic = R.drawable.lioneers_tai_hao_wu,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                2.67F,
                                1.89F,
                                1.17F
                            )
                ),
                age = 35,
                number = "54",
                position = "F",
                teamId = getTeamIdByName(TeamName.LIONEERS)
            )

        var calvin_chieng =
            Player(
                firstName = "Li-Huan",
                lastName = "Chieng",
                profilePic = R.drawable.dreamers_li_huan_chieng,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                13.25F,
                                4.13F,
                                2.29F
                            ),
                    GameID(2021002)
                            to
                            PlayerStats(
                                13.25F,
                                4.13F,
                                2.29F
                            )
                ),
                age = 32,
                number = "7",
                position = "F",
                teamId = getTeamIdByName(TeamName.DREAMERS)
            )
        var chun_chi_lin =
            Player(
                firstName = "Chun-chi",
                lastName = "Lin",
                profilePic = R.drawable.dreamers_chun_chi_lin,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                9.9F,
                                1.6F,
                                1.5F
                            )
                ),
                age = 22,
                number = "11",
                position = "G",
                teamId = getTeamIdByName(TeamName.DREAMERS)
            )
        var derek_Lee =
            Player(
                firstName = "Te-wei",
                lastName = "Lee",
                profilePic = R.drawable.dreamers_te_wei_lee,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                8.5F,
                                8.27F,
                                1.5F
                            )
                ),
                age = 29,
                number = "26",
                position = "C",
                teamId = getTeamIdByName(TeamName.DREAMERS)
            )
        var kenneth =
            Player(
                firstName = "Kenneth",
                lastName = "Chen",
                profilePic = R.drawable.dreamers_kenneth_chen,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                6.62F,
                                3.14F,
                                2F
                            )
                ),
                age = 25,
                number = "23",
                position = "F",
                teamId = getTeamIdByName(TeamName.DREAMERS)
            )

        var jerry_chen =
            Player(
                firstName = "Yu-wei",
                lastName = "Chen",
                profilePic = R.drawable.steelers_yu_wei_chen,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                11.14F,
                                2.71F,
                                2.29F
                            )
                ),
                age = 21,
                number = "4",
                position = "G",
                teamId = getTeamIdByName(TeamName.STEELERS)
            )
        var tucker =
            Player(
                firstName = "Anthony",
                lastName = "Tucker",
                profilePic = R.drawable.steelers_anthony_tucker,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                27.88F,
                                8.12F,
                                5.59F
                            )
                ),
                age = 31,
                number = "1",
                position = "G",
                teamId = getTeamIdByName(TeamName.STEELERS)
            )
        var cheng_ju_lu =
            Player(
                firstName = "Cheng-ju",
                lastName = "Lu",
                profilePic = R.drawable.steelers_cheng_ju_lu,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                15.71F,
                                2.86F,
                                2.29F
                            )
                ),
                age = 34,
                number = "13",
                position = "F",
                teamId = getTeamIdByName(TeamName.STEELERS)
            )
        var po_chih_wang =
            Player(
                firstName = "Po-chih",
                lastName = "Wang",
                profilePic = R.drawable.steelers_po_chih_wang,
                stats =
                mutableMapOf(
                    GameID(2021001)
                            to
                            PlayerStats(
                                2F,
                                1.4F,
                                0.13F
                            )
                ),
                age = 24,
                number = "10",
                position = "C",
                teamId = getTeamIdByName(TeamName.STEELERS)
            )
        var teams: ArrayList<Team> = arrayListOf(
            Team(
                teamId = getTeamIdByName(TeamName.BRAVES),
                name = "Braves",
                location = "Taipei",
                profilePic = R.drawable.braves,
                ranking = "4th",
                foundingDate = Date(2001 - 1900, 1 - 1, 1),
                arena = "Hoping",
                color = R.color.braves_color,
                playerList =
                arrayListOf(
                    chih_chieh_lin,
                    hsiang_chun_tseng,
                    michael_sigletary,
                    shu_wei_lin
                )
            ),
            Team(
                teamId = getTeamIdByName(TeamName.KINGS),
                name = "Kings",
                location = "New Taipei",
                profilePic = R.drawable.kings,
                ranking = "3rd",
                foundingDate = Date(2021 - 1900, 1 - 1, 1),
                arena = "HsinChung",
                color = R.color.kings_color,
                playerList =
                arrayListOf(
                    q_davis,
                    thomas_welsh,
                    chun_nan_chen,
                    hsing_chih_yang
                )
            ),
            Team(
                teamId = getTeamIdByName(TeamName.PILOTS),
                name = "Pilots",
                location = "Taoyuan",
                profilePic = R.drawable.pilots,
                ranking = "5th",
                foundingDate = Date(1999 - 1900, 1 - 1, 1),
                arena = "Taoyuan",
                color = R.color.pilots_color,
                playerList =
                arrayListOf(
                    d_roboson,
                    chuh_hsiang_lu,
                    jordan_tolbert,
                    chih_yao_shih
                )
            ),
            Team(
                teamId = getTeamIdByName(TeamName.LIONEERS),
                name = "Lioneers",
                location = "Hsinchu",
                profilePic = R.drawable.lioneers,
                ranking = "2nd",
                foundingDate = Date(2020 - 1900, 1 - 1, 1),
                arena = "ChuPei",
                color = R.color.lioneers_color,
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
            ),
            Team(
                teamId = getTeamIdByName(TeamName.DREAMERS),
                name = "Dreamers",
                location = "Changhua",
                profilePic = R.drawable.dreamers,
                ranking = "1st",
                foundingDate = Date(2005 - 1900, 1 - 1, 1),
                arena = "ChungHau",
                color = R.color.dreamers_color,
                playerList =
                arrayListOf(
                    calvin_chieng,
                    chun_chi_lin,
                    derek_Lee,
                    kenneth
                )
            ),
            Team(
                teamId = getTeamIdByName(TeamName.STEELERS),
                name = "Steelers",
                location = "Kaoshung",
                profilePic = R.drawable.steelers,
                ranking = "6th",
                foundingDate = Date(2021 - 1900, 1 - 1, 1),
                arena = "KH",
                color = R.color.steelers_color,
                playerList =
                arrayListOf(
                    jerry_chen,
                    tucker,
                    cheng_ju_lu,
                    po_chih_wang
                )
            )
        )
        private val fakeBravesStats1 = mutableMapOf<Player, PlayerStats>(
            chih_chieh_lin to PlayerStats(10F, 4F, 1F, steals = 0F, blocks = 1F),
            hsiang_chun_tseng to PlayerStats(4F, 4F, 1F, steals = 0F, blocks = 1F),
            michael_sigletary to PlayerStats(22F, 21F, 4F, steals = 2F, blocks = 4F),
            shu_wei_lin to PlayerStats(0F, 0F, 0F, steals = 0F, blocks = 0F)
        )
        private val fakeBravesStats2 = mutableMapOf<Player, PlayerStats>(
            chih_chieh_lin to PlayerStats(23F, 8F, 2F, steals = 2F, blocks = 0F),
            hsiang_chun_tseng to PlayerStats(3F, 11F, 1F, steals = 1F, blocks = 3F),
            michael_sigletary to PlayerStats(33F, 10F, 4F, steals = 4F, blocks = 2F),
            shu_wei_lin to PlayerStats(18F, 2F, 7F, steals = 2F, blocks = 0F)
        )
        private val fakeKingsStats1 = mutableMapOf<Player, PlayerStats>(
            q_davis to PlayerStats(11F, 4F, 1F, steals = 0F, blocks = 2F),
            hsing_chih_yang to PlayerStats(4F, 4F, 1F, steals = 0F, blocks = 1F),
            thomas_welsh to PlayerStats(23F, 21F, 4F, steals = 2F, blocks = 4F),
            chun_nan_chen to PlayerStats(0F, 0F, 0F, steals = 0F, blocks = 0F)
        )
        private val fakeKingsStats2 = mutableMapOf<Player, PlayerStats>(
            q_davis to PlayerStats(13F, 8F, 4F, steals = 5F, blocks = 0F),
            thomas_welsh to PlayerStats(29F, 20F, 6F, steals = 2F, blocks = 4F),
            chun_nan_chen to PlayerStats(3F, 1F, 1F, steals = 1F, blocks = 0F),
            hsing_chih_yang to PlayerStats(9F, 2F, 1F, steals = 1F, blocks = 1F)
        )
        private val fakePilotsStats1 = mutableMapOf<Player, PlayerStats>(
            d_roboson to PlayerStats(43F, 12F, 0F, steals = 3F, blocks = 2F),
            chuh_hsiang_lu to PlayerStats(13F, 1F, 1F, steals = 1F, blocks = 0F),
            jordan_tolbert to PlayerStats(17F, 13F, 1F, steals = 2F, blocks = 4F),
            chih_yao_shih to PlayerStats(4F, 2F, 0F, steals = 0F, blocks = 0F)
        )
        private val fakePilotsStats2 = mutableMapOf<Player, PlayerStats>(
            d_roboson to PlayerStats(38F, 7F, 1F, steals = 0F, blocks = 1F),
            chuh_hsiang_lu to PlayerStats(16F, 4F, 1F, steals = 2F, blocks = 0F),
            jordan_tolbert to PlayerStats(22F, 16F, 4F, steals = 2F, blocks = 3F),
            chih_yao_shih to PlayerStats(4F, 2F, 0F, steals = 0F, blocks = 0F)
        )
        private val fakeLioneersStats1 = mutableMapOf<Player, PlayerStats>(
            kuo_hao_kao to PlayerStats(13F, 8F, 4F, steals = 5F, blocks = 0F),
            ming_yi to PlayerStats(3F, 1F, 1F, steals = 1F, blocks = 0F),
            nick_faust to PlayerStats(33F, 11F, 1F, steals = 2F, blocks = 3F),
            bhullar to PlayerStats(29F, 22F, 3F, steals = 1F, blocks = 6F)
        )
        private val fakeLioneersStats2 = mutableMapOf<Player, PlayerStats>(
            kuo_hao_kao to PlayerStats(18F, 5F, 1F, steals = 3F, blocks = 1F),
            nick_faust to PlayerStats(4F, 8F, 1F, steals = 3F, blocks = 1F),
            b_dawson to PlayerStats(17F, 11F, 4F, steals = 4F, blocks = 4F),
            even_lee to PlayerStats(10F, 7F, 2F, steals = 0F, blocks = 2F)
        )
        private val fakeDreamersStats1 = mutableMapOf<Player, PlayerStats>(
            calvin_chieng to PlayerStats(13F, 8F, 1F, steals = 1F, blocks = 0F),
            chun_chi_lin to PlayerStats(23F, 1F, 4F, steals = 1F, blocks = 0F),
            derek_Lee to PlayerStats(27F, 11F, 1F, steals = 2F, blocks = 3F),
            kenneth to PlayerStats(19F, 8F, 3F, steals = 1F, blocks = 1F)
        )
        private val fakeSteelersStats1 = mutableMapOf<Player, PlayerStats>(
            jerry_chen to PlayerStats(11F, 4F, 10F, steals = 0F, blocks = 1F),
            tucker to PlayerStats(24F, 4F, 1F, steals = 0F, blocks = 0F),
            cheng_ju_lu to PlayerStats(22F, 5F, 4F, steals = 2F, blocks = 1F),
            po_chih_wang to PlayerStats(6F, 0F, 0F, steals = 0F, blocks = 2F)
        )


        val games = arrayListOf<Game>(
//            Game(
//                gameId = GameID(2021001),
//                guestTeam = getTeamIdByName(TeamName.KINGS),
//                hostTeam = getTeamIdByName(TeamName.LIONEERS),
//                date = Date(2022 - 1900, 1 - 1, 28, 13, 0),
//                guestPlayerStats = fakeKingsStats1,
//                hostPlayerStats = fakeLioneersStats1,
//                status = GameStatus.END,
//                highlightPhoto = R.drawable.game_kings_vs_lioneers,
//            ),
//            Game(
//                gameId = GameID(2021002),
//                guestTeam = getTeamIdByName(TeamName.BRAVES),
//                hostTeam = getTeamIdByName(TeamName.PILOTS),
//                date = Date(2022 - 1900, 1 - 1, 28, 19, 0),
//                guestPlayerStats = fakeBravesStats1,
//                hostPlayerStats = fakePilotsStats1,
//                status = GameStatus.END,
//                highlightPhoto = R.drawable.game_braves_vs_pilots,
//            ),
//            Game(
//                gameId = GameID(2021003),
//                guestTeam = getTeamIdByName(TeamName.STEELERS),
//                hostTeam = getTeamIdByName(TeamName.DREAMERS),
//                date = Date(2022 - 1900, 1 - 1, 28, 19, 0),
//                guestPlayerStats = fakeSteelersStats1,
//                hostPlayerStats = fakeDreamersStats1,
//                status = GameStatus.END,
//                highlightPhoto = R.drawable.game_steelers_vs_dreamers,
//            ),
//            Game(
//                gameId = GameID(2021004),
//                guestTeam = getTeamIdByName(TeamName.PILOTS),
//                hostTeam = getTeamIdByName(TeamName.KINGS),
//                date = Date(2022 - 1900, 1 - 1, 29, 13, 0),
//                guestPlayerStats = fakePilotsStats2,
//                hostPlayerStats = fakeKingsStats2,
//                status = GameStatus.END,
//                highlightPhoto = R.drawable.game_pilots_vs_kings,
//            ),
//            Game(
//                gameId = GameID(2021005),
//                guestTeam = getTeamIdByName(TeamName.LIONEERS),
//                hostTeam = getTeamIdByName(TeamName.BRAVES),
//                date = Date(2022 - 1900, 1 - 1, 29, 13, 0),
//                guestPlayerStats = fakeLioneersStats2,
//                hostPlayerStats = fakeBravesStats2,
//                status = GameStatus.INGAME,
//                highlightPhoto = R.drawable.game_braves_vs_lioneers,
//                quarter = "3rd",
//                remainingTime = "0:16"
//            ),
//            Game(
//                gameId = GameID(2021006),
//                guestTeam = getTeamIdByName(TeamName.PILOTS),
//                hostTeam = getTeamIdByName(TeamName.STEELERS),
//                date = Date(2022 - 1900, 1 - 1, 29, 19, 0),
//                guestPlayerStats = fakePilotsStats2,
//                hostPlayerStats = fakeSteelersStats1,
//                status = GameStatus.INGAME,
//                highlightPhoto = R.drawable.game_pilots_vs_steelers,
//                quarter = "1st",
//                remainingTime = "4:16"
//            ),
//            Game(
//                gameId = GameID(2021007),
//                guestTeam = getTeamIdByName(TeamName.LIONEERS),
//                hostTeam = getTeamIdByName(TeamName.DREAMERS),
//                date = Date(2022 - 1900, 1 - 1, 30, 12, 0),
////                guestPlayerStats = fakeLioneersStats1,
////                hostPlayerStats = fakeDreamersStats1,
//                status = GameStatus.NOT_YET_START,
//                highlightPhoto = R.drawable.game_lioneers_vs_dreamers,
//            ),
//            Game(
//                gameId = GameID(2021008),
//                guestTeam = getTeamIdByName(TeamName.KINGS),
//                hostTeam = getTeamIdByName(TeamName.STEELERS),
//                date = Date(2022 - 1900, 1 - 1, 30, 13, 0),
////                guestPlayerStats = fakeKingsStats1,
////                hostPlayerStats = fakeSteelersStats1,
//                status = GameStatus.NOT_YET_START,
//                highlightPhoto = R.drawable.game_kings_vs_steelers,
//            ),
//            Game(
//                gameId = GameID(2021009),
//                guestTeam = getTeamIdByName(TeamName.LIONEERS),
//                hostTeam = getTeamIdByName(TeamName.BRAVES),
//                date = Date(2022 - 1900, 1 - 1, 30, 15, 0),
////                guestPlayerStats = fakeLioneersStats1,
////                hostPlayerStats = fakeBravesStats2,
//                status = GameStatus.NOT_YET_START,
//                highlightPhoto = R.drawable.game_braves_vs_lioneers,
//            ),
//            Game(
//                gameId = GameID(2021010),
//                guestTeam = getTeamIdByName(TeamName.DREAMERS),
//                hostTeam = getTeamIdByName(TeamName.KINGS),
//                date = Date(2022 - 1900, 1 - 1, 30, 19, 0),
////                guestPlayerStats = fakeDreamersStats1,
////                hostPlayerStats = fakeKingsStats2,
//                status = GameStatus.NOT_YET_START,
//                highlightPhoto = R.drawable.game_kings_vs_dreamers
//            )
        )

    }
}
