package com.geeks.rickandmortyy.data.api

import com.geeks.rickandmortyy.data.model.characters.BaseResponse
import com.geeks.rickandmortyy.data.model.detail.BaseDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<BaseResponse>

    @GET("id")
    suspend fun getSingleCharacter(
        @Path("id") id: Int
    ): Response<BaseDetailResponse>

}