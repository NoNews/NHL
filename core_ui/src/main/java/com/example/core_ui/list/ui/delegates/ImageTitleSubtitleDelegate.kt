package com.example.core_ui.list.ui.delegates

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.core_ui.R
import com.example.core_ui.list.ui.delegateadapter.BaseItemDelegate
import com.example.core_ui.list.ui.delegateadapter.BaseViewHolder
import com.example.core_ui.list.ui.delegateadapter.ListItem
import com.example.core_ui.list.ui.images.Image
import com.example.core_ui.list.ui.images.loadTo

class ImageTitleSubtitleDelegate(private val onClick: ((Item) -> Unit)? = null) :
    BaseItemDelegate<ImageTitleSubtitleDelegate.Item, ImageTitleSubtitleDelegate.ViewHolder>() {


    override fun getLayoutRes(): Int = R.layout.delegate_image_title_subtitle

    override fun canBeAttachedToItem(item: ListItem): Boolean = item is Item

    override fun getViewHolder(holderView: View): ViewHolder = ViewHolder(holderView)


    override fun bindItem(item: Item, holder: ViewHolder) {
        holder.bindItem(item, onClick)
    }


    class ViewHolder(view: View) : BaseViewHolder(view) {

        private var tvTitle: TextView = itemView.findViewById(R.id.tv_team_title)
        private var tvSubtitle: TextView = itemView.findViewById(R.id.tv_team_subtitle)
        private var ltRoot: ViewGroup = itemView.findViewById(R.id.lt_team_root)
        private var image: ImageView = itemView.findViewById(R.id.iv_image)
        private var listener: ((TitleValueItem) -> Unit)? = null


        fun bindItem(item: Item, onClick: ((Item) -> Unit)?) {
            tvTitle.text = item.title
            if (item.subtitle.isEmpty()) {
                tvSubtitle.visibility = View.GONE
            } else {
                tvSubtitle.text = item.subtitle
                tvSubtitle.visibility = View.VISIBLE
            }
            item.image.loadTo(image)
            ltRoot.setOnClickListener {
                onClick?.invoke(item)
            }

        }

    }


    data class Item(
        override val id: String,
        val title: String = "",
        val subtitle: String = "",
        val image: Image = Image.Empty,
        val payload: Any? = null
    ) : ListItem

}