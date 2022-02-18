package com.example.gamechangermobile.user

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.chuh_hsiang_lu
import com.example.gamechangermobile.MainActivity.Companion.currentUser
import com.example.gamechangermobile.MainActivity.Companion.jordan_tolbert
import com.example.gamechangermobile.MainActivity.Companion.kuo_hao_kao
import com.example.gamechangermobile.MainActivity.Companion.nick_faust
import com.example.gamechangermobile.R
import com.example.gamechangermobile.gametab.GameAdapter
import com.example.gamechangermobile.models.Player
import com.example.gamechangermobile.models.getAllPlayer
import kotlinx.android.synthetic.main.activity_user_fav_players.*
import kotlinx.android.synthetic.main.fragment_game.*

class UserFavPlayersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_fav_players)

        val favPlayer = currentUser.favPlayer
        val otherPlayer = getAllPlayer().minus(currentUser.favPlayer)

        fav_players_recycler_view.adapter = FavPlayersAdapter(favPlayer.toList(), true)
        fav_players_recycler_view.layoutManager = LinearLayoutManager(this)

        other_players_recycler_view.adapter = FavPlayersAdapter(otherPlayer.toList(), false)
        other_players_recycler_view.layoutManager = LinearLayoutManager(this)
    }

}

fun notifyFavPlayerDataChanged(player: Player, addToFav: Boolean) {
    if (addToFav) {
        currentUser.favPlayer.add(player)
    }else{
        currentUser.favPlayer.remove(player)
    }
//    fav_players_recycler_view.adapter!!.notifyDataSetChanged()
}