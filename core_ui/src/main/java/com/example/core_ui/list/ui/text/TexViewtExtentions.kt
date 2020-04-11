@file:Suppress("NAME_SHADOWING")

package com.example.core_ui.list.ui.text

import android.content.Context
import android.widget.TextView

fun TextView.set(text: Text) {
    val result = context.provideText(text)
    setText(result)
}


fun Context.provideText(text: Text): CharSequence {
    return when (text) {
        is Text.Simple -> text.value
        is Text.Resource -> getString(text.resource)
        is Text.Arguments -> getString(text.resource, text.argument)
        is Text.Multi -> {
            val builder = StringBuilder()
            text.texts.forEach { current ->
                builder.append(findMultiTextValue(this, current))
            }
            builder.toString()
        }
    }
}


private fun findMultiTextValue(context: Context, text: Text) = when (text) {
    is Text.Simple -> text.value
    is Text.Resource -> context.getString(text.resource)
    is Text.Arguments -> context.getString(text.resource, text.argument)
    else -> error("Multi text doesn't support recurcive way")
}
