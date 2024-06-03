package com.geeks.rickandmortyy.ui

import androidx.lifecycle.ViewModel
import com.geeks.rickandmortyy.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    fun getCharacters() = repository.getCharacters()

}