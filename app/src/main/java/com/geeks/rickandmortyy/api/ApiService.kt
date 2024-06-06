package com.geeks.rickandmortyy.api

import com.geeks.rickandmortyy.data.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    fun getCharacters(
        @Query("page") page: Int
    ): Call<BaseResponse>
}