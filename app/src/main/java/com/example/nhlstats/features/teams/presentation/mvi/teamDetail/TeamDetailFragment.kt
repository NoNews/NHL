package com.example.nhlstats.features.teams.presentation.mvi.teamDetail

import androidx.ui.core.Text
import androidx.ui.layout.FlexColumn
import androidx.ui.material.TopAppBar
import com.example.nhlstats.common.presentation.BaseFragment
import org.koin.android.ext.android.getKoin
import org.koin.core.Koin

class TeamDetailFragment : BaseFragment<TeamDetailViewModel>(TeamDetailViewModel::class) {

    override fun drawComposeView() {
        val state = viewModel.state
        TeamDetailScreen(state = state)
    }

    private fun TeamDetailScreen(state: TeamDetailState) {
        FlexColumn {
            inflexible {
                TopAppBar(title = { Text("TeamDetail") })
            }
//            if (state.progress) {
//                Center {
//                    CircularProgressIndicator()
//                }
//            } else {
            flexible(flex = 1f) {
//                createTeams(
//                    list = requireNotNull(
//                        state.data
//                    )
//                )
            }
//            }
        }
    }
}