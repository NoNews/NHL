package com.example.nhlstats.features.teams.presentation.mvi.list

import androidx.lifecycle.viewModelScope
import com.example.nhlstats.common.presentation.BaseViewModel
import com.example.nhlstats.features.teams.domain.TeamsRepository
import kotlinx.coroutines.launch

class TeamsViewModel(private val repository: TeamsRepository) : BaseViewModel() {

    val state = TeamsState()
    override fun onInited() {
        viewModelScope.launch {
            state.rendredProgress()
            val data = repository.getShortTeams()
            val content = data.content
            val error = data.error
            when {
                content != null -> state.renderContent(content)
                error != null -> state.renderError(error)
            }
        }
    }


}