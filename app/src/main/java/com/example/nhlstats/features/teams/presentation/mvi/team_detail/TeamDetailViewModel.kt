package com.example.nhlstats.features.teams.presentation.mvi.team_detail

import androidx.lifecycle.viewModelScope
import com.example.nhlstats.common.data.response.requireContent
import com.example.nhlstats.common.presentation.BaseViewModel
import com.example.nhlstats.features.teams.domain.TeamsRepository
import com.example.nhlstats.features.teams.presentation.mvi.team_detail_players.TeamPlayersContract
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class TeamDetailViewModel(
    private val repository: TeamsRepository,
    private val teamId: Int,
    private val router: Router
) :
    BaseViewModel<TeamDetailState>() {

    override fun onCreated() {
        viewModelScope.launch {
            repository.getTeams()
                .filter { it.content != null }
                .map { data ->
                    data.requireContent().find { team ->
                        team.id == teamId
                    }
                }
                .collect { team ->
                    updateState(TeamDetailState(title = team!!.name))
                }
        }
    }

    fun onClickPlayers() {
        val screen = TeamPlayersContract.createScreen(teamId)
        router.navigateTo(screen)
    }

    fun onBackPressed() {
        router.exit()
    }

    override fun onCleared() {
        super.onCleared()
    }
}