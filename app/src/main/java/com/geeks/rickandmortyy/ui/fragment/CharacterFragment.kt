package com.geeks.rickandmortyy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.geeks.rickandmortyy.data.model.Character
import com.geeks.rickandmortyy.databinding.FragmentCharacterBinding
import com.geeks.rickandmortyy.ui.CharacterViewModel
import com.geeks.rickandmortyy.ui.adapter.CharacterAdapter
import com.geeks.rickandmortyy.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterBinding
    private val viewModel by viewModels<CharacterViewModel>()
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
        viewModel.characters.observe(viewLifecycleOwner, Observer<PagedList<Character>> {
            charactersAdapter.submitList(it)
        })

        viewModel.getCharacters().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.pgCharacter.visibility = View.VISIBLE
                    binding.rvCharacter.visibility = View.GONE
                }

                is Resource.Success -> {
                    binding.pgCharacter.visibility = View.GONE
                    binding.rvCharacter.visibility = View.VISIBLE
                    resource.data.let { charactersAdapter.submitList(resource) }
                }

                is Resource.Error -> {
                    binding.pgCharacter.visibility = View.GONE
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.errorTextView.text = resource.message
                }
            }
        }
    }
}