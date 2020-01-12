package com.example.nhlstats.common.data.response

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.IllegalStateException

class RequestDelegate<Params : Any?, Domain : Any?>(
    private val fromNetwork: (params: Params) -> Domain
//    private val fromMemory: (params: Params) -> Domain?,
//    private val toMemory: ((Params, Domain) -> Unit)
) {

    suspend fun get(params: Params, forceReload: Boolean): Data<Domain> {

        return Data(error = IllegalStateException())
//        return withContext(Dispatchers.IO) {
//            val result: Data<Domain>
//            try {
//                val fromMemory = fromMemory(params)
//                result = if (fromMemory == null) {
//                    val fromNetwork = fromNetwork(params)
//                    toMemory(params, fromNetwork)
//                    fromMemory(params)
//                        .toData()
//                } else {
//                    fromMemory.toData()
//                }
//            } catch (e: Exception) {
//                return@withContext Data<Domain>(error = e)
//            }
//            result
//        }
    }

}