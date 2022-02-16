package com.example.gamechangermobile.user

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.chuh_hsiang_lu
import com.example.gamechangermobile.MainActivity.Companion.jordan_tolbert
import com.example.gamechangermobile.MainActivity.Companion.kuo_hao_kao
import com.example.gamechangermobile.MainActivity.Companion.nick_faust
import com.example.gamechangermobile.R
import com.example.gamechangermobile.gametab.GameAdapter
import com.example.gamechangermobile.models.Player
import kotlinx.android.synthetic.main.activity_user_fav_players.*
import kotlinx.android.synthetic.main.fragment_game.*

class UserFavPlayersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_fav_players)

        val favPlayer = listOf<Player>(jordan_tolbert, chuh_hsiang_lu)
        val otherPlayer = listOf<Player>(kuo_hao_kao, nick_faust)

        fav_players_recycler_view.adapter = FavPlayersAdapter(favPlayer)
        fav_players_recycler_view.layoutManager = LinearLayoutManager(this)

        other_players_recycler_view.adapter = FavPlayersAdapter(otherPlayer)
        other_players_recycler_view.layoutManager = LinearLayoutManager(this)
    }

}