package com.example.gamechangermobile.gamepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamechangermobile.playerpage.PlayerViewModel

class GameViewModelFactory(private val gameID: Int): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(gameID) as T
    }

}