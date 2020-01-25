package com.example.nhlstats.common.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<out VM : BaseViewModel>(
    viewModelClass: KClass<VM>,
    @LayoutRes layoutRes: Int
) :
    Fragment(layoutRes) {
    protected open val viewModel: VM by lazy { getViewModel<VM>(viewModelClass) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onActive()
    }


}