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
                        FirstName = "chih-chieh",
                        LastName = "Lin",
                        ProfilePic = R.drawable.braves_chih_chieh_lin,
                        _pts = 9.5F,
                        _reb = 3.33F,
                        _ast = 1.83F,
                        age = 38,
                        number = 12,
                        position = "F"
                )
        var hsiang_chun_tseng =
                Player(
                        FirstName = "Hsiang-chun",
                        LastName = "Tseng",
                        ProfilePic = R.drawable.braves_hsiang_chun_tseng,
                        _pts = 3.38F,
                        _reb = 3.25F,
                        _ast = 0.5F,
                        age = 22,
                        number = 21,
                        position = "C"
                )
        var michael_sigletary =
                Player(
                        FirstName = "Michael",
                        LastName = "Singletary",
                        ProfilePic = R.drawable.braves_michael_singletary,
                        _pts = 12.8F,
                        _reb = 3.4F,
                        _ast = 3.1F,
                        age = 28,
                        number = 17,
                        position = "F"
                )
        var shu_wei_lin =
                Player(
                        FirstName = "Shu_wei",
                        LastName = "Lin",
                        ProfilePic = R.drawable.braves_shu_wei_lin,
                        _pts = 12.13F,
                        _reb = 2.88F,
                        _ast = 4.13F,
                        age = 28,
                        number = 1,
                        position = "G"
                )

        var q_davis =
                Player(
                        FirstName = "Q",
                        LastName = "Davis",
                        ProfilePic = R.drawable.kings_q_davis,
                        _pts = 5F,
                        _reb = 11F,
                        _ast = 1F,
                        age = 37,
                        number = 50,
                        position = "C"
                )
        var thomas_welsh =
                Player(
                        FirstName = "Thomas",
                        LastName = "Welsh",
                        ProfilePic = R.drawable.kings_thomas_welsh,
                        _pts = 18.57F,
                        _reb = 19.57F,
                        _ast = 2.71F,
                        age = 24,
                        number = 40,
                        position = "C"
                )
        var chun_nan_chen =
                Player(
                        FirstName = "Chun-nan",
                        LastName = "Chen",
                        ProfilePic = R.drawable.kings_chun_nan_chen,
                        _pts = 3.2F,
                        _reb = 1.8F,
                        _ast = 0.8F,
                        age = 20,
                        number = 3,
                        position = "F"
                )
        var hsing_chih_yang =
                Player(
                        FirstName = "Hsing-chih",
                        LastName = "Yang",
                        ProfilePic = R.drawable.kings_hsing_chih_yang,
                        _pts = 4.11F,
                        _reb = 3.22F,
                        _ast = 0.44F,
                        age = 27,
                        number = 33,
                        position = "F"
                )

        var d_roboson =
                Player(
                        FirstName = "Devin",
                        LastName = "Roboson",
                        ProfilePic = R.drawable.pilots_devin_robinson,
                        _pts = 24.8F,
                        _reb = 14.6F,
                        _ast = 2.2F,
                        age = 25,
                        number = 0,
                        position = "F"
                )
        var chuh_hsiang_lu =
                Player(
                        FirstName = "Chun-hsiang",
                        LastName = "Lu",
                        ProfilePic = R.drawable.pilots_chun_hsiang_lu,
                        _pts = 15.33F,
                        _reb = 5F,
                        _ast = 0.33F,
                        age = 23,
                        number = 69,
                        position = "G"
                )
        var jordan_tolbert =
                Player(
                        FirstName = "Chun-nan",
                        LastName = "Chen",
                        ProfilePic = R.drawable.pilots_jordan_tolbert,
                        _pts = 19.17F,
                        _reb = 14.17F,
                        _ast = 4.33F,
                        age = 28,
                        number = 1,
                        position = "F"
                )
        var chih_yao_shih =
                Player(
                        FirstName = "Chin-Yao",
                        LastName = "Shih",
                        ProfilePic = R.drawable.pilots_chin_yao_shih,
                        _pts = 14.17F,
                        _reb = 3.25F,
                        _ast = 3.63F,
                        age = 30,
                        number = 28,
                        position = "F"
                )

        var kuo_hao_kao =
                Player(
                        FirstName = "Oscar",
                        LastName = "Kao",
                        ProfilePic = R.drawable.lioneers_kuo_hao_kao,
                        _pts = 7.17F,
                        _reb = 3F,
                        _ast = 6.17F,
                        age = 22,
                        number = 4,
                        position = "G"
                )
        var nick_faust =
                Player(
                        FirstName = "Nicholas",
                        LastName = "Faust",
                        ProfilePic = R.drawable.lioneers_nicholas_faust,
                        _pts = 27.17F,
                        _reb = 8.5F,
                        _ast = 2.83F,
                        age = 27,
                        number = 7,
                        position = "F"
                )
        var b_dawson =
                Player(
                        FirstName = "Branden",
                        LastName = "Dawson",
                        ProfilePic = R.drawable.lioneers_branden_dawson,
                        _pts = 14.33F,
                        _reb = 14F,
                        _ast = 1.67F,
                        age = 27,
                        number = 22,
                        position = "F"
                )
        var even_lee =
                Player(
                        FirstName = "Chia-jul",
                        LastName = "Lee",
                        ProfilePic = R.drawable.lioneers_chia_jul_lee,
                        _pts = 6.89F,
                        _reb = 4.22F,
                        _ast = 2.67F,
                        age = 26,
                        number = 12,
                        position = "F"
                )
        var ming_yi =
                Player(
                        FirstName = "Ming-yi",
                        LastName = "LIN",
                        ProfilePic = R.drawable.lioneers_ming_yi_lin,
                        _pts = 3.94F,
                        _reb = 2.71F,
                        _ast = 2.88F,
                        age = 24,
                        number = 3,
                        position = "G"
                )
        var bhullar =
                Player(
                        FirstName = "Singh",
                        LastName = "Bhullar",
                        ProfilePic = R.drawable.lioneers_simbhullar,
                        _pts = 26.33F,
                        _reb = 21.89F,
                        _ast = 2.44F,
                        age = 28,
                        number = 35,
                        position = "C"
                )
        var yi_huei =
                Player(
                        FirstName = "Yi-huei",
                        LastName = "Lin",
                        ProfilePic = R.drawable.lioneers_yi_huei_lin,
                        _pts = 11.56F,
                        _reb = 4.22F,
                        _ast = 3.4F,
                        age = 34,
                        number = 36,
                        position = "F"
                )
        var leon_sung =
                Player(
                        FirstName = "Yu-hsuan",
                        LastName = "Sung",
                        ProfilePic = R.drawable.lioneers_yu_hsuan_sung,
                        _pts = 5.2F,
                        _reb = 0.8F,
                        _ast = 1F,
                        age = 31,
                        number = 5,
                        position = "F"
                )
        var elliot =
                Player(
                        FirstName = "Elliot",
                        LastName = "Tan",
                        ProfilePic = R.drawable.lioneers_elliot_tan,
                        _pts = 7.82F,
                        _reb = 2.88F,
                        _ast = 1.53F,
                        age = 32,
                        number = 6,
                        position = "G"
                )
        var yun_hao =
                Player(
                        FirstName = "Yun-hao",
                        LastName = "Chu",
                        ProfilePic = R.drawable.lioneers_yun_hao_chu,
                        _pts = 5.44F,
                        _reb = 5F,
                        _ast = 1.33F,
                        age = 23,
                        number = 8,
                        position = "F"
                )
        var hao_tien =
                Player(
                        FirstName = "Hao",
                        LastName = "Tien",
                        ProfilePic = R.drawable.lioneers_hao_tien,
                        _pts = 4.84F,
                        _reb = 2.74F,
                        _ast = 3.16F,
                        age = 22,
                        number = 9,
                        position = "G"
                )
        var shun_yi =
                Player(
                        FirstName = "Shun-yi",
                        LastName = "Hsiao",
                        ProfilePic = R.drawable.lioneers_shun_yi_hsiao,
                        _pts = 4.33F,
                        _ast = 1.71F,
                        _reb = 0.81F,
                        age = 28,
                        number = 11,
                        position = "F"
                )
         var shao_chieh =
                Player(
                        FirstName = "Shao-chieh",
                        LastName = "Kuo",
                        ProfilePic = R.drawable.lioneers_shao_chieh_kuo,
                        _pts = 2.29F,
                        _ast = 0.86F,
                        _reb = 0.29F,
                        age = 30,
                        number = 24,
                        position = "F"
                )
         var tai_hao =
                Player(
                        FirstName = "Tai-hao",
                        LastName = "Wu",
                        ProfilePic = R.drawable.lioneers_tai_hao_wu,
                        _pts = 2.67F,
                        _ast = 1.89F,
                        _reb = 1.17F,
                        age = 35,
                        number = 54,
                        position = "F"
                )

        var calvin_chieng =
                Player(
                        FirstName = "Li-Huan",
                        LastName = "Chieng",
                        ProfilePic = R.drawable.dreamers_li_huan_chieng,
                        _pts = 13.25F,
                        _reb = 4.13F,
                        _ast = 2.29F,
                        age = 32,
                        number = 7,
                        position = "F"
                )
        var chun_chi_lin =
                Player(
                        FirstName = "Chun-chi",
                        LastName = "Lin",
                        ProfilePic = R.drawable.dreamers_chun_chi_lin,
                        _pts = 9.9F,
                        _reb = 1.6F,
                        _ast = 1.5F,
                        age = 22,
                        number = 11,
                        position = "G"
                )
        var derek_Lee =
                Player(
                        FirstName = "Te-wei",
                        LastName = "Lee",
                        ProfilePic = R.drawable.dreamers_te_wei_lee,
                        _pts = 8.5F,
                        _reb = 8.27F,
                        _ast = 1.5F,
                        age = 29,
                        number = 26,
                        position = "C"
                )
        var kenneth =
                Player(
                        FirstName = "Kenneth",
                        LastName = "Chen",
                        ProfilePic = R.drawable.dreamers_kenneth_chen,
                        _pts = 6.62F,
                        _reb = 3.14F,
                        _ast = 2F,
                        age = 25,
                        number = 23,
                        position = "F"
                )

        var jerry_chen =
                Player(
                        FirstName = "Yu-wei",
                        LastName = "Chen",
                        ProfilePic = R.drawable.steelers_yu_wei_chen,
                        _pts = 11.14F,
                        _reb = 2.71F,
                        _ast = 2.29F,
                        age = 21,
                        number = 4,
                        position = "G"
                )
        var tucker =
                Player(
                        FirstName = "Anthony",
                        LastName = "Tucker",
                        ProfilePic = R.drawable.steelers_anthony_tucker,
                        _pts = 27.88F,
                        _reb = 8.12F,
                        _ast = 5.59F,
                        age = 31,
                        number = 1,
                        position = "G"
                )
        var cheng_ju_lu =
                Player(
                        FirstName = "Cheng-ju",
                        LastName = "Lu",
                        ProfilePic = R.drawable.steelers_cheng_ju_lu,
                        _pts = 15.71F,
                        _reb = 2.86F,
                        _ast = 2.29F,
                        age = 34,
                        number = 13,
                        position = "F"
                )
        var po_chih_wang =
                Player(
                        FirstName = "Po-chih",
                        LastName = "Wang",
                        ProfilePic = R.drawable.steelers_po_chih_wang,
                        _pts = 2F,
                        _reb = 1.4F,
                        _ast = 0.13F,
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
                        arrayListOf(kuo_hao_kao, nick_faust, b_dawson, even_lee, ming_yi, bhullar, yi_huei, leon_sung, elliot, yun_hao, hao_tien, shun_yi, shao_chieh, tai_hao)
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
