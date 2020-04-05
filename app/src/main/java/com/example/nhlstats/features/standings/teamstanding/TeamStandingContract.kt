package com.example.nhlstats.features.standings.teamstanding

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

interface TeamStandingContract {

    companion object {
        fun createScreen(): SupportAppScreen {
            return object : SupportAppScreen() {
                override fun getFragment(): Fragment = TeamStandingFragment.newInstance()
            }
        }
    }
}