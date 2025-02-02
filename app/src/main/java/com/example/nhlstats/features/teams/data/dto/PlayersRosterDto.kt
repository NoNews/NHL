package com.example.nhlstats.features.teams.data.dto

import com.google.gson.annotations.SerializedName

data class PlayersRosterDto(
    @SerializedName("roster")
    val nested: List<ShortPlayerDto>
)