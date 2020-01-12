package com.example.nhlstats.features.teams.presentation.mvi.list

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.withOpacity
import androidx.ui.tooling.preview.Preview
import com.example.nhlstats.R
import com.example.nhlstats.VectorImage
import com.example.nhlstats.common.presentation.BaseFragment
import com.example.nhlstats.features.teams.data.ShortTeam
import com.example.nhlstats.features.teams.presentation.mvi.teamDetail.TeamDetailFragment

class TeamsFragment : BaseFragment<TeamsViewModel>(TeamsViewModel::class) {

    override fun drawComposeView() {
        TeamsScreen(state = viewModel.state)
    }


    @Composable
    private fun TeamsScreen(state: TeamsState) {

        if (state.isDefault()) {
            return
        }

        FlexColumn {
            inflexible {
                TopAppBar(
                    title = { Text("Teams") },
                    navigationIcon = { R.drawable.ic_launcher_background })
            }
            if (state.progress) {
                Center {
                    CircularProgressIndicator()
                }
            } else {
                flexible(flex = 1f) {
                    createTeams(
                        list = requireNotNull(
                            state.data
                        )
                    )
                }
            }
        }
    }


    @Composable
    fun createTeams(list: List<ShortTeam>) {
        VerticalScroller {
            Column {
                list.forEach { team ->
                    createTeamRow(team = team)
                }
            }
        }
    }

    @Composable
    fun createTeamRow(team: ShortTeam) {
        val typography = +MaterialTheme.typography()

        Clickable(onClick = {
            navigate(TeamDetailFragment())
        }) {
            //            Ripple(bounded = true) {
            Padding(left = 16.dp, right = 16.dp, top = 8.dp, bottom = 8.dp) {
                Row(arrangement = Arrangement.SpaceBetween) {
                    VectorImage(
                        id = R.drawable.ic_capitals,
                        modifier = Spacing(right = 16.dp)
                    )
                    Column(
                        modifier = Flexible(1f)
                            .wraps(Gravity.Center)
                    ) {
                        Text(
                            text = team.name,
                            style = typography.h6
                        )
                        Text(
                            text = team.fistYearOfPlay,
                            style = typography.subtitle1
                                .withOpacity(0.37f)
                        )
                    }
                }
            }
//            }
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        MaterialTheme {
            TeamsScreen(
                state = TeamsState(
                    progress = true
                )
            )
        }
    }
}