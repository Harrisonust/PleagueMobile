package com.example.gamechangermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gamechangermobile.models.Player
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val playerData = intent.getParcelableExtra<Player>("selected_player")
        Log.d("Debug", playerData?._pts.toString()+"!!!")
        player_page_profile_pic.setImageResource(playerData!!.ProfilePic)
        player_page_player_firstname.text = playerData?.FirstName
        player_page_player_lastname.text = playerData?.LastName
        player_page_player_team.text = playerData?.team.Name
        player_page_player_number.text = playerData?.number.toString()
        player_page_player_position.text = playerData?.position
        player_page_player_pts.text = playerData?._pts.toString()
        player_page_player_reb.text = playerData?._reb.toString()
        player_page_player_ast.text = playerData?._ast.toString()

        player_page_tab.addTab(player_page_tab.newTab().setText("Game Record"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Stats"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Career"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Adv"))
        player_page_tab.addTab(player_page_tab.newTab().setText("Team eff"))
    }


}