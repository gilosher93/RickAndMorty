package com.example.rickandmortycharacters.view_models

import androidx.lifecycle.*
import com.example.rickandmortycharacters.model.Character
import com.example.rickandmortycharacters.network.RickAndMortyService
import com.example.rickandmortycharacters.model.GetCharactersResponse
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MainViewModel : ViewModel() {

    private val characters =
        MutableLiveData<Result>(Result.Loading)

    private val service : RickAndMortyService by inject(RickAndMortyService::class.java)

    fun observeCharacters(
        lifecycleOwner: LifecycleOwner,
        observer: Observer<Result>
    ) {
        characters.observe(lifecycleOwner, observer)
    }

    fun loadCharacters() {
        viewModelScope.launch {
            val response = service.getCharacters()
            response?.results?.let {
                characters.value = Result.Success(it)
            } ?: run {
                characters.value = Result.Error(Throwable("Unable to fetch data..."))
            }
        }
    }
}

sealed class Result {
    data class Success(val characters: List<Character>): Result()
    data class Error(val throwable: Throwable): Result()
    object Loading: Result()
}