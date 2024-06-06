package com.geeks.rickandmortyy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.geeks.rickandmortyy.CharacterDataSourceFactory
import com.geeks.rickandmortyy.api.ApiService
import com.geeks.rickandmortyy.data.model.BaseResponse
import javax.inject.Inject
import com.geeks.rickandmortyy.data.model.Character
import com.geeks.rickandmortyy.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository @Inject constructor(
    private val api: ApiService
) {
    fun getCharacters(): MutableLiveData<Resource<List<Character>>> {
//        val factory = CharacterDataSourceFactory(api)
//        val config = PagedList.Config.Builder()
//            .setEnablePlaceholders(false)
//            .setPageSize(20)
//            .build()
        val data = MutableLiveData<Resource<List<Character>>>()

        data.postValue(Resource.Loading())
        api.getCharacters(page = 1).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, responce: Response<BaseResponse>) {
                if (responce.isSuccessful && responce.body() != null) {
                    responce.body()?.let {
                        data.postValue(Resource.Success(it.results))
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                data.postValue(Resource.Error(t.localizedMessage ?: "Unknown error"))
            }
        })
        return data
        //return LivePagedListBuilder(factory, config).build()
    }
}