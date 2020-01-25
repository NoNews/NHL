package com.example.nhlstats.features.teams.presentation.mvi.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nhlstats.common.presentation.BaseViewModel
import com.example.nhlstats.features.teams.data.ShortTeam
import com.example.nhlstats.features.teams.domain.TeamsRepository
import com.example.nhlstats.features.teams.presentation.mvi.teamDetail.TeamDetailContract
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class TeamsViewModel(
    private val repository: TeamsRepository,
    private val router: Router
) : BaseViewModel() {

    private val state = MutableLiveData<TeamsState>()
    override fun onInited() {
        getTeams()
    }

    private fun getTeams() {
        viewModelScope.launch {
            state.postValue(TeamsState(progress = true))
            val data = repository.getShortTeams()
            val content = data.content
            val error = data.error
            when {
                content != null -> state.postValue(TeamsState(content = content))
                error != null -> state.postValue(TeamsState(error = error))
            }
        }
    }

    fun observeState(): LiveData<TeamsState> = state

    fun onClickTeam(team: ShortTeam) {
        router.navigateTo(TeamDetailContract.screen)
    }

}