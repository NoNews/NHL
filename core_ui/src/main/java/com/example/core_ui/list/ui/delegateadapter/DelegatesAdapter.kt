package com.example.core_ui.list.ui.delegateadapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class DelegatesAdapter(private val manager: DelegatesManager) :
    ListAdapter<ListItem, BaseViewHolder>(

        object : DiffUtil.ItemCallback<ListItem>() {

            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) =
                oldItem == newItem

        }
    ) {


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        manager.getViewTypeByItem(item)
        return manager.getViewTypeByItem(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return manager.createItemViewHolder(parent, viewType);
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        manager.bindItem(holder, item)
    }

}
