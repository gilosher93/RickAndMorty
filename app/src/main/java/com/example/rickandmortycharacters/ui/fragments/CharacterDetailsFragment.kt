package com.example.rickandmortycharacters.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmortycharacters.model.Character

class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    val args: CharacterDetailsFragmentArgs by navArgs()
    val character: Character
        get() = args.character

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setContent()
    }

    private fun setContent() {
        val sb = StringBuilder()
        sb.append("Name: ${character.name}")
            .append("\n")
            .append("Status: ${character.status}")
            .append("\n")
            .append("Species: ${character.species}")
            .append("\n")
            .append("Gender: ${character.gender}")
            .append("\n")
            .append("Location: ${character.location.name}")
            .append("\n")
            .append("Origin: ${character.origin.name}")

        if (character.type.isNotEmpty()) {
            sb.append("\n")
                .append("Type: ${character.type}")
        }

        binding.characterDetails.text = sb

        if (character.image.isNotEmpty()) {
            context?.let {
                Glide.with(it)
                    .applyDefaultRequestOptions(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .load(character.image)
                    .into(binding.characterImage)
            }
        }
    }
}