package com.example.nhlstats.features.players.presentation

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


interface PlayerContract {

    companion object {
        const val PLAYER_ID_KEY = "player_id"

        fun createScreen(id: Int): SupportAppScreen {
            return object : SupportAppScreen() {
                override fun getFragment(): Fragment {
                    return PlayerFragment.newInstance(id)
                }
            }
        }
    }

}