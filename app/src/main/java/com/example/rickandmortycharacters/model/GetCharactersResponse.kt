package com.example.rickandmortycharacters.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class GetCharactersResponse(
    val info: Info,
    val results: List<Character>
)