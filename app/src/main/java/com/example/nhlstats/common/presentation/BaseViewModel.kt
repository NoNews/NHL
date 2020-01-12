package com.example.nhlstats.common.presentation

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private var inited: Boolean = false
    fun onActive() {
        if (!inited) {
            inited = true
            onInited()
        }
    }

    protected abstract fun onInited()
}