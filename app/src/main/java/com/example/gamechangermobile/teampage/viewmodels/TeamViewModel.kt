package com.example.gamechangermobile

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.gamechangermobile.MainActivity.Companion.games
import com.example.gamechangermobile.database.GCGame
import com.example.gamechangermobile.database.GCStatsParser
import com.example.gamechangermobile.database.GCTeam
import com.example.gamechangermobile.models.*
import com.example.gamechangermobile.network.Api
import com.example.gamechangermobile.network.Network
import com.example.gamechangermobile.network.UrlRequestCallback
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class TeamViewModel(application: Application, teamID: TeamID) : AndroidViewModel(application) {
    private val context = application
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallbackFunc())

    private val gameList = MutableLiveData<MutableSet<Game>>()
    private val isGamesUpdated = MutableLiveData<Boolean>()

    class Factory(private val application: Application, private val teamID: TeamID) :
        ViewModelProvider.AndroidViewModelFactory(application) {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TeamViewModel(application, teamID) as T
        }
    }

    init {
        Network.loadData(
            context,
            "team_season_data",
            mapOf(
                "season_id" to "4",
                "part" to "info,ranking",
                "team_id" to teamID?.ID.toString()
            ),
            urlRequestCallback
        )
    }

    fun getGames(): LiveData<MutableSet<Game>> {
        return gameList
    }

    fun getGameStatus(): LiveData<Boolean> {
        return isGamesUpdated
    }

    private fun networkRequestCallbackFunc(): UrlRequestCallback.OnFinishRequest {
        return object : UrlRequestCallback.OnFinishRequest {
            override fun onFinishRequest(result: String?) {

//                var data = result?.let { GCStatsParser().parse<GCTeam>(it) }?.get(0)
//                var ranking = "na"
//
//                if (data != null) {
//                    val team = getTeamById(teamID)
//                    team?.totalRecord?.wins = data.info.win_count
//                    team?.totalRecord?.loses = data.info.lose_count
//                    team?.streak = data.info.winning_streak.toString()
//                    team?.ranking = data.ranking.team.ranking.toString()
//
//                    ranking = data.ranking.team.ranking.toString()
//                    ranking += if (ranking == "1") "st"
//                    else if (ranking == "2") "nd"
//                    else "th"
//                }
//                gameList.postValue(games)
                isGamesUpdated.postValue(true)
                Log.d("ViewModel", "Finish assigning")

            }
        }
    }
}