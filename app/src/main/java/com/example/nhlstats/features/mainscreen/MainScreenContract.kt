package com.example.nhlstats.features.mainscreen

import androidx.fragment.app.Fragment
import com.example.nhlstats.features.mainscreen.ui.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

interface MainScreenContract {

    companion object {
        fun createScreen(): SupportAppScreen {
            return object : SupportAppScreen() {
                override fun getFragment(): Fragment {
                    return MainFragment()
                }
            }
        }
    }

}