package com.example.nhlstats.features.mainscreen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nhlstats.common.presentation.BaseFragment
import com.example.nhlstats.features.teams.TeamsFlowFragment
import com.example.nhlstats.features.teams.presentation.mvi.list.TeamsFragment

class MainViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        return TeamsFlowFragment()
    }

}