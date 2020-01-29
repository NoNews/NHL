package com.example.nhlstats.features.teams.presentation.mvi.team_detail

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

interface TeamDetailContract {

    companion object {


        const val TEAM_ID_KEY = "team_id"

        fun createScreen(teamId: Int): SupportAppScreen {
            return object : SupportAppScreen() {
                override fun getFragment(): Fragment {
                    return TeamDetailFragment.newInstance(teamId)
                }
            }
        }

    }

}