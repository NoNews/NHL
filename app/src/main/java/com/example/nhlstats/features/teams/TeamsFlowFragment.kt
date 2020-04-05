package com.example.nhlstats.features.teams

import android.os.Bundle
import com.example.nhlstats.FlowKey
import com.example.nhlstats.common.presentation.FlowFragment
import com.example.nhlstats.features.teams.presentation.mvi.list.TeamsContract

class TeamsFlowFragment : FlowFragment() {

    override fun flowName(): String = FlowKey.TEAMS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            router.replaceScreen(TeamsContract.createScreen())
        }
    }
}

