package com.example.nhlstats.features.teams.presentation.mvi.teamDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseFragment
import kotlinx.android.synthetic.main.team_detail_fragment.*
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf


class TeamDetailFragment : BaseFragment<TeamDetailState, TeamDetailViewModel>(
    viewModelClass = TeamDetailViewModel::class,
    layoutRes = R.layout.team_detail_fragment
) {


    companion object {
        fun newInstance(teamId: Int): Fragment {
            val args = Bundle()
            args.putInt(TeamDetailContract.TEAM_ID_KEY, teamId)
            val fragment = TeamDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getParameters(): ParametersDefinition? = {
        val teamId = arguments?.getInt(TeamDetailContract.TEAM_ID_KEY, 0)
        parametersOf(teamId)
    }

    override fun stateUpdated(state: TeamDetailState) {
        toolbar.title = state.title
    }


}