package com.example.nhlstats.features.standings.teamstanding

import android.os.Bundle
import android.view.View
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseMviFragment

class TeamStandingFragment : BaseMviFragment<TeamStandingState, TeamStandingViewModel>(
    TeamStandingViewModel::class,
    R.layout.team_standing_fragment
) {

    companion object {
        fun newInstance() = TeamStandingFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun stateUpdated(state: TeamStandingState) {

    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

}