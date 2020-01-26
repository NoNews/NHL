package com.example.core_ui.list.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupWithAdapter(adapter: ListAdapter<*, *>) {
    isMotionEventSplittingEnabled = false
    layoutManager = LinearLayoutManager(context)
    this.adapter = adapter
}