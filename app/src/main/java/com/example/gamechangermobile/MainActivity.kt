package com.example.gamechangermobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gamesFrag = GameFragment()
        val statsFrag = StatsFragment()

        replaceFragment(gamesFrag)

        bottom_navigation.setOnItemSelectedListener {
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
        debug_btn.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragView, fragment)
            commit()
        }
    }
}