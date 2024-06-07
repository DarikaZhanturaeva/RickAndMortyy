package com.geeks.rickandmortyy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.geeks.rickandmortyy.data.Repository
import com.geeks.rickandmortyy.data.model.Character

class CharacterViewModel (
    private val repository: Repository
):ViewModel() {
    fun getCharacters() = repository.getCharacters()

    val characters: LiveData<PagedList<Character>> = LivePagedListBuilder(
        repository.getCharactersDataSourceFactory(),
        PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
    ).build()
}