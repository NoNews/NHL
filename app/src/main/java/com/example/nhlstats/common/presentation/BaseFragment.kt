package com.example.nhlstats.common.presentation

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    open fun onBackPressed() {

    }
}