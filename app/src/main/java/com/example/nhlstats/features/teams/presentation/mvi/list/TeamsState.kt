package com.example.nhlstats.features.teams.presentation.mvi.list

import com.example.core_ui.list.mvi.BaseScreenState
import com.example.core_ui.list.ui.delegates.TitleValueItem

data class TeamsState(
    val progress: Boolean = false,
    val content: List<TitleValueItem>? = null,
    val error: Throwable? = null
) : BaseScreenState
