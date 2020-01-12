package com.example.nhlstats.features.teams.data.dto

import com.google.gson.annotations.SerializedName

data class TeamsResponse(
    @SerializedName("teams")
    val teams: List<TeamDto>
) {
}