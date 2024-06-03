package com.geeks.rickandmortyy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.rickandmortyy.R
import com.geeks.rickandmortyy.databinding.FragmentCharacterBinding
import com.geeks.rickandmortyy.ui.CharacterViewModel
import com.geeks.rickandmortyy.ui.adapter.CharacterAdapter
import com.geeks.rickandmortyy.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }
    private var charactersAdapter: CharacterAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        /*viewModel.getCharacters().observe(viewLifecycleOwner) { res ->
            setupRecyclerView()
            when (res) {
                is Resource.Loading -> {
                    binding.pgCharacter.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.pgCharacter.visibility = View.GONE
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), res.message, Toast.LENGTH_SHORT).show()
                }
            }
        }*/
    }

    private fun setupRecyclerView() {
        charactersAdapter = CharacterAdapter()
        binding.rvCharacter.apply {
            setHasFixedSize(true)
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}