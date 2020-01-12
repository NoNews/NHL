package com.example.nhlstats.common.data.response

data class Data<T>(
    val content: T? = null,
    val error: Throwable? = null
)

fun <T> T?.toData(loading: Boolean = false, error: Throwable? = null): Data<T> =
    Data(
        content = this,
        error = error
    )
