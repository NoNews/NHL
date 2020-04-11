package com.example.nhlstats.features.teams.presentation.mvi.team_detail_players

import android.view.Gravity
import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.example.core_ui.list.ui.delegateadapter.ListItem
import com.example.core_ui.list.ui.delegates.ImageTitleSubtitleDelegate
import com.example.core_ui.list.ui.delegates.TitleDelegate
import com.example.core_ui.list.ui.images.Image
import com.example.core_ui.list.ui.text.Text
import com.example.core_ui.list.ui.text.asText
import com.example.nhlstats.R
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

                val positionName = Text.Resource(getGroupName(type))
                items += TitleDelegate.Item(
                    id = positionName.resource.toString(),
                    title = positionName,
                    gravity = Gravity.START
                )
            }
            items += ImageTitleSubtitleDelegate.Item(
                id = shortPlayer.id.toString(),
                title = shortPlayer.fullName.asText(),
                subtitle = getSubtitle(shortPlayer),
                image = Image.Network(url = shortPlayer.imageUrl())
            )
        }
        return items
    }

    private fun getSubtitle(shortPlayer: ShortPlayer): Text {
        return Text.Multi(
            listOfNotNull(
                "#".asText(),
                shortPlayer.jerseyNumber?.asText(),
                " ".asText(),
                getForwardTypeText(shortPlayer.positionInfo)
            )
        )
    }

    private fun getForwardTypeText(positionInfo: PositionInfo): Text? =
        if (positionInfo is PositionInfo.Forward) {
            Text.Resource(getForwardType(positionInfo.forwardType))
        } else {
            null
        }


    @StringRes
    fun getForwardType(type: ForwardType) =
        when (type) {
            ForwardType.RIGHT_WING -> R.string.forward_right_wing
            ForwardType.LEFT_WING -> R.string.forward_left_wing
            ForwardType.CENTER -> R.string.forward_center
        }


    @StringRes
    private fun getGroupName(type: PositionType) = when (type) {
        PositionType.FORWARD -> R.string.group_forwards
        PositionType.DEFENSEMAN -> R.string.group_defencemen
        PositionType.GOALIE -> R.string.group_goalies
    }

    fun onBackPressed() {
        router.exit()
    }


}


