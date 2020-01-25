package com.example.nhlstats.features.teams.data.response

import com.example.nhlstats.features.teams.data.dto.TeamDto
import com.google.gson.annotations.SerializedName

data class TeamsResponse(
    @SerializedName("teams")
    val teams: List<TeamDto>
) {
}