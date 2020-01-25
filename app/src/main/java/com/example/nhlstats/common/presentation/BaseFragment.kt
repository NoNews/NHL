package com.example.nhlstats.common.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.core_ui.list.mvi.BaseScreenState
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

abstract class BaseFragment<STATE : BaseScreenState, out VM : BaseViewModel<STATE>>(
    viewModelClass: KClass<VM>,
    @LayoutRes layoutRes: Int
) :
    Fragment(layoutRes) {
    protected open val viewModel: VM by lazy {
        getViewModel(
            clazz = viewModelClass,
            qualifier = null,
            parameters = getParameters()
        )
    }


    protected open fun getParameters(): ParametersDefinition? = {
        parametersOf()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onActive()


        viewModel.observeState().observe(
            this,
            Observer(::stateUpdated)
        )

    }

    abstract fun stateUpdated(state: STATE)


}
