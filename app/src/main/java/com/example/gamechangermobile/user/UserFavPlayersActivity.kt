package com.example.gamechangermobile.user

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamechangermobile.MainActivity.Companion.currentUser
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_user_fav_players.*

class UserFavPlayersActivity : AppCompatActivity() {
    private val onItemClickCallback: FavPlayersAdapter.ItemClickListener = onItemCLickCallbackFunc()
    private val favPlayer: MutableSet<PlayerID> = currentUser.favPlayer
    private val otherPlayer: MutableSet<PlayerID> =
        getAllPlayer().minus(currentUser.favPlayer) as MutableSet<PlayerID>


    private fun onItemCLickCallbackFunc(): FavPlayersAdapter.ItemClickListener {
        return object : FavPlayersAdapter.ItemClickListener {
            override fun onItemClickListener() {
                fav_players_recycler_view.adapter =
                    FavPlayersAdapter(favPlayer.toList(), true, onItemClickCallback)
                fav_players_recycler_view.adapter?.notifyDataSetChanged()
                other_players_recycler_view.adapter =
                    FavPlayersAdapter(getAllPlayer().minus(favPlayer).toList(), false, onItemClickCallback)
                other_players_recycler_view.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_fav_players)

        fav_players_recycler_view.adapter =
            FavPlayersAdapter(favPlayer.toList(), true, onItemClickCallback)
        fav_players_recycler_view.layoutManager = LinearLayoutManager(this)
        other_players_recycler_view.adapter =
            FavPlayersAdapter(otherPlayer.toList(), false, onItemClickCallback)
        other_players_recycler_view.layoutManager = LinearLayoutManager(this)
    }
}

fun addToFavPlayer(view: View, player: PlayerID) {
    currentUser.favPlayer.add(player)
    Snackbar.make(
        view, "${getPlayerById(player)?.fullName} is added to Favorite", Snackbar.LENGTH_SHORT
    ).setAction("Undo") { removeFromFavPlayer(view, player) }.show()
}


fun removeFromFavPlayer(view: View, player: PlayerID) {
    currentUser.favPlayer.remove(player)
    Snackbar.make(
        view, "${getPlayerById(player)?.fullName} is removed from Favorite", Snackbar.LENGTH_SHORT
    ).setAction("Undo") { addToFavPlayer(view, player) }.show()
}
