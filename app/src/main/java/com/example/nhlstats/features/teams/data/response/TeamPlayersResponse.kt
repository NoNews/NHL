package com.example.nhlstats.features.teams.data.response

import com.example.nhlstats.features.teams.data.dto.PlayersRosterDto
import com.google.gson.annotations.SerializedName

data class TeamPlayersResponse(
    @SerializedName("roster")
    val roster: PlayersRosterDto
)