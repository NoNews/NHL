package com.example.nhlstats.common.presentation

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.example.core_ui.list.mvi.BaseScreenState
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass


abstract class BaseMviFragment<STATE : BaseScreenState, out VM : BaseViewModel<STATE>>(
    viewModelClass: KClass<VM>,
    @LayoutRes layoutRes: Int
) : BaseFragment(layoutRes) {

    private var dialog: ProgressDialog? = null
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

    fun showFullScreenProgress(show: Boolean) {

//        if (show && dialog?.isShowing == true) {
//            return
//        }
//        if (show) {
//            dialog = ProgressDialog(requireContext())
//            dialog?.show()
//        } else {
//            dialog?.hide()
//            dialog = null
//        }

    }

    abstract fun stateUpdated(state: STATE)


}
