package com.example.nhlstats.features.mainscreen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nhlstats.features.standings.StandingsFlowFragment
import com.example.nhlstats.features.teams.TeamsFlowFragment

class MainViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when {
            position == 0 -> StandingsFlowFragment()
            position == 1 -> TeamsFlowFragment()
            else -> error("")
        }
    }

}