package com.geeks.rickandmortyy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.DataSource
import com.geeks.rickandmortyy.CharacterDataSourceFactory
import com.geeks.rickandmortyy.api.ApiService
import javax.inject.Inject
import com.geeks.rickandmortyy.data.model.Character
import com.geeks.rickandmortyy.utils.Resource
import kotlinx.coroutines.Dispatchers
import okio.IOException
import retrofit2.HttpException

const val ARG_ERROR_MESSAGE = "Unknown Error"

class Repository @Inject constructor(
    private val api: ApiService
) {
    fun getCharacters(): LiveData<Resource<List<Character>>> {
        return liveData(Dispatchers.IO) {
            try {
                val response = api.getCharacters(page = 1)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        emit(Resource.Success(it.results))
                    }
                }
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: ARG_ERROR_MESSAGE))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: ARG_ERROR_MESSAGE))
            }
        }
    }
    fun getCharactersDataSourceFactory(): DataSource.Factory<Int, Character> {
        return CharacterDataSourceFactory(api)
    }
}