package com.example.simpleviralgames.api

import com.example.simpleviralgames.model.gamelist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiCall {
    @GET()
    suspend fun getApiReponse(@Url url: String): Response<gamelist>
}