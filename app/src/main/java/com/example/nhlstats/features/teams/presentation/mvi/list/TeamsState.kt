package com.example.nhlstats.features.teams.presentation.mvi.list

import com.example.core_ui.list.ui.delegateadapter.ListItem
import com.example.core_ui.list.mvi.BaseScreenState

data class TeamsState(
    val progress: Boolean = false,
    val content: List<ListItem>? = null,
    val error: Throwable? = null
) : BaseScreenState
