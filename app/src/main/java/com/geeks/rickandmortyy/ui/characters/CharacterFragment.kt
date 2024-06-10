package com.geeks.rickandmortyy.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.geeks.rickandmortyy.databinding.FragmentCharacterBinding
import com.geeks.rickandmortyy.ui.adapter.CharacterAdapter

class CharacterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterBinding
    private val viewModel: CharacterViewModel by viewModel()
    private lateinit var charactersAdapter: CharacterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserve()
    }

    private fun initialize() {
        charactersAdapter = CharacterAdapter()
        binding.rvCharacter.adapter = charactersAdapter
    }

    private fun setupObserve() {
        viewModel.getCharacters().observe(viewLifecycleOwner){
            charactersAdapter.submitData(this.lifecycle,it)
        }
    }
}