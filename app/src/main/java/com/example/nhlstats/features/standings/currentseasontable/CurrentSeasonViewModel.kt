package com.example.nhlstats.features.standings.currentseasontable

import com.example.nhlstats.common.presentation.BaseViewModel
import com.example.nhlstats.features.standings.teamstanding.TeamStandingContract
import ru.terrakok.cicerone.Router

class CurrentSeasonViewModel(private val router: Router) : BaseViewModel<CurrentSeasonState>() {


    override fun onCreated() {

    }


    fun onTeamClicked() {
        router.navigateTo(TeamStandingContract.createScreen())
    }

    fun onBackPressed() {
        router.exit()
    }
}