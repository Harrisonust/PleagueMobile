package com.example.gamechangermobile.playerpage

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.gamechangermobile.MainActivity.Companion.games
import com.example.gamechangermobile.database.GCGame
import com.example.gamechangermobile.database.GCStatsParser
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

class PlayerViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    private val urlRequestCallback = UrlRequestCallback(networkRequestCallbackFunc())

    private val gameList = MutableLiveData<MutableSet<Game>>()
    private val isGamesUpdated = MutableLiveData<Boolean>()

    init {
        Network.loadData(
            context,
            "game_data",
            mapOf("season_id" to "4"),
            "GC",
            urlRequestCallback
        )
        Log.d("DEBUG", "YOU ARE HERE!!!!!! AHHHHH")
    }

    fun test(): String {
        return "HIHIHIHI"
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

                var GCGameList = result?.let { GCStatsParser().parse<GCGame>(it) }

                if (GCGameList != null) {
                    for (gcGameData in GCGameList) {
                        val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(gcGameData.date)
                        val game = Game(
                            gameId = GameID(gcGameData.id),
                            guestTeam = getTeamIdByName(gcGameData.away_team_name),
                            hostTeam = getTeamIdByName(gcGameData.home_team_name),
                            date = date,
                            guestScore = gcGameData.away_team_score,
                            hostScore = gcGameData.home_team_score,
                            status = GameStatus.END
                        )
                        games.add(game)
                        getTeamById(game.hostTeam)?.gamesIdList?.add(game.gameId)
                        getTeamById(game.guestTeam)?.gamesIdList?.add(game.gameId)
                    }
                }
                gameList.postValue(games)
                isGamesUpdated.postValue(true)
                Log.d("ViewModel", "Finish assigning")
//                activity?.runOnUiThread {
//                    updateGameCardView()
//                }
            }
        }
    }
}