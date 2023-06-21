package com.example.simpleviralgames.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://prod.apis.simpleviralgames.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}