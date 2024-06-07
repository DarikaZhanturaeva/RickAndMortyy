package com.geeks.rickandmortyy

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.geeks.rickandmortyy.api.ApiService
import com.geeks.rickandmortyy.data.model.BaseResponse
import com.geeks.rickandmortyy.data.model.Character
import com.geeks.rickandmortyy.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDataSource(private val api: ApiService) : PageKeyedDataSource<Int, Character>() {

    val data = MutableLiveData<Resource<List<Character>>>()
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Character>) {
        scope.launch {
            try {
                val response = api.getCharacters(1)
                if (response.isSuccessful && response.body() != null) {
                    val characters = response.body()!!.results
                    withContext(Dispatchers.Main) {
                        callback.onResult(characters, null, 2)
                    }
                }
            } catch (e: Exception) {
                data.postValue(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        scope.launch {
            try {
                val response = api.getCharacters(params.key)
                if (response.isSuccessful && response.body() != null) {
                    val characters = response.body()!!.results
                    withContext(Dispatchers.Main) {
                        callback.onResult(characters, params.key + 1)
                    }
                }
            } catch (e: Exception) {
                data.postValue(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }
}