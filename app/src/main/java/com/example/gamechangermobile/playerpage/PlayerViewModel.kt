package com.example.gamechangermobile.playerpage

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.gamechangermobile.MainActivity.Companion.games
import com.example.gamechangermobile.database.GCGame
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.*
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class PlayerViewModel(playerGCID: Int) : ViewModel() {
    private val okHttp = OkHttp(OkHttpOnSuccessResponse())

    private val isGamesUpdated = MutableLiveData<Boolean>()

    init {
        Log.d("DEBUG", "HIHIHIHIYOU ARE HERE!!!!!! AHHHHH $playerGCID")
        okHttp.getRequest(
            "game_data",
            mapOf("season_id" to "4"),
            "GC"
        )
    }

    fun getGameStatus(): LiveData<Boolean> {
        return isGamesUpdated
    }

    private fun OkHttpOnSuccessResponse(): OkHttp.OnSuccessResponse {
        return object: OkHttp.OnSuccessResponse {
            override fun action(result: String?) {
                Log.d("RESPONSE", result!!)
                isGamesUpdated.postValue(true)
            }
        }
    }
}