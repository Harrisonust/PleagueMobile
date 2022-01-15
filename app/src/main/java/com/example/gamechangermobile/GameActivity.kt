package com.example.gamechangermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import com.example.gamechangermobile.models.Game
import com.example.gamechangermobile.models.Player
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        actionBar?.hide()
        setContentView(R.layout.activity_game)
        val game_data = intent.getParcelableExtra<Game>("GAME_DATA")
        if(game_data != null) {
            Log.d("Debug", game_data!!.hostName)
            game_page_header_guest_icon.setImageResource(game_data!!.guestImg)
            game_page_header_host_icon.setImageResource(game_data!!.hostImg)
            game_page_header_guest_score.text = game_data!!.guestScore.toString()
            game_page_header_host_score.text = game_data!!.hostScore.toString()
            game_page_header_time.text = game_data!!.remainingTime
//            game_page_header_
        }

        game_page_tab.addTab(game_page_tab.newTab().setText("Summary"))
        game_page_tab.addTab(game_page_tab.newTab().setText("Box Score"))
        game_page_tab.addTab(game_page_tab.newTab().setText("Highlights"))
        game_page_tab.addTab(game_page_tab.newTab().setText("Plays"))

        game_page_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(game_page_tab))
        game_page_tab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(game_page_viewpager))
    }
}