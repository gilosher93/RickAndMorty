package com.example.rickandmortycharacters.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.ItemCharacterBinding
import com.example.rickandmortycharacters.model.Character
import com.example.rickandmortycharacters.ui.diff_utils.CharacterDiffUtilCallback

class CharactersAdapter(
    private var items: List<Character> = listOf(),
    private var onCharacterClicked: (character: Character) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = DataBindingUtil.inflate<ItemCharacterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_character,
            parent,
            false
        )
        return CharacterViewHolder(binding, onCharacterClicked)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setData(newItems: List<Character>) {
        val callback = CharacterDiffUtilCallback(items, newItems)
        items = newItems

        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
    }

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding,
        private val onCharacterClicked: (character: Character) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {

            setImage(character)

            binding.root.setOnClickListener {
                onCharacterClicked(character)
            }
        }

        private fun setImage(character: Character) {
            binding.characterName.text = character.name.trim()
            if (character.image.isNotEmpty()) {
                Glide
                    .with(itemView.context)
                    .applyDefaultRequestOptions(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .load(character.image)
                    .into(binding.characterImage)
            }
            val imageSize = binding.root.context.resources.displayMetrics.widthPixels / 2
            val layoutParams = binding.characterImage.layoutParams
            layoutParams.width = imageSize
            layoutParams.height = imageSize
            binding.characterImage.layoutParams = layoutParams
        }
    }

}