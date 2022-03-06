package com.example.rickandmortycharacters.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Keep
data class Location(
    val name: String,
    val url: String
) : Parcelable