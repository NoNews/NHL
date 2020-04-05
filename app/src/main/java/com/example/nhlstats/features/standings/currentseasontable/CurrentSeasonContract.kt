package com.example.nhlstats.features.standings.currentseasontable

import androidx.fragment.app.Fragment
import com.example.nhlstats.features.teams.presentation.mvi.list.TeamsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

interface CurrentSeasonContract {

    companion object {
        fun createScreen(): SupportAppScreen {
            return object : SupportAppScreen() {
                override fun getFragment(): Fragment = CurrentSeasonFragment.newInstance()
            }
        }
    }

}