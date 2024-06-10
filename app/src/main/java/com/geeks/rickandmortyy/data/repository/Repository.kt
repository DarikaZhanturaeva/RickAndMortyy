package com.geeks.rickandmortyy.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.geeks.rickandmortyy.data.api.ApiService
import com.geeks.rickandmortyy.data.model.characters.Character
import com.geeks.rickandmortyy.data.model.detail.BaseDetailResponse
import com.geeks.rickandmortyy.data.paging.CharacterPagingSource
import com.geeks.rickandmortyy.utils.Resource
import kotlinx.coroutines.Dispatchers
import okio.IOException
import retrofit2.Callback
import retrofit2.HttpException

const val ARG_ERROR_MESSAGE = "Unknown Error"

class Repository(
    private val api: ApiService
) {
    fun getCharacters(): LiveData<PagingData<Character>> {
        return Pager(
            pagingSourceFactory = {
                CharacterPagingSource(api)
            },
            config = PagingConfig(
                pageSize = 10,
            )
        ).liveData
    }

//    fun getSingleCharacters(): LiveData<BaseDetailResponse> {
//        return liveData(Dispatchers.IO) {
//            val response = api.getSingleCharacter(id = 1)
//            if (response.isSuccessful && response.body() != null) {
//                response.body()
//            }
//        }
//    }
}