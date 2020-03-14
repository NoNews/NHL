package com.example.nhlstats.features.teams.presentation.mvi.team_detail_players

import com.example.core_ui.list.mvi.BaseScreenState
import com.example.core_ui.list.ui.delegates.TitleValueItem


sealed class TeamPlayersState : BaseScreenState {
    object Loading : TeamPlayersState()
    data class Error(val throwable: Throwable) : TeamPlayersState()
    data class Content(
        val title: String,
        val content: List<TitleValueItem>
    ) : TeamPlayersState()
}

