package com.example.core_ui.list.ui.delegates

data class TitleValueItem(
    val title: String,
    val subtitle: String = "",
    val imageRes: Int = 0,
    val imageUrl: String? = null,
    val payload: Any? = null
)