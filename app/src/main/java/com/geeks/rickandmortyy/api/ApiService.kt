package com.geeks.rickandmortyy.api

import com.geeks.rickandmortyy.data.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    fun getCharacters(): Call<BaseResponse>

}