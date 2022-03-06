package com.example.rickandmortycharacters.network

import com.example.rickandmortycharacters.model.GetCharactersResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class RickAndMortyServiceImpl(
    private val client: HttpClient
): RickAndMortyService {
    override suspend fun getCharacters(): GetCharactersResponse? {
        return try {
            client.get {
                url(HttpRoutes.CHARACTERS)
                contentType(ContentType.Application.Json)
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            null
        }
    }


}