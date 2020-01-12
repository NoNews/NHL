package com.example.nhlstats.common.data.response

import kotlinx.coroutines.Deferred
import retrofit2.Response

class HttpResponseWrapper<T>() {

    suspend fun wrap(data: Deferred<Response<T>>): T =
        data.await().body() ?: throw IllegalStateException()
}