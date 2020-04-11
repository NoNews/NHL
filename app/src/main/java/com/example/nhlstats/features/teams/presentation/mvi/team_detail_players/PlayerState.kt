package com.example.nhlstats.features.teams.presentation.mvi.team_detail_players

import com.example.core_ui.list.mvi.BaseScreenState
import com.example.core_ui.list.ui.delegateadapter.ListItem


sealed class PlayerState : BaseScreenState {
    object Loading : PlayerState()
    data class Error(val throwable: Throwable) : PlayerState()
    data class Content(
        val title: String,
        val content: List<ListItem>
    ) : PlayerState()
}

