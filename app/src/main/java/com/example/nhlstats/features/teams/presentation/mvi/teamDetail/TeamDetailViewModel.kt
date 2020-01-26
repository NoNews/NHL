package com.example.nhlstats.features.teams.presentation.mvi.teamDetail

import androidx.lifecycle.viewModelScope
import com.example.nhlstats.common.presentation.BaseViewModel
import com.example.nhlstats.features.teams.domain.TeamsRepository
import kotlinx.coroutines.launch

class TeamDetailViewModel(
    private val repository: TeamsRepository,
    private val teamId: Int
) :
    BaseViewModel<TeamDetailState>() {

    override fun onCreated() {

        viewModelScope.launch {
            val data = repository.getTeam(teamId)
            if (data.content != null) {
                val content = data.content

                updateState(TeamDetailState(title = content.name))
            }
        }
    }

    fun onClickPlayers() {
    }
}