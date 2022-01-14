package com.example.gamechangermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.gamechangermobile.models.Player
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gamesFrag = GameFragment()
        val statsFrag = StatsFragment()

        bottom_navigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.games_page -> {
                    Toast.makeText(this, "game", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.mainFragView, gamesFrag)
                        commit()
                    }
                }

                R.id.stats_page -> {
                    Toast.makeText(this, "stats", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.mainFragView, statsFrag)
                        commit()
                    }
                }
            }
        }
    }
}