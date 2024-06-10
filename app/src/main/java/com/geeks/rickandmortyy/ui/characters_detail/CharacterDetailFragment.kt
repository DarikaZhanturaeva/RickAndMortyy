package com.geeks.rickandmortyy.ui.characters_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geeks.rickandmortyy.R
import com.geeks.rickandmortyy.databinding.FragmentCharacterDetailBinding
import com.skydoves.expandablelayout.ExpandableLayout

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ex = requireActivity().findViewById<ExpandableLayout>(R.id.expandable)
        var g = 0
        ex.parentLayout.setOnClickListener {
            if (g == 0) {
                g = 1
                ex.expand()
            } else {
                g = 0
                ex.collapse()
            }
        }
    }
}