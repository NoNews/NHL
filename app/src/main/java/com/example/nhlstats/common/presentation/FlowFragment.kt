package com.example.nhlstats.common.presentation

import android.content.Context
import android.os.Bundle
import com.example.nhlstats.R
import org.koin.android.ext.android.getKoin
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

abstract class FlowFragment() : BaseFragment(R.layout.flow_fragment) {

    private val currentFragment get() = childFragmentManager.findFragmentById(R.id.lt_container) as? BaseFragment


    private lateinit var navigatorHolder: NavigatorHolder
    protected lateinit var router: Router
    private lateinit var navigator: SupportAppNavigator


    abstract fun flowName(): String

    private val flowModule = module {
        val cicreone = Cicerone.create()
        factory(named(flowName())) { cicreone.router }
        factory(named(flowName())) { cicreone.navigatorHolder }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = SupportAppNavigator(activity, childFragmentManager, R.id.lt_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            loadKoinModules(flowModule)
        }
        router = getKoin().get(named(flowName()))
        navigatorHolder = getKoin().get(named(flowName()))
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed()
    }

    override fun onDestroy() {
        if (activity?.isFinishing == true) {
            unloadKoinModules(flowModule)
        }
        super.onDestroy()
    }

}