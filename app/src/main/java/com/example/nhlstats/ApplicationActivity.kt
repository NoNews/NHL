package com.example.nhlstats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nhlstats.common.presentation.BaseFragment
import com.example.nhlstats.features.mainscreen.MainScreenContract
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {


    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(android.R.id.content) as? BaseFragment


    private val router: Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator: SupportAppNavigator =
        object : SupportAppNavigator(this, supportFragmentManager, android.R.id.content) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            router.replaceScreen(
                MainScreenContract.createScreen()
            )
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: router.exit()
    }
}


