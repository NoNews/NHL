package com.example.core_ui.list.ui

import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core_ui.R

fun RecyclerView.setupWithAdapter(adapter: ListAdapter<*, *>) {
    isMotionEventSplittingEnabled = false
    layoutManager = LinearLayoutManager(context)
    this.adapter = adapter
}


fun Toolbar.setupNavigationButton() {
    setNavigationIcon(R.drawable.ic_arrow_left)
}