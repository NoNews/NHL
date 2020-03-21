package com.example.nhlstats.features.teams.presentation.mvi.list

import androidx.lifecycle.viewModelScope
import com.example.core_ui.list.ui.delegates.ImageTitleSubtitleDelegate
import com.example.core_ui.list.ui.images.Image
import com.example.nhlstats.common.data.response.Data
import com.example.nhlstats.common.presentation.BaseViewModel
import com.example.nhlstats.common.ui.toImageRes
import com.example.nhlstats.features.teams.domain.ShortTeam
import com.example.nhlstats.features.teams.domain.TeamsRepository
import com.example.nhlstats.features.teams.presentation.mvi.team_detail.TeamDetailContract
import kotlinx.coroutines.flow.collect
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
            repository
                .getTeams()
                .collect { data -> resolveState(data) }
        }
    }

    private fun resolveState(data: Data<List<ShortTeam>>) {
        val content = data.content
        val loading = data.loading
        val error = data.error

        when {
            content != null -> {
                val items = content.map { team ->
                    ImageTitleSubtitleDelegate.Item(
                        id = team.id.toString(),
                        title = team.name,
                        subtitle = team.fistYearOfPlay,
                        image = Image.Resource(team.toImageRes()),
                        payload = team.id
                    )
                }
                updateState(TeamsState(content = items))
            }
            loading -> updateState(TeamsState(progress = true))
            error != null -> updateState(TeamsState(error = error))
        }
    }

    fun onClickTeam(team: ImageTitleSubtitleDelegate.Item) {
        val id = (team.payload as Int)
        val screen = TeamDetailContract.createScreen(teamId = id)
        router.navigateTo(screen)
    }

}
