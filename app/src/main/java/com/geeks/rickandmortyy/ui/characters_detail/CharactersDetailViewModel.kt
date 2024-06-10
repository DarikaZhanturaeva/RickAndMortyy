package com.geeks.rickandmortyy.ui.characters_detail

import androidx.lifecycle.ViewModel
import com.geeks.rickandmortyy.data.repository.Repository

class CharactersDetailViewModel(
    private val repository: Repository
) : ViewModel() {
    fun getSingleCharacters() = repository.getCharacters()
}