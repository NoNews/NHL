package com.example.nhlstats.features.teams.presentation.mvi.list

import androidx.fragment.app.Fragment
import com.example.nhlstats.features.teams.presentation.mvi.list.TeamsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

interface TeamsContract {

    companion object {
        fun createScreen(): SupportAppScreen {
            return object : SupportAppScreen() {
                override fun getFragment(): Fragment = TeamsFragment.newInstance()
            }
        }
    }

}