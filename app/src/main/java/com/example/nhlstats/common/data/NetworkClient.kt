package com.example.nhlstats.common.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient(private val okHttp: OkHttpClient) {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://statsapi.web.nhl.com/api/v1/")
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S = retrofit.create(serviceClass)

}