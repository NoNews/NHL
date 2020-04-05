package com.example.nhlstats.features.teams.presentation.mvi.team_detail_players

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.core_ui.list.ui.delegateadapter.DelegatesAdapter
import com.example.core_ui.list.ui.delegateadapter.DelegatesManager
import com.example.core_ui.list.ui.delegates.ImageTitleSubtitleDelegate
import com.example.core_ui.list.ui.delegates.TitleDelegate
import com.example.core_ui.list.ui.setupNavigationButton
import com.example.core_ui.list.ui.setupWithAdapter
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseMviFragment
import com.example.nhlstats.features.teams.presentation.mvi.team_detail.TeamDetailContract
import kotlinx.android.synthetic.main.team_detail_fragment.*
import kotlinx.android.synthetic.main.team_players_fragment.*
import kotlinx.android.synthetic.main.team_players_fragment.toolbar
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf

class TeamPlayersFragment :
    BaseMviFragment<TeamPlayersState, TeamPlayersViewModel>(
        TeamPlayersViewModel::class,
        R.layout.team_players_fragment
    ) {

    companion object {
        fun newInstance(teamId: Int): Fragment {
            val args = Bundle()
            args.putInt(TeamPlayersContract.TEAM_ID_KEY, teamId)
            val fragment = TeamPlayersFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val adapter = DelegatesAdapter(
        DelegatesManager().apply {
            addDelegate(TitleDelegate())
            addDelegate(ImageTitleSubtitleDelegate())
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_players.setupWithAdapter(adapter)
        toolbar.setupNavigationButton()
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }


    override fun getParameters(): ParametersDefinition? = {
        val teamId = arguments?.getInt(TeamDetailContract.TEAM_ID_KEY, 0)
        parametersOf(teamId)
    }

    override fun stateUpdated(state: TeamPlayersState) {
        when (state) {
            is TeamPlayersState.Content -> {
                showFullScreenProgress(false)
                toolbar.title = state.title
                adapter.submitList(state.content)
            }
            is TeamPlayersState.Error -> {
                showFullScreenProgress(false)
            }

            is TeamPlayersState.Loading -> showFullScreenProgress(true)
        }
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }
}