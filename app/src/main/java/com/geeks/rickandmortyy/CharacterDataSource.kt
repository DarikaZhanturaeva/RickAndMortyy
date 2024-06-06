package com.geeks.rickandmortyy

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.geeks.rickandmortyy.api.ApiService
import com.geeks.rickandmortyy.data.model.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.geeks.rickandmortyy.data.model.Character
import com.geeks.rickandmortyy.utils.Resource

class CharacterDataSource(private val api: ApiService) : PageKeyedDataSource<Int, Character>() {

    val data = MutableLiveData<Resource<List<Character>>>()
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Character>) {
        api.getCharacters(1).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val characters = response.body()!!.results
                    callback.onResult(characters, null, 2)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                data.postValue(Resource.Error(t.localizedMessage ?: "Unknown error"))
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        // Not needed in most cases
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        api.getCharacters(params.key).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val characters = response.body()!!.results
                    callback.onResult(characters, params.key + 1)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                data.postValue(Resource.Error(t.localizedMessage ?: "Unknown error"))
            }
        })
    }
}