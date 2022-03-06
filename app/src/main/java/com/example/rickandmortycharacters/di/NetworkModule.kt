package com.example.rickandmortycharacters.di

import com.example.rickandmortycharacters.network.RickAndMortyService
import org.koin.dsl.module

val networkModule = module {
    single { RickAndMortyService.create() }
}