package com.example.gamechangermobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.gamechangermobile.models.Player
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()

        val gamesFrag = GameFragment()
        val statsFrag = StatsFragment()
        replaceFragment(gamesFrag)
        debugButton.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            val player = Player("Harrison", "Lo", R.drawable.ic_baseline_sports_basketball_24)
            player._pts = 10.2F
            player._reb = 5.4F
            player._ast = 5.6F
            player.position = "C"
            player.number = 11

            intent.putExtra("selected_player", player)
            startActivity(intent)
        }
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
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
}