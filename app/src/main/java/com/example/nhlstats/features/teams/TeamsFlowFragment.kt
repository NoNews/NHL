package com.example.nhlstats.features.teams

import android.os.Bundle
import com.example.nhlstats.common.presentation.FlowFragment
import com.example.nhlstats.features.teams.presentation.mvi.list.TeamsContract

class TeamsFlowFragment : FlowFragment() {

    override fun flowName(): String = "TeamsFlow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            router.navigateTo(TeamsContract.createScreen())
        }
    }
}

