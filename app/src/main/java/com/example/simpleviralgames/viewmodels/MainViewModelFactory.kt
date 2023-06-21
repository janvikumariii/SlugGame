package com.example.simpleviralgames.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleviralgames.repository.gameRepository

class MainViewModelFactory(private val gameRepository: gameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(gameRepository) as T
    }
}