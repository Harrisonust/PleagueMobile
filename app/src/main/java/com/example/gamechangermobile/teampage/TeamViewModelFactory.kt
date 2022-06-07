package com.example.gamechangermobile.teampage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamechangermobile.playerpage.PlayerViewModel

class TeamViewModelFactory(private val teamID: Int): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TeamViewModel(teamID) as T
    }
}