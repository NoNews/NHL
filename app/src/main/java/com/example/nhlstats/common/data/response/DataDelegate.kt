package com.example.nhlstats.common.data.response

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataDelegate<Params : Any?, Domain : Any?>(
    private val fromNetwork: suspend (params: Params) -> Domain,
    private val fromMemory: (params: Params) -> Domain?,
    private val toMemory: ((Params, Domain) -> Unit)
) {


    suspend fun get(params: Params, forceReload: Boolean): Flow<Data<Domain>> {
        return flow {
            try {
                val fromMemory = fromMemory.invoke(params)
                if (fromMemory == null || forceReload) {
                    emit(Data(loading = true))
                    val fromNetwork = fromNetwork.invoke(params)
                    emit(fromNetwork.toData())
                    toMemory.invoke(params, fromNetwork)
                } else {
                    emit(fromMemory.toData())
                }
            } catch (e: Exception) {
                emit(Data(error = e))
            }
        }
    }
}