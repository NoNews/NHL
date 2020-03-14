package com.example.nhlstats.features.teams.data.dto

import com.google.gson.annotations.SerializedName

data class ShortPersonDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("link")
    val link: String
)