package com.example.rickandmortycharacters.ui.diff_utils

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortycharacters.model.Character

class CharacterDiffUtilCallback(
    private var newList: List<Character>,
    private var oldList: List<Character>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}