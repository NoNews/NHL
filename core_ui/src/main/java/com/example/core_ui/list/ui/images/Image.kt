package com.example.core_ui.list.ui.images


sealed class Image {

    object Empty : Image()
    class Network(val url: String) : Image()
    class Resource(val resId: Int) : Image()

}

class NetworkImage(url: String)