package com.example.core_ui.list.ui.delegateadapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView

class DelegatesManager() {

    private val delegates = SparseArray<ItemDelegate<out ListItem, out BaseViewHolder>>()

    fun addDelegate(delegate: ItemDelegate<out ListItem, out BaseViewHolder>): DelegatesManager {
        delegates[delegate.getViewType()] = delegate
        return this
    }

    fun getViewTypeByItem(item: ListItem): Int {
        delegates.forEach { key, itemDelegate ->
            val canBeAttachedToItem = itemDelegate.canBeAttachedToItem(item)
            if (canBeAttachedToItem) {
                return key
            }
        }

        throw IllegalStateException("You can't attach ")
    }

    fun createItemViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val delegate = delegates[viewType]
        return delegate.onCreateViewHolder(parent)
    }

    @Suppress("unchecked_cast")
    fun bindItem(holder: BaseViewHolder, item: ListItem) {
        val key = getViewTypeByItem(item)
        val delegate = delegates[key] as ItemDelegate<ListItem, RecyclerView.ViewHolder>
        delegate.bindItem(item, holder)
    }


}