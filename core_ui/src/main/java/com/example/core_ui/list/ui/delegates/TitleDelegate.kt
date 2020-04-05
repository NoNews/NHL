package com.example.core_ui.list.ui.delegates

import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.core_ui.R
import com.example.core_ui.list.ui.delegateadapter.BaseItemDelegate
import com.example.core_ui.list.ui.delegateadapter.BaseViewHolder
import com.example.core_ui.list.ui.delegateadapter.ListItem

class TitleDelegate() : BaseItemDelegate<TitleDelegate.Item, TitleDelegate.ViewHolder>() {


    override fun getLayoutRes(): Int = R.layout.delegate_title

    override fun getViewHolder(holderView: View): ViewHolder =
        ViewHolder(holderView)

    override fun canBeAttachedToItem(item: ListItem): Boolean = item is Item

    override fun bindItem(item: Item, holder: ViewHolder) {
        holder.bind(item)
    }


    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.tv_title)

        fun bind(item: Item) {
            title.text = item.title
            title.gravity = item.gravity
        }

    }

    data class Item(
        override val id: String = "",
        val title: String,
        var gravity: Int = Gravity.START
    ) : ListItem


}