package com.example.gamechangermobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.PlayerStats
import com.example.gamechangermobile.models.Team
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
                FirstName = "chih-chieh",
                LastName = "Lin",
                ProfilePic = R.drawable.braves_chih_chieh_lin,
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
                FirstName = "Hsiang-chun",
                LastName = "Tseng",
                ProfilePic = R.drawable.braves_hsiang_chun_tseng,
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
                FirstName = "Michael",
                LastName = "Singletary",
                ProfilePic = R.drawable.braves_michael_singletary,
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
                FirstName = "Shu_wei",
                LastName = "Lin",
                ProfilePic = R.drawable.braves_shu_wei_lin,
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
                FirstName = "Q",
                LastName = "Davis",
                ProfilePic = R.drawable.kings_q_davis,
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
                FirstName = "Thomas",
                LastName = "Welsh",
                ProfilePic = R.drawable.kings_thomas_welsh,
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
                FirstName = "Chun-nan",
                LastName = "Chen",
                ProfilePic = R.drawable.kings_chun_nan_chen,
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
                FirstName = "Hsing-chih",
                LastName = "Yang",
                ProfilePic = R.drawable.kings_hsing_chih_yang,
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
                FirstName = "Devin",
                LastName = "Roboson",
                ProfilePic = R.drawable.pilots_devin_robinson,
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
                FirstName = "Chun-hsiang",
                LastName = "Lu",
                ProfilePic = R.drawable.pilots_chun_hsiang_lu,
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
                FirstName = "Chun-nan",
                LastName = "Chen",
                ProfilePic = R.drawable.pilots_jordan_tolbert,
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
                FirstName = "Chin-Yao",
                LastName = "Shih",
                ProfilePic = R.drawable.pilots_chin_yao_shih,
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
                FirstName = "Oscar",
                LastName = "Kao",
                ProfilePic = R.drawable.lioneers_kuo_hao_kao,
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
                FirstName = "Nicholas",
                LastName = "Faust",
                ProfilePic = R.drawable.lioneers_nicholas_faust,
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
                FirstName = "Branden",
                LastName = "Dawson",
                ProfilePic = R.drawable.lioneers_branden_dawson,
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
                FirstName = "Chia-jul",
                LastName = "Lee",
                ProfilePic = R.drawable.lioneers_chia_jul_lee,
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
                FirstName = "Ming-yi",
                LastName = "Lin",
                ProfilePic = R.drawable.lioneers_ming_yi_lin,
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
                FirstName = "Singh",
                LastName = "Bhullar",
                ProfilePic = R.drawable.lioneers_simbhullar,
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
                FirstName = "Yi-huei",
                LastName = "Lin",
                ProfilePic = R.drawable.lioneers_yi_huei_lin,
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
                FirstName = "Yu-hsuan",
                LastName = "Sung",
                ProfilePic = R.drawable.lioneers_yu_hsuan_sung,
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
                FirstName = "Elliot",
                LastName = "Tan",
                ProfilePic = R.drawable.lioneers_elliot_tan,
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
                FirstName = "Yun-hao",
                LastName = "Chu",
                ProfilePic = R.drawable.lioneers_yun_hao_chu,
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
                FirstName = "Hao",
                LastName = "Tien",
                ProfilePic = R.drawable.lioneers_hao_tien,
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
                FirstName = "Shun-yi",
                LastName = "Hsiao",
                ProfilePic = R.drawable.lioneers_shun_yi_hsiao,
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
                FirstName = "Shao-chieh",
                LastName = "Kuo",
                ProfilePic = R.drawable.lioneers_shao_chieh_kuo,
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
                FirstName = "Tai-hao",
                LastName = "Wu",
                ProfilePic = R.drawable.lioneers_tai_hao_wu,
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
                FirstName = "Li-Huan",
                LastName = "Chieng",
                ProfilePic = R.drawable.dreamers_li_huan_chieng,
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
                FirstName = "Chun-chi",
                LastName = "Lin",
                ProfilePic = R.drawable.dreamers_chun_chi_lin,
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
                FirstName = "Te-wei",
                LastName = "Lee",
                ProfilePic = R.drawable.dreamers_te_wei_lee,
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
                FirstName = "Kenneth",
                LastName = "Chen",
                ProfilePic = R.drawable.dreamers_kenneth_chen,
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
                FirstName = "Yu-wei",
                LastName = "Chen",
                ProfilePic = R.drawable.steelers_yu_wei_chen,
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
                FirstName = "Anthony",
                LastName = "Tucker",
                ProfilePic = R.drawable.steelers_anthony_tucker,
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
                FirstName = "Cheng-ju",
                LastName = "Lu",
                ProfilePic = R.drawable.steelers_cheng_ju_lu,
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
                FirstName = "Po-chih",
                LastName = "Wang",
                ProfilePic = R.drawable.steelers_po_chih_wang,
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
                Name = "Braves",
                Location = "Taipei",
                ProfilePic = R.drawable.braves,
                total_record = "5 - 5",
                home_record = "4 - 1",
                away_record = "1 - 4",
                ranking = "4th",
                founding_date = "2001",
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
                Name = "Kings",
                Location = "New Taipei",
                ProfilePic = R.drawable.kings,
                total_record = "5 - 4",
                home_record = "5 - 0",
                away_record = "1 - 4",
                ranking = "3rd",
                founding_date = "2021",
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
                Name = "Pilots",
                Location = "Taoyuan",
                ProfilePic = R.drawable.pilots,
                total_record = "3 - 4",
                home_record = "2 - 2",
                away_record = "1 - 2",
                ranking = "5th",
                founding_date = "1999",
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
                Name = "Lioneers",
                Location = "Hsinchu",
                ProfilePic = R.drawable.lioneers,
                total_record = "5 - 4",
                home_record = "2 - 2",
                away_record = "3 - 2",
                ranking = "2nd",
                founding_date = "2020",
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
                Name = "Dreamers",
                Location = "Changhua",
                ProfilePic = R.drawable.dreamers,
                total_record = "5 - 3",
                home_record = "4 - 1",
                away_record = "1 - 2",
                ranking = "1st",
                founding_date = "2005",
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
                Name = "Steelers",
                Location = "Kaoshung",
                ProfilePic = R.drawable.steelers,
                total_record = "2 - 5",
                home_record = "1 - 1",
                away_record = "1 - 4",
                ranking = "6th",
                founding_date = "2021",
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
    }
}
