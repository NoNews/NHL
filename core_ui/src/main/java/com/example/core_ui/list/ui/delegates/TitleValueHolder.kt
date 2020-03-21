package com.example.core_ui.list.ui.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.core_ui.R


class TitleValueHolder private constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private var tvTitle: TextView = itemView.findViewById(R.id.tv_team_title)
    private var tvSubtitle: TextView = itemView.findViewById(R.id.tv_team_subtitle)
    private var ltRoot: ViewGroup = itemView.findViewById(R.id.lt_team_root)
    private var image: ImageView = itemView.findViewById(R.id.iv_image)
    private var listener: ((TitleValueItem) -> Unit)? = null

    constructor(
        inflater: LayoutInflater,
        parent: ViewGroup,
        listener: ((TitleValueItem) -> Unit)?
    ) : this(inflater.inflate(R.layout.delegate_image_title_subtitle, parent, false)) {
        this.listener = listener
    }

    fun bind(item: TitleValueItem) {
        tvTitle.text = item.title

        if (item.subtitle.isEmpty()) {
            tvSubtitle.visibility = View.GONE
        } else {
            tvSubtitle.text = item.subtitle
            tvSubtitle.visibility = View.VISIBLE
        }

        when {
            item.imageUrl != null -> loadImageFromNetwork(item)
            item.imageRes != 0 -> image.setImageResource(item.imageRes)
        }
        ltRoot.setOnClickListener {
            listener?.invoke(item)
        }
    }

    private fun loadImageFromNetwork(item: TitleValueItem) {
        image.load(uri = item.imageUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

}