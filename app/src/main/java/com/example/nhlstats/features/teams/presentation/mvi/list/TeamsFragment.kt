package com.example.nhlstats.features.teams.presentation.mvi.list

import android.os.Bundle
import android.view.View
import com.example.core_ui.list.ui.delegateadapter.DelegatesAdapter
import com.example.core_ui.list.ui.delegateadapter.DelegatesManager
import com.example.core_ui.list.ui.delegates.ImageTitleSubtitleDelegate
import com.example.core_ui.list.ui.setupWithAdapter
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseFragment
import kotlinx.android.synthetic.main.teams_fragment.*

class TeamsFragment :
    BaseFragment<TeamsState, TeamsViewModel>(TeamsViewModel::class, R.layout.teams_fragment) {

    private val adapter: DelegatesAdapter =
        DelegatesAdapter(
            DelegatesManager().apply {
                addDelegate(
                    ImageTitleSubtitleDelegate(
                        onClick = { team ->
                            viewModel.onClickTeam(team)
                        }
                    )
                )
            }
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_teams.setupWithAdapter(adapter)
    }

    override fun stateUpdated(state: TeamsState) {
        when {
            state.progress -> {
                showFullScreenProgress(true)
            }
            state.content != null -> {
                showFullScreenProgress(false)
                adapter.submitList(state.content)
            }
        }
    }

}