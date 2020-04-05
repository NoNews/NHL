package com.example.nhlstats.features.mainscreen.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.nhlstats.R
import com.example.nhlstats.common.presentation.BaseFragment
import com.example.nhlstats.features.mainscreen.MainViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Router


class MainFragment : BaseFragment(R.layout.main_fragment),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var adapter: MainViewPagerAdapter

    private val router: Router by inject()

    private val currentTabFragment: BaseFragment?
        get() = childFragmentManager.fragments.firstOrNull { it.isVisible } as? BaseFragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_bar.setOnNavigationItemSelectedListener(this)

        adapter = MainViewPagerAdapter(this)
        
        view_pager.adapter = adapter
        view_pager.isUserInputEnabled = false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu_standings -> {
                view_pager.setCurrentItem(0, false)
            }

            R.id.menu_teams -> {
                view_pager.setCurrentItem(1, false)
            }
        }
        return true
    }


    override fun onBackPressed() {
        currentTabFragment?.onBackPressed() ?: super.onBackPressed()
    }

}