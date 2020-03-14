package com.example.nhlstats.features.teams.data.dto

import com.google.gson.annotations.SerializedName

data class ShortPlayerDto(
    @SerializedName("position")
    val position: PositionDto,
    @SerializedName("person")
    val personDto: ShortPersonDto,
    @SerializedName("jerseyNumber")
    val jerseyNumber: String?
)