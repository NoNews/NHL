package com.example.nhlstats.features.teams.presentation.mvi.team_detail_players

import androidx.lifecycle.viewModelScope
import com.example.core_ui.list.ui.delegates.ImageTitleSubtitleDelegate
import com.example.core_ui.list.ui.images.Image
import com.example.nhlstats.common.data.response.*
import com.example.nhlstats.common.presentation.BaseViewModel
import com.example.nhlstats.common.ui.imageUrl
import com.example.nhlstats.features.teams.domain.ShortTeamPlayers
import com.example.nhlstats.features.teams.domain.TeamsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class TeamPlayersViewModel constructor(
    private val teamId: Int,
    private val router: Router,
    private val repository: TeamsRepository
) : BaseViewModel<TeamPlayersState>() {

    override fun onCreated() {
        viewModelScope.launch {
            repository.getPlayers(teamId)
                .collect { teamPlayers ->
                    setupPlayers(teamPlayers)
                }
        }
    }

    private fun setupPlayers(teamPlayers: Data<ShortTeamPlayers>) {
        val state = when {
            teamPlayers.isLoading() -> TeamPlayersState.Loading
            teamPlayers.isError() -> TeamPlayersState.Error(teamPlayers.error!!)
            teamPlayers.contentLoaded() -> {
                val content = teamPlayers.requireContent()
                val teamName = content.teamName
                val players = content.players
                TeamPlayersState.Content(
                    title = teamName,
                    content = players.map { shortPlayer ->
                        ImageTitleSubtitleDelegate.Item(
                            id = shortPlayer.id.toString(),
                            title = shortPlayer.fullName,
                            subtitle = " #" + shortPlayer.jerseyNumber,
                            image = Image.Network(url = shortPlayer.imageUrl())
                        )
                    }
                )
            }
            else -> throw IllegalStateException("Unreachable block of code")
        }
        updateState(state)
    }

    fun onBackPressed() {
        router.exit()
    }


}


