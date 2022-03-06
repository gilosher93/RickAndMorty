package com.example.rickandmortycharacters.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String?
)