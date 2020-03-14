package com.example.nhlstats.features.teams.data.dto

import com.google.gson.annotations.SerializedName

data class PlayerTeamDto(
    @SerializedName("shortName")
    val teamName: String,
    @SerializedName("roster")
    val roster: PlayersRosterDto
)