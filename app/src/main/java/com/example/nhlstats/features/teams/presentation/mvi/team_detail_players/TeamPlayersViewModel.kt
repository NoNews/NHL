package com.example.nhlstats.features.teams.presentation.mvi.team_detail_players

import android.view.Gravity
import androidx.lifecycle.viewModelScope
import com.example.core_ui.list.ui.delegateadapter.ListItem
import com.example.core_ui.list.ui.delegates.ImageTitleSubtitleDelegate
import com.example.core_ui.list.ui.delegates.TitleDelegate
import com.example.core_ui.list.ui.images.Image
import com.example.nhlstats.common.data.response.*
import com.example.nhlstats.common.presentation.BaseViewModel
import com.example.nhlstats.common.ui.imageUrl
import com.example.nhlstats.features.teams.domain.*
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
                updateContent(teamPlayers)
            }
            else -> throw IllegalStateException("Unreachable block of code")
        }
        updateState(state)
    }

    private fun updateContent(teamPlayers: Data<ShortTeamPlayers>): TeamPlayersState.Content {
        val content = teamPlayers.requireContent()
        val teamName = content.teamName
        val players = content.players.sortedBy { it.positionInfo.type }
        return TeamPlayersState.Content(
            title = teamName,
            content = getContent(players)
        )
    }

    private fun getContent(players: List<ShortPlayer>): List<ListItem> {
        val items = mutableListOf<ListItem>()


        var currentType: PositionType? = null
        players.forEach { shortPlayer ->
            val type = shortPlayer.positionInfo.type
            if (currentType != type) {
                currentType = type

                val positionName = getPositionName(type)
                items += TitleDelegate.Item(
                    id = positionName,
                    title = positionName,
                    gravity = Gravity.START
                )
            }
            items += ImageTitleSubtitleDelegate.Item(
                id = shortPlayer.id.toString(),
                title = shortPlayer.fullName,
                subtitle = getSubtitle(shortPlayer),
                image = Image.Network(url = shortPlayer.imageUrl())
            )
        }
        return items
    }

    private fun getSubtitle(shortPlayer: ShortPlayer): String {

        val builder = StringBuilder()
        builder.append(" #")
        builder.append(shortPlayer.jerseyNumber)

        val positionInfo = shortPlayer.positionInfo
        if (positionInfo is PositionInfo.Forward) {
            builder.append(", ")
            val forwardType = when (positionInfo.forwardType) {
                ForwardType.RIGHT_WING -> "Right wing"
                ForwardType.LEFT_WING -> "Left wing"
                ForwardType.CENTER -> "Center"
            }
            builder.append(forwardType)
        }
        return builder.toString()
    }


    private fun getPositionName(type: PositionType) = when (type) {
        PositionType.FORWARD -> "Forwards"
        PositionType.DEFENSEMAN -> "Defensemen"
        PositionType.GOALIE -> "Goalies"
    }

    fun onBackPressed() {
        router.exit()
    }


}


