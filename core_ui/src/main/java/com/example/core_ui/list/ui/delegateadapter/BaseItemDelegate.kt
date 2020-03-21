package com.example.core_ui.list.ui.delegateadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class BaseItemDelegate<T : ListItem, H : BaseViewHolder> :
    ItemDelegate<T, H> {
    override fun getViewType(): Int = getLayoutRes()


    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutRes(), parent, false)
        return getViewHolder(view)
    }

    abstract fun getViewHolder(holderView: View): H


    @LayoutRes
    abstract fun getLayoutRes(): Int
}