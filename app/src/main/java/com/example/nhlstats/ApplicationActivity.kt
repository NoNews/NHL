package com.example.nhlstats

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nhlstats.features.teams.presentation.mvi.list.TeamsFragment
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity(), Navigator {


    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator: SupportAppNavigator =
        object : SupportAppNavigator(this, supportFragmentManager, android.R.id.content) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, TeamsFragment())
                .commit()
        }
    }

    override fun navigate(fragment: Fragment, params: Parcelable?) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(android.R.id.content, fragment)
            .commit()
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}

interface Navigator {
    fun navigate(fragment: Fragment, params: Parcelable? = null)
}
