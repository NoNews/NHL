package com.example.nhlstats.features.teams.presentation.mvi.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.core_ui.list.ui.setupWithAdapter
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseFragment
import com.example.nhlstats.features.teams.domain.ShortTeam
import com.example.nhlstats.features.teams.presentation.mvi.list.adapter.TitleValueHolderAdapter
import com.example.nhlstats.features.teams.presentation.mvi.list.adapter.TitleValueHolderDiffUtil
import kotlinx.android.synthetic.main.teams_fragment.*

class TeamsFragment :
    BaseFragment<TeamsState, TeamsViewModel>(TeamsViewModel::class, R.layout.teams_fragment) {


    private lateinit var teamListAdapter: TitleValueHolderAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupUx()
    }

    override fun stateUpdated(state: TeamsState) {
        when {
            state.progress -> {
                showFullScreenProgress(true)
            }
            state.content != null -> {
                showFullScreenProgress(false)
                teamListAdapter.submitList(state.content)
            }
        }
    }

    private fun setupUx() {
        teamListAdapter.onClickTeam { team -> viewModel.onClickTeam(team?.payload as ShortTeam) }
    }

    private fun setupUi() {
        teamListAdapter = TitleValueHolderAdapter(
            LayoutInflater.from(context),
            diffCallback = TitleValueHolderDiffUtil()
        )
        rv_teams.setupWithAdapter(teamListAdapter)
    }


}