package com.example.nhlstats.features.teams.presentation.mvi.list

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.core_ui.list.ui.delegateadapter.DelegatesAdapter
import com.example.core_ui.list.ui.delegateadapter.DelegatesManager
import com.example.core_ui.list.ui.delegates.ImageTitleSubtitleDelegate
import com.example.core_ui.list.ui.setupWithAdapter
import com.example.nhlstats.FlowKey
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseMviFragment
import kotlinx.android.synthetic.main.teams_fragment.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

class TeamsFragment :
    BaseMviFragment<TeamsState, TeamsViewModel>(TeamsViewModel::class, R.layout.teams_fragment) {


    companion object {
        fun newInstance(): TeamsFragment = TeamsFragment()
    }


    private val screenModule = module {
        viewModel {
            TeamsViewModel(
                get(), get(
                    FlowKey.router(
                        "TeamsFlow"
                    )
                )
            )
        }
    }

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


    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(screenModule)
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

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

    override fun onDetach() {
        super.onDetach()
        unloadKoinModules(screenModule)
    }
}