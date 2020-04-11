package com.example.core_ui.list.ui.text


fun String.asText(): Text = Text.Simple(this)
fun CharSequence.asText(): Text = Text.Simple(this)
fun Int.asText(): Text = Text.Resource(this)



