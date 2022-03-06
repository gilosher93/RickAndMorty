package com.example.rickandmortycharacters.ui.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.HomeFragmentBinding
import com.example.rickandmortycharacters.model.Character
import com.example.rickandmortycharacters.ui.adapter.CharactersAdapter
import com.example.rickandmortycharacters.view_models.MainViewModel
import com.example.rickandmortycharacters.view_models.Result

const val NUM_OF_COLUMN = 2

class HomeFragment : Fragment() {

    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.loadCharacters()

        charactersAdapter = CharactersAdapter(onCharacterClicked = ::onCharacterClicked)
        binding.characterList.adapter = charactersAdapter
        binding.characterList.layoutManager = GridLayoutManager(requireContext(), NUM_OF_COLUMN)

        observeCharacters()
    }

    private fun onCharacterClicked(character: Character) {
        val action = HomeFragmentDirections.actionMainFragmentToCharacterDetailsFragment(character)
        action.arguments
        findNavController().navigate(action)
    }

    private fun observeCharacters() {
        viewModel.observeCharacters(viewLifecycleOwner) {
            when(it) {
                is Result.Loading -> {
                    setProgress(true)
                }
                is Result.Success -> {
                    setProgress(false)
                    charactersAdapter.setData(it.characters)
                }
                is Result.Error -> {
                    setProgress(false)
                }
                else -> {}
            }
        }
    }

    private fun setProgress(show: Boolean) {
        if (show) {
            binding.homeProgressBar.visibility = View.VISIBLE
        } else {
            binding.homeProgressBar.visibility = View.GONE
        }
    }
}