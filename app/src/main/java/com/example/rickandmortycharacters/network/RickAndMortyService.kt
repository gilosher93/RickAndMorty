package com.example.rickandmortycharacters.network

import com.example.rickandmortycharacters.model.GetCharactersResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface RickAndMortyService {

    suspend fun getCharacters(): GetCharactersResponse?

    companion object {
        fun create(): RickAndMortyService =
            RickAndMortyServiceImpl(HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            })
    }
}