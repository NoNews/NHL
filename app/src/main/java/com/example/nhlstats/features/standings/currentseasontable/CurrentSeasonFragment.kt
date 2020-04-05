package com.example.nhlstats.features.standings.currentseasontable

import android.os.Bundle
import android.view.View
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseMviFragment

class CurrentSeasonFragment : BaseMviFragment<CurrentSeasonState, CurrentSeasonViewModel>(
    CurrentSeasonViewModel::class,
    R.layout.current_season_fragment
) {

    companion object {
        fun newInstance() = CurrentSeasonFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun stateUpdated(state: CurrentSeasonState) {

    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

}