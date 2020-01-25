package com.example.nhlstats.features.teams.presentation.mvi.teamDetail

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

interface TeamDetailContract {

    companion object {
        val screen = object : SupportAppScreen() {
            override fun getFragment(): Fragment {
                return TeamDetailFragment.newInstance()
            }
        }
    }

}