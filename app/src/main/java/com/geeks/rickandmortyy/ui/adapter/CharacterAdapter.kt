package com.geeks.rickandmortyy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geeks.rickandmortyy.data.model.characters.Character
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.paging.PagingDataAdapter
import coil.load
import com.geeks.rickandmortyy.R
import com.geeks.rickandmortyy.databinding.ItemCharacterBinding

class CharacterAdapter() :
    PagingDataAdapter<Character, CharacterAdapter.RickAndMortyViewHolder>(diffUtil) {
    inner class RickAndMortyViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(character: Character) = with(binding) {
            characterName.text = character.name
            characterLocation.text = character.location?.name
            characterFirstSeen.text = character.origin?.name
            imgCharacter.load(character.image) {
                crossfade(true)
            }
            characterStatus.text = character.status
            colorIndicator.setImageResource(
                when {
                    character.status?.contains("Dead") == true -> R.drawable.ic_circle_red
                    character.status?.contains("Alive") == true -> R.drawable.ic_circle_green
                    else -> R.drawable.ic_circle_grey
                }
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyViewHolder {
        return RickAndMortyViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RickAndMortyViewHolder, position: Int) {
        getItem(position).let {
            if (it != null) {
                holder.onBind(it)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
