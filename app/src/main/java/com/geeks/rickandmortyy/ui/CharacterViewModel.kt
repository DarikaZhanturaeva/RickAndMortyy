package com.geeks.rickandmortyy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.geeks.rickandmortyy.data.Repository
import com.geeks.rickandmortyy.data.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    fun getCharacters() = repository.getCharacters()
    //val characters: LiveData<PagedList<Character>> = repository.getCharacters()
}