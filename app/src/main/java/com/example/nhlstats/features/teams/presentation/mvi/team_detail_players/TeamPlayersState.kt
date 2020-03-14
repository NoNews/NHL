package com.example.nhlstats.features.teams.presentation.mvi.team_detail_players

import com.example.core_ui.list.mvi.BaseScreenState
import com.example.core_ui.list.ui.delegates.TitleValueItem

data class TeamPlayersState(
    val title: String,
    val players: List<TitleValueItem>
) : BaseScreenState