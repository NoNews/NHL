package com.example.nhlstats.features.standings.teamstanding

import com.example.nhlstats.common.presentation.BaseViewModel
import ru.terrakok.cicerone.Router

class TeamStandingViewModel(private val router: Router) : BaseViewModel<TeamStandingState>() {

    override fun onCreated() {

    }


    fun onTeamClicked() {

    }

    fun onBackPressed() {
        router.exit()
    }
}