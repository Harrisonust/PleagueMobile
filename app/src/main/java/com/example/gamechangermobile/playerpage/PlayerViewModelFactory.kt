package com.example.gamechangermobile.playerpage

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayerViewModelFactory(private val application: Application, private val playerGCID: Int): ViewModelProvider.AndroidViewModelFactory(application) {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return PlayerViewModel(
//            application, playerGCID
//        ) as T
//    }

}