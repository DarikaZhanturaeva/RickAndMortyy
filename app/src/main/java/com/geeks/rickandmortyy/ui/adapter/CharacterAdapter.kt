package com.geeks.rickandmortyy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.geeks.rickandmortyy.data.model.Character
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.transform.CircleCropTransformation
import com.geeks.rickandmortyy.databinding.ItemCharacterBinding

class CharacterAdapter() : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var binding: ItemCharacterBinding? = null

    inner class CharacterViewHolder(ItemBinding: ItemCharacterBinding) : RecyclerView.ViewHolder(
        ItemBinding.root
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding!!)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = differ.currentList[position]
        holder.itemView.apply {
            binding?.characterName?.text = character.name
            binding?.characterLocation?.text = character.location.name
            binding?.characterFirstSeen?.text = character.origin.name
            binding?.let {
                binding?.imgCharacter?.let { imgCharacter ->
                    imgCharacter.load(character.image) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
}