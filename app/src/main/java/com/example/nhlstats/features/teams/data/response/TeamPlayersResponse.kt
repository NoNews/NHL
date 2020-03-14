package com.example.nhlstats.features.teams.data.response

import com.example.nhlstats.features.teams.data.dto.PlayerTeamDto
import com.google.gson.annotations.SerializedName

data class TeamPlayersResponse(
    @SerializedName("teams")
    val teams: List<PlayerTeamDto>
)
