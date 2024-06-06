package com.geeks.rickandmortyy

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.geeks.rickandmortyy.api.ApiService
import com.geeks.rickandmortyy.data.model.Character

class CharacterDataSourceFactory(private val api: ApiService) : DataSource.Factory<Int, Character>() {

    val sourceLiveData = MutableLiveData<CharacterDataSource>()

    override fun create(): DataSource<Int, Character> {
        val source = CharacterDataSource(api)
        sourceLiveData.postValue(source)
        return source
    }
}