package com.example.nhlstats.features.teams.presentation.mvi.team_detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nhlstats.FlowKey
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseMviFragment
import kotlinx.android.synthetic.main.team_detail_fragment.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module


class TeamDetailFragment : BaseMviFragment<TeamDetailState, TeamDetailViewModel>(
    viewModelClass = TeamDetailViewModel::class,
    layoutRes = R.layout.team_detail_fragment
) {


    private val screenModule = module {
        viewModel { (teamId: Int) ->
            TeamDetailViewModel(
                get(),
                teamId,
                get(
                    FlowKey.router(
                        "TeamsFlow"
                    )
                )
            )
        }


    }

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

    override fun onAttach(context: Context) {
        super.onAttach(context)

        loadKoinModules(screenModule)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_players.setOnClickListener {
            viewModel.onClickPlayers()
        }
    }

    override fun stateUpdated(state: TeamDetailState) {
        toolbar.title = state.title
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }


    override fun onDetach() {
        super.onDetach()
        unloadKoinModules(screenModule)
    }


}