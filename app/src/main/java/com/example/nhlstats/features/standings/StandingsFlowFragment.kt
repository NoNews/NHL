package com.example.nhlstats.features.standings

import android.os.Bundle
import com.example.nhlstats.FlowKey
import com.example.nhlstats.common.presentation.FlowFragment
import com.example.nhlstats.features.standings.currentseasontable.CurrentSeasonContract

class StandingsFlowFragment : FlowFragment() {
    override fun flowName(): String = FlowKey.STANDINGS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            router.replaceScreen(CurrentSeasonContract.createScreen())
        }

    }
}