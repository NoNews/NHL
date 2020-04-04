package com.example.nhlstats.common.presentation

import android.content.Context
import android.os.Bundle
import com.example.nhlstats.R
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

abstract class FlowFragment() : BaseFragment(R.layout.flow_fragment) {

    private val currentFragment get() = childFragmentManager.findFragmentById(R.id.lt_container) as? BaseFragment


    private val navigatorHolder: NavigatorHolder by inject(named(this.flowName()))
    protected val router: Router by inject(named(this.flowName()))
    private lateinit var navigator: SupportAppNavigator


    abstract fun flowName(): String


    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = SupportAppNavigator(activity, childFragmentManager, R.id.lt_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scope = getKoin().getOrCreateScope(flowName(), named(flowName()))

        val cicerone = Cicerone.create()
        scope.declare(cicerone.router)
        scope.declare(cicerone.navigatorHolder)


//        loadKoinModules(
//            module {
//                val cicerone = Cicerone.create()
//                factory(
//                    FlowKey.router(
//                        flowName()
//                    )
//                ) {
//                    cicerone.router
//                }
//                factory(
//                    FlowKey.navigationHolder(
//                        flowName()
//                    )
//                ) {
//                    cicerone.navigatorHolder
//                }
//            }
//        )
    }


    override fun onDetach() {
        super.onDetach()
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

}