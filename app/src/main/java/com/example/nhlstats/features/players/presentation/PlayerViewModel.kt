package com.example.nhlstats.features.players.presentation

import com.example.nhlstats.common.presentation.BaseViewModel
import com.example.nhlstats.features.teams.presentation.mvi.team_detail_players.PlayerState
import ru.terrakok.cicerone.Router

class PlayerViewModel(private val id: Int, private val router: Router) :
    BaseViewModel<PlayerState>() {

    override fun onCreated() {

    }

    fun onBackPressed() {
        router.exit()
    }

}