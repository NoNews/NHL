package com.example.nhlstats.features.teams.presentation.mvi.teamDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseFragment

class TeamDetailFragment : BaseFragment<TeamDetailViewModel>(
    viewModelClass = TeamDetailViewModel::class,
    layoutRes = R.layout.teams_fragment
) {


    companion object {
        fun newInstance(): Fragment {
            val args = Bundle()
            val fragment = TeamDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}