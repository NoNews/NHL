package com.example.core_ui.list.ui.text

import androidx.annotation.StringRes
import java.io.Serializable


sealed class Text : Serializable {
    data class Simple(val value: CharSequence) : Text()
    data class Resource(@StringRes val resource: Int) : Text()
    data class Arguments(@StringRes val resource: Int, val argument: List<Text>) : Text()
    data class Multi(val texts: List<Text>) : Text()
}




