package com.example.nhlstats.features.teams.presentation.mvi.list

import com.example.nhlstats.features.teams.data.ShortTeam

data class TeamsState(
    val progress: Boolean = false,
    val content: List<ShortTeam> = emptyList(),
    val error: Throwable? = null
)
