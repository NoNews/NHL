package com.example.core_ui.list.ui.delegateadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ItemDelegate<T : ListItem, H> where H : RecyclerView.ViewHolder {
    fun getViewType(): Int
    fun canBeAttachedToItem(item: ListItem): Boolean
    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder
    fun bindItem(item: T, holder: H)
}