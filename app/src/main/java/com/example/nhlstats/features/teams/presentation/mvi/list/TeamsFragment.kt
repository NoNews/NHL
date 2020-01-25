package com.example.nhlstats.features.teams.presentation.mvi.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import com.example.core_ui.list.setupWithAdapter
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseFragment
import com.example.nhlstats.features.teams.presentation.mvi.list.adapter.TeamDiffCallback
import com.example.nhlstats.features.teams.presentation.mvi.list.adapter.TeamsListAdapter
import kotlinx.android.synthetic.main.teams_fragment.*

class TeamsFragment : BaseFragment<TeamsViewModel>(TeamsViewModel::class, R.layout.teams_fragment) {


    private lateinit var teamListAdapter: TeamsListAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupUx()
        observeState()
    }

    private fun observeState() {
        viewModel.observeState()
            .observe(this, Observer { teamState ->
                if (teamState.content.isNotEmpty()) {
                    teamListAdapter.submitList(teamState.content)
                }
            })
    }

    private fun setupUx() {
        teamListAdapter.onClickTeam { team -> viewModel.onClickTeam(team) }
    }

    private fun setupUi() {
        teamListAdapter = TeamsListAdapter(
            LayoutInflater.from(context),
            diffCallback = TeamDiffCallback()
        )
        rv_teams.setupWithAdapter(teamListAdapter)
    }

}