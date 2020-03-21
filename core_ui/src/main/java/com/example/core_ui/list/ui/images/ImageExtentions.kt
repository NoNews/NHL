package com.example.core_ui.list.ui.images

import android.widget.ImageView
import coil.api.load
import coil.transform.CircleCropTransformation

fun Image.loadTo(imageView: ImageView) {

    when (this) {
        is Image.Empty -> imageView.setImageDrawable(null)
        is Image.Network -> imageView.load(uri = url) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        is Image.Resource -> imageView.load(resId)
    }
}