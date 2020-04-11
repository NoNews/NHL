package com.example.nhlstats.features.players.presentation

import android.os.Bundle
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseMviFragment
import com.example.nhlstats.features.teams.presentation.mvi.team_detail_players.PlayerState
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf

class PlayerFragment : BaseMviFragment<PlayerState, PlayerViewModel>(
    PlayerViewModel::class,
    R.layout.player_fragment
) {
    companion object {
        fun newInstance(playerId: Int): PlayerFragment {
            val args = Bundle()
            args.putInt(PlayerContract.PLAYER_ID_KEY, playerId)
            val fragment = PlayerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getParameters(): ParametersDefinition? = {
        val playerId = arguments?.getInt(PlayerContract.PLAYER_ID_KEY, 0)
        parametersOf(playerId)
    }

    override fun stateUpdated(state: PlayerState) {

    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

}