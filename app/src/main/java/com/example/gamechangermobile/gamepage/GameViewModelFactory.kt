package com.example.gamechangermobile.gamepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameViewModelFactory(private val gameID: Int, private val plgGameID: String = ".") :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(gameID, plgGameID) as T
    }

}