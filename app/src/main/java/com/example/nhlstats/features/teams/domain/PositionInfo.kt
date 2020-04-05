package com.example.nhlstats.features.teams.domain

sealed class PositionInfo {
    abstract val type: PositionType

    data class Goalie(override val type: PositionType) : PositionInfo()
    data class Defenceman(override val type: PositionType) : PositionInfo()
    data class Forward(override val type: PositionType, val forwardType: ForwardType) : PositionInfo()
}