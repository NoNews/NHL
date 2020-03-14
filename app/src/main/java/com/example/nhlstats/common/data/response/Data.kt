package com.example.nhlstats.common.data.response

data class Data<T>(
    val content: T? = null,
    val error: Throwable? = null,
    val loading: Boolean = false
)

fun <T> T?.toData(loading: Boolean = false, error: Throwable? = null): Data<T> =
    Data(
        content = this,
        error = error
    )

fun <T> Data<T>.requireContent(): T = content ?: error("content must not be null")
fun <T> Data<T>.isError() = error != null
fun <T> Data<T>.isLoading() = loading
fun <T> Data<T>.contentLoaded() = content != null
