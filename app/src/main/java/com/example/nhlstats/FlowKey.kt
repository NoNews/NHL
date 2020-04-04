package com.example.nhlstats

import org.koin.core.qualifier.named

object FlowKey {
    fun router(flowKey: String) = named("router_$flowKey")
    fun navigationHolder(flowKey: String) = named("navigation_holder$flowKey")
}