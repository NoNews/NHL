package com.example.nhlstats.common.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_ui.list.mvi.BaseScreenState

abstract class BaseViewModel<T : BaseScreenState> : ViewModel() {

    private val liveData = MutableLiveData<T>()

    private var inited: Boolean = false
    fun onActive() {
        if (!inited) {
            inited = true
            onCreated()
        }
    }

    fun observeState(): LiveData<T> = liveData

    protected abstract fun onCreated()

    protected fun updateState(state: T) {
        liveData.postValue(state)
    }
}