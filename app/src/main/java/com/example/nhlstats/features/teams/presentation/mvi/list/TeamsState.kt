package com.example.nhlstats.features.teams.presentation.mvi.list

import com.example.core_ui.list.mvi.BaseScreenState
import com.example.nhlstats.features.teams.domain.ShortTeam

data class TeamsState(
    val progress: Boolean = false,
    val content: List<ShortTeam>? = null,
    val error: Throwable? = null
) : BaseScreenState
