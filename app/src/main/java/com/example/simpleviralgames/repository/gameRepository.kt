package com.example.simpleviralgames.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simpleviralgames.api.apiCall
import com.example.simpleviralgames.model.gamelist

class gameRepository(private val apiCall: apiCall) {

    private val gameLiveData = MutableLiveData<gamelist>()
    val gameData: LiveData<gamelist>
        get() = gameLiveData

    suspend fun getResponse() {
       
        val result =
            apiCall.getApiReponse("https://prod.apis.simpleviralgames.com/game/slug/water-sort-2?language=en")
        if (result.body() != null) {
            gameLiveData.postValue(result.body()
            )
        }
    }
}