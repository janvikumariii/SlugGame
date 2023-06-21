package com.example.simpleviralgames.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleviralgames.model.gamelist
import com.example.simpleviralgames.repository.gameRepository
import kotlinx.coroutines.launch

class MainViewModel(private val gameRepository: gameRepository):ViewModel() {
    init{
        viewModelScope.launch {
            gameRepository.getResponse()
        }

    }
    val gameData: LiveData<gamelist>
        get() = gameRepository.gameData
}