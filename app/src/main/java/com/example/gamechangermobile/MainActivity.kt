package com.example.gamechangermobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.Team
import kotlinx.android.synthetic.main.activity_main.*

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
                        "chih-chieh",
                        "Lin",
                        R.drawable.braves_chih_chieh_lin,
                        9.5F,
                        1.83F,
                        3.33F,
                        age = 38,
                        number = 12,
                        position = "F"
                )
        var hsiang_chun_tseng =
                Player(
                        "Hsiang-chun",
                        "Tseng",
                        R.drawable.braves_hsiang_chun_tseng,
                        3.38F,
                        0.5F,
                        3.25F,
                        age = 22,
                        number = 21,
                        position = "C"
                )
        var michael_sigletary =
                Player(
                        "Michael",
                        "Singletary",
                        R.drawable.braves_michael_singletary,
                        12.8F,
                        3.1F,
                        3.4F,
                        age = 28,
                        number = 17,
                        position = "F"
                )
        var shu_wei_lin =
                Player(
                        "Shu_wei",
                        "Lin",
                        R.drawable.braves_shu_wei_lin,
                        12.13F,
                        4.13F,
                        2.88F,
                        age = 28,
                        number = 1,
                        position = "G"
                )

        var q_davis =
                Player(
                        "Q",
                        "Davis",
                        R.drawable.kings_q_davis,
                        5F,
                        1F,
                        11F,
                        age = 37,
                        number = 50,
                        position = "C"
                )
        var thomas_welsh =
                Player(
                        "Thomas",
                        "Welsh",
                        R.drawable.kings_thomas_welsh,
                        18.57F,
                        2.71F,
                        19.57F,
                        age = 24,
                        number = 40,
                        position = "C"
                )
        var chun_nan_chen =
                Player(
                        "Chun-nan",
                        "Chen",
                        R.drawable.kings_chun_nan_chen,
                        3.2F,
                        0.8F,
                        1.8F,
                        age = 20,
                        number = 3,
                        position = "F"
                )
        var hsing_chih_yang =
                Player(
                        "Hsing-chih",
                        "Yang",
                        R.drawable.kings_hsing_chih_yang,
                        4.11F,
                        0.44F,
                        3.22F,
                        age = 27,
                        number = 33,
                        position = "F"
                )

        var d_roboson =
                Player(
                        "Devin",
                        "Roboson",
                        R.drawable.pilots_devin_robinson,
                        24.8F,
                        2.2F,
                        14.6F,
                        age = 25,
                        number = 0,
                        position = "F"
                )
        var chuh_hsiang_lu =
                Player(
                        "Chun-hsiang",
                        "Lu",
                        R.drawable.pilots_chun_hsiang_lu,
                        15.33F,
                        0.33F,
                        5F,
                        age = 23,
                        number = 69,
                        position = "G"
                )
        var jordan_tolbert =
                Player(
                        "Chun-nan",
                        "Chen",
                        R.drawable.pilots_jordan_tolbert,
                        19.17F,
                        4.33F,
                        14.17F,
                        age = 28,
                        number = 1,
                        position = "F"
                )
        var chih_yao_shih =
                Player(
                        "Chin-Yao",
                        "Shih",
                        R.drawable.pilots_chin_yao_shih,
                        14.17F,
                        3.63F,
                        3.25F,
                        age = 30,
                        number = 28,
                        position = "F"
                )

        var kuo_hao_kao =
                Player(
                        "Oscar",
                        "Kao",
                        R.drawable.lioneers_kuo_hao_kao,
                        7.17F,
                        6.17F,
                        3F,
                        age = 22,
                        number = 4,
                        position = "G"
                )
        var nick_faust =
                Player(
                        "Nicholas",
                        "Faust",
                        R.drawable.lioneers_nicholas_faust,
                        27.17F,
                        2.83F,
                        8.5F,
                        age = 27,
                        number = 7,
                        position = "F"
                )
        var b_dawson =
                Player(
                        "Branden",
                        "Dawson",
                        R.drawable.lioneers_branden_dawson,
                        14.33F,
                        1.67F,
                        14F,
                        age = 27,
                        number = 22,
                        position = "F"
                )
        var even_lee =
                Player(
                        "Chia-jul",
                        "Lee",
                        R.drawable.lioneers_chia_jul_lee,
                        6.89F,
                        2.67F,
                        4.22F,
                        age = 26,
                        number = 12,
                        position = "F"
                )

        var calvin_chieng =
                Player(
                        "Li-Huan",
                        "Chieng",
                        R.drawable.dreamers_li_huan_chieng,
                        13.25F,
                        2.29F,
                        4.13F,
                        age = 32,
                        number = 7,
                        position = "F"
                )
        var chun_chi_lin =
                Player(
                        "Chun-chi",
                        "Lin",
                        R.drawable.dreamers_chun_chi_lin,
                        9.9F,
                        1.5F,
                        1.6F,
                        age = 22,
                        number = 11,
                        position = "G"
                )
        var derek_Lee =
                Player(
                        "Te-wei",
                        "Lee",
                        R.drawable.dreamers_te_wei_lee,
                        8.5F,
                        1.5F,
                        8.27F,
                        age = 29,
                        number = 26,
                        position = "C"
                )
        var kenneth =
                Player(
                        "Kenneth",
                        "Chen",
                        R.drawable.dreamers_kenneth_chen,
                        6.62F,
                        2F,
                        3.14F,
                        age = 25,
                        number = 23,
                        position = "F"
                )

        var jerry_chen =
                Player(
                        "Yu-wei",
                        "Chen",
                        R.drawable.steelers_yu_wei_chen,
                        11.14F,
                        2.29F,
                        2.71F,
                        age = 21,
                        number = 4,
                        position = "G"
                )
        var tucker =
                Player(
                        "Anthony",
                        "Tucker",
                        R.drawable.steelers_anthony_tucker,
                        27.88F,
                        5.59F,
                        8.12F,
                        age = 31,
                        number = 1,
                        position = "G"
                )
        var cheng_ju_lu =
                Player(
                        "Cheng-ju",
                        "Lu",
                        R.drawable.steelers_cheng_ju_lu,
                        15.71F,
                        2.29F,
                        2.86F,
                        age = 34,
                        number = 13,
                        position = "F"
                )
        var po_chih_wang =
                Player(
                        "Po-chih",
                        "Wang",
                        R.drawable.steelers_po_chih_wang,
                        2F,
                        0.13F,
                        1.4F,
                        age = 24,
                        number = 10,
                        position = "C"
                )

        var Braves =
                Team(
                        "Braves",
                        "Taipei",
                        R.drawable.braves,
                        "5 - 5",
                        "4th",
                        arrayListOf(
                                chih_chieh_lin,
                                hsiang_chun_tseng,
                                michael_sigletary,
                                shu_wei_lin
                        )
                )
        var Kings =
                Team(
                        "Kings",
                        "New Taipei",
                        R.drawable.kings,
                        "5 - 4",
                        "3rd",
                        arrayListOf(q_davis, thomas_welsh, chun_nan_chen, hsing_chih_yang)
                )
        var Pilots =
                Team(
                        "Pilots",
                        "Taoyuan",
                        R.drawable.pilots,
                        "3 - 4",
                        "5th",
                        arrayListOf(d_roboson, chuh_hsiang_lu, jordan_tolbert, chih_yao_shih)
                )
        var Lioneers =
                Team(
                        "Lioneers",
                        "Hsinchu",
                        R.drawable.lioneers,
                        "5 - 4",
                        "2nd",
                        arrayListOf(kuo_hao_kao, nick_faust, b_dawson, even_lee)
                )
        var Dreamers =
                Team(
                        "Dreamers",
                        "Changhua",
                        R.drawable.dreamers,
                        "5 - 3",
                        "1st",
                        arrayListOf(calvin_chieng, chun_chi_lin, derek_Lee, kenneth)
                )
        var Steelers =
                Team(
                        "Steelers",
                        "Kaoshung",
                        R.drawable.steelers,
                        "2 - 5",
                        "6th",
                        arrayListOf(jerry_chen, tucker, cheng_ju_lu, po_chih_wang)
                )
    }
}
