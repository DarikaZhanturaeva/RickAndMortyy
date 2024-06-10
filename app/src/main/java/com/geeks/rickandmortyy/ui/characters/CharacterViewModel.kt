package com.geeks.rickandmortyy.ui.characters

import androidx.lifecycle.ViewModel
import com.geeks.rickandmortyy.data.repository.Repository

class CharacterViewModel (
    private val repository: Repository
):ViewModel() {
    fun getCharacters() = repository.getCharacters()

}