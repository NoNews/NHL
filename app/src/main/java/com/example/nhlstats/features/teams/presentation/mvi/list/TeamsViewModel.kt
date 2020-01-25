package com.example.nhlstats.features.teams.presentation.mvi.list

import androidx.lifecycle.viewModelScope
import com.example.nhlstats.common.presentation.BaseViewModel
import com.example.nhlstats.features.teams.domain.ShortTeam
import com.example.nhlstats.features.teams.domain.TeamsRepository
import com.example.nhlstats.features.teams.presentation.mvi.teamDetail.TeamDetailContract
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class TeamsViewModel(
    private val repository: TeamsRepository,
    private val router: Router
) : BaseViewModel<TeamsState>() {


    override fun onCreated() {
        getTeams()
    }

    private fun getTeams() {
        viewModelScope.launch {
            updateState(TeamsState(progress = true))
            val data = repository.getTeams()
            val content = data.content
            val error = data.error
            when {
                content != null -> updateState(TeamsState(content = content))
                error != null -> updateState(TeamsState(error = error))
            }
        }
    }

    fun onClickTeam(team: ShortTeam) {
        val screen = TeamDetailContract.createScreen(teamId = team.id)
        router.navigateTo(screen)
    }

}